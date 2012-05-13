package ie.myClub.cloudApp.web;


import java.util.List;

import ie.myClub.cloudApp.Fixture;
import ie.myClub.cloudApp.jdbcFixtureList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("main")
@Controller
public class MainController {

	@Autowired
	private jdbcFixtureList fList;
	
	@RequestMapping(method = RequestMethod.GET)
	public void listFixtures(Model model) {
		model.addAttribute("fixture", fList.getAllMyFixture());
		model.addAttribute("main", fList.getAll());
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createFixture(Model model, @RequestParam String venue, @RequestParam String when, @RequestParam Boolean training) {
		Fixture fixture = new Fixture();
		fixture.setVenue(venue);
		fixture.setWhen(when);
		boolean t = true;
		if(training == null) t = false;
		fixture.setTraining(t);
		fList.save(fixture);
		model.addAttribute("fixture", fList.getAllMyFixture());
		model.addAttribute("fixtureAll", fList.getAll());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteFixture(Model model, @RequestParam int fixtureId) {
		fList.delete(fixtureId);
		model.addAttribute("fixture", fList.getAllMyFixture());
		model.addAttribute("fixtureAll", fList.getAll());
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateFixture(Model model, @RequestParam int fixtureId) {
		Fixture fixture = fList.get(fixtureId);
		fixture.setTraining(!fixture.isTraining());
		fList.update(fixture);
		model.addAttribute("fixture", fList.getAllMyFixture());
		model.addAttribute("fixtureAll", fList.getAll());
	}
}
