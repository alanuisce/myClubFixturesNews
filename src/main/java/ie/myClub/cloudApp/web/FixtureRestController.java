package ie.myClub.cloudApp.web;

import java.util.List;

import ie.myClub.cloudApp.Fixture;
import ie.myClub.cloudApp.jdbcFixtureList;

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
public class FixtureRestController {

	@Autowired
	private jdbcFixtureList fList;

	@RequestMapping(value = "/Fixture", method = RequestMethod.GET)
	public @ResponseBody
	List<Fixture> listFixtures() {
	return fList.getAll();
	}

	@RequestMapping(value = "/Fixture/{fixtureId}", method = RequestMethod.GET)
	public @ResponseBody
	Fixture fixture(@PathVariable Integer fixtureId) {
	return fList.get(fixtureId);
	}

	@RequestMapping(value = "/Fixture", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void createFixture(@RequestBody Fixture fixture, HttpServletRequest request,
	HttpServletResponse response) {
		fList.save(fixture);
	response.addHeader("Location",
	getLocationForChildResource(request, fixture.getId()));
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

	@RequestMapping(value = "/Fixture/{fixtureId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateFixture(@PathVariable Integer fixtureId, @RequestBody Fixture fixture) {
		Fixture current = fList.get(fixtureId);
	current.setVenue(fixture.getVenue());
	current.setTraining(fixture.isTraining());
	fList.update(current);
	}

	@RequestMapping(value = "/Fixture/{fixtureId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteFixture(@PathVariable Integer fixtureId) {
		fList.delete(fixtureId);
	}
	}

	// get : curl -i --user alan:alan123 -H "Content-Type: application/json" http://localhost:8080/demoapp/rest/todo
	// create: curl -i --user alan:alan123 -H "Content-Type: application/json" -X POST -d '{"venue": "buy milk"}' http://localhost:8080/demoapp/rest/todo
	// get : curl -i --user alan:alan123 -H "Content-Type: application/json" http://localhost:8080/demoapp/rest/todo/1
	// update: curl -i --user alan:alan123 -H "Content-Type: application/json" -X PUT -d '{"venue": "Practice", "done": true}' http://localhost:8080/demoapp/rest/todo/1
	// delete: curl -i --user alan:alan123 -H "Content-Type: application/json" -X DELETE http://localhost:8080/demoapp/rest/todo/1
