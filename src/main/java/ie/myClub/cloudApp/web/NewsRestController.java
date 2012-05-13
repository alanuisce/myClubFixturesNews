package ie.myClub.cloudApp.web;

import java.util.List;

import ie.myClub.cloudApp.News;
import ie.myClub.cloudApp.jdbcNewsList;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriTemplate;

@Controller
public class NewsRestController {

	@Autowired
	private jdbcNewsList fList;

	@RequestMapping(value = "/News", method = RequestMethod.GET)
	public @ResponseBody
	List<News> listNews() {
	return fList.getAll();
	}

	@RequestMapping(value = "/News/{newsId}", method = RequestMethod.GET)
	public @ResponseBody
	News news(@PathVariable Integer newsId) {
	return fList.get(newsId);
	}

	@RequestMapping(value = "/News", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createNews(@RequestBody News news, HttpServletRequest request,
	HttpServletResponse response) {
		fList.save(news);
	response.addHeader("Location",
	getLocationForChildResource(request, news.getId()));
	}

	private String getLocationForChildResource(HttpServletRequest request,
	Integer childIdentifier) {
	// get the current URL from the request
	final StringBuffer url = request.getRequestURL();
	// append the /xyz to the URL and make it a UriTemplate
	final UriTemplate template = new UriTemplate(url.append("/{childId}")
	.toString());
	return template.expand(childIdentifier).toASCIIString();
	}

	@RequestMapping(value = "/News/{newsId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateNews(@PathVariable Integer newsId, @RequestBody News news) {
		News current = fList.get(newsId);
	current.setHeading(news.getHeading());
	current.setBody(news.getBody());
	fList.update(current);
	}

	@RequestMapping(value = "/News/{newsId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteNews(@PathVariable Integer newsId) {
		fList.delete(newsId);
	}
	}

	// get : curl -i --user alan:alan123 -H "Content-Type: application/json" http://localhost:8080/demoapp/rest/todo
	// create: curl -i --user alan:alan123 -H "Content-Type: application/json" -X POST -d '{"venue": "buy milk"}' http://localhost:8080/demoapp/rest/todo
	// get : curl -i --user alan:alan123 -H "Content-Type: application/json" http://localhost:8080/demoapp/rest/todo/1
	// update: curl -i --user alan:alan123 -H "Content-Type: application/json" -X PUT -d '{"venue": "Practice", "done": true}' http://localhost:8080/demoapp/rest/todo/1
	// delete: curl -i --user alan:alan123 -H "Content-Type: application/json" -X DELETE http://localhost:8080/demoapp/rest/todo/1
