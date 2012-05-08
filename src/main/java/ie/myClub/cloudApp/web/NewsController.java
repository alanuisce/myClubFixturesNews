package ie.myClub.cloudApp.web;

import java.util.List;

import ie.myClub.cloudApp.News;
import ie.myClub.cloudApp.NewsItems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("news")
@Controller
public class NewsController {

	@Autowired
	private NewsItems nList;

	@RequestMapping(method = RequestMethod.GET)
	public void listFixtures(Model model) {
		model.addAttribute("news", nList.getNews());
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createFixture(Model model, @RequestParam String heading, @RequestParam String body) {
		News news = new News();
		news.setHeading(heading);
		news.setBody(body);
		nList.addNews(news);
		model.addAttribute("news", nList.getNews());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteFixture(Model model, @RequestParam int newsId) {
		nList.getNews().remove(newsId - 1);
		model.addAttribute("news", nList.getNews());
	}

}
