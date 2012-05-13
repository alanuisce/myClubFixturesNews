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
public class jdbcNewsList {

	private JdbcTemplate jdbcTemplate;

	public jdbcNewsList(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	jdbcNewsList() {
	}

	public void save(News news) {
		jdbcTemplate.update(
				"insert into News (heading, body, owner) values(?,?,?)",
				news.getHeading(), news.getBody(), getCurrentUser());
	}

	public News get(int id) {
		return jdbcTemplate.queryForObject(
				"select id, heading, body from News where id=? and owner=?",
				new NewsMapper(), id, getCurrentUser());
	}

	public List<News> getAll() {
		return jdbcTemplate.query(
				"select id, heading, body from News where owner=?",
				new NewsMapper(), getCurrentUser());
	}

	public void delete(int id) {
		jdbcTemplate.update("delete from News where id=? and owner=?", id,
				getCurrentUser());
	}

	public void update(News news) {
		jdbcTemplate.update(
				"update News set heading=?, body=? where id=? and owner=?",
				news.getHeading(), news.getBody(), news.getId(), getCurrentUser());
	}

	private String getCurrentUser() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}

class NewsMapper implements RowMapper<News> {

	public News mapRow(ResultSet rs, int rowNum) throws SQLException {
		News news = new News();
		news.setId(rs.getInt("id"));
		news.setHeading(rs.getString("heading"));
		news.setBody(rs.getString("body"));
		return news;
	}

}
