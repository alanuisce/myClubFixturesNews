package ie.myClub.cloudApp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class jdbcFixtureList {
	private JdbcTemplate jdbcTemplate;

	public jdbcFixtureList(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void save(Fixture fixture) {
		jdbcTemplate.update("insert into Fixture (text, done) values(?,?)",
				fixture.getVenue(), fixture.isTraining());
	}

	public Fixture get(int id) {
		return jdbcTemplate.queryForObject(
				"select id, text, done from Fixture where id=?", new FixtureMapper(),
				id);
	}
	
	public List<Fixture> getAll() {
		return jdbcTemplate.query("select id, text, done from Fixture",
		new FixtureMapper());
		}
	
	public void delete(int id) {
		jdbcTemplate.update("delete from Fixture where id=?", id);
		}
	
	public void update(Fixture fixture) {
		jdbcTemplate.update("update Fixture set text=?, done=? where id=?",
				fixture.getVenue(), fixture.isTraining(), fixture.getId());
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