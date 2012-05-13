package ie.myClub.cloudApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;

@Secured("ROLE_USER")
public class jdbcFixtureList {

	private JdbcTemplate jdbcTemplate;

	public jdbcFixtureList(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	jdbcFixtureList() {
	}

	public void save(Fixture fixture) {
		jdbcTemplate.update(
				"insert into Fixture (venue, when, training, owner) values(?,?,?,?)",
				fixture.getVenue(), fixture.getWhen(), fixture.isTraining(), getCurrentUser());
	}

	public Fixture get(int id) {
		return jdbcTemplate.queryForObject(
				"select id, venue, when, training from Fixture where id=? and owner=?",
				new FixtureMapper(), id, getCurrentUser());
	}

	public List<Fixture> getAllMyFixture() {
		return jdbcTemplate.query(
				"select id, venue, when, training from Fixture where owner=?",
				new FixtureMapper(), getCurrentUser());
	}
	
	public List<Fixture> getAll() {
		return jdbcTemplate.query(
				"select id, venue, when, training from Fixture",
				new FixtureMapper());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from Fixture where id=? and owner=?", id,
				getCurrentUser());
	}

	public void update(Fixture fixture) {
		jdbcTemplate.update(
				"update Fixture set venue=?, when=?, training=? where id=? and owner=?",
				fixture.getVenue(), fixture.getWhen(), fixture.isTraining(), fixture.getId(), getCurrentUser());
	}

	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}

class FixtureMapper implements RowMapper<Fixture> {

	public Fixture mapRow(ResultSet rs, int rowNum) throws SQLException {
		Fixture fixture = new Fixture();
		fixture.setId(rs.getInt("id"));
		fixture.setVenue(rs.getString("venue"));
		fixture.setWhen(rs.getString("when"));
		fixture.setTraining(rs.getBoolean("training"));
		return fixture;
	}

}