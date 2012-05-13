package ie.myClub.cloudApp.web;

import java.util.List;

import ie.myClub.cloudApp.News;
import ie.myClub.cloudApp.jdbcNewsList;

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
	private jdbcNewsList nList;
	
	@RequestMapping(method = RequestMethod.GET)
	public void listNews(Model model) {
		model.addAttribute("news", nList.getAllMyNews());
		model.addAttribute("newsAll", nList.getAll());
	}

	@RequestMapping(method = RequestMethod.POST)
	public void createNews(Model model, @RequestParam String heading, @RequestParam String body) {
		News news = new News();
		news.setHeading(heading);
		news.setBody(body);
		nList.save(news);
		model.addAttribute("news", nList.getAllMyNews());
		model.addAttribute("newsAll", nList.getAll());
	}

	@RequestMapping(method = RequestMethod.DELETE)
	public void deleteNews(Model model, @RequestParam int newsId) {
		nList.delete(newsId);
		model.addAttribute("news", nList.getAllMyNews());
		model.addAttribute("newsAll", nList.getAll());
	}
}
