package ie.myClub.cloudApp.web;

import java.util.List;

import ie.myClub.cloudApp.Fixture;
import ie.myClub.cloudApp.FixtureList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("fixture")
@Controller
public class FixtureController {

	@Autowired
	private FixtureList fList;

	@RequestMapping(method = RequestMethod.GET)
	public void listFixtures(Model model) {
		model.addAttribute("fixture", fList.getFixtures());
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createFixture(Model model, @RequestParam String venue, @RequestParam String when, @RequestParam String training) {
		Fixture fixture = new Fixture();
		fixture.setVenue(venue);
		fixture.setWhen(when);
		boolean t = training != null;
		fixture.setTraining(t);
		fList.addFixture(fixture);
		model.addAttribute("fixture", fList.getFixtures());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteFixture(Model model, @RequestParam int fixtureId) {
		fList.getFixtures().remove(fixtureId - 1);
		model.addAttribute("fixture", fList.getFixtures());
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void updateFixture(Model model, @RequestParam int fixtureId) {
		Fixture fixture = fList.getFixtures().get(fixtureId - 1);
		fixture.setTraining(!fixture.isTraining());
		model.addAttribute("fixture", fList.getFixtures());
	}

}
