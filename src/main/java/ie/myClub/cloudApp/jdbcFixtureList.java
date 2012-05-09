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

	public void save(Fixture todo) {
		jdbcTemplate.update(
				"insert into Fixture (text, done, owner) values(?,?,?)",
				todo.getVenue(), todo.isTraining(), getCurrentUser());
	}

	public Fixture get(int id) {
		return jdbcTemplate.queryForObject(
				"select id, text, done from Fixture where id=? and owner=?",
				new FixtureMapper(), id, getCurrentUser());
	}

	public List<Fixture> getAll() {
		return jdbcTemplate.query(
				"select id, text, done from Fixture where owner=?",
				new FixtureMapper(), getCurrentUser());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from Fixture where id=? and owner=?", id,
				getCurrentUser());
	}

	public void update(Fixture todo) {
		jdbcTemplate.update(
				"update Fixture set text=?, done=? where id=? and owner=?",
				todo.getVenue(), todo.isTraining(), todo.getId(), getCurrentUser());
	}

	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}

class FixtureMapper implements RowMapper<Fixture> {

	public Fixture mapRow(ResultSet rs, int rowNum) throws SQLException {
		Fixture fixture = new Fixture();
		fixture.setId(rs.getInt("id"));
		fixture.setVenue(rs.getString("text"));
		fixture.setTraining(rs.getBoolean("done"));
		return fixture;
	}

}