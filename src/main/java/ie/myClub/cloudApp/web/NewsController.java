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
	public void listNews(Model model) {
		model.addAttribute("news", nList.getNews());
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createNews(Model model, @RequestParam String heading) {
		News news = new News();
		news.setHeading(heading);
		nList.addNews(news);
		model.addAttribute("news", nList.getNews());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteNews(Model model, @RequestParam int fixtureId) {
		nList.getNews().remove(fixtureId - 1);
		model.addAttribute("news", nList.getNews());
	}

}
