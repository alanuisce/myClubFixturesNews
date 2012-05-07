package ie.myClub.cloudApp;

import java.util.ArrayList;
import java.util.List;

public class NewsItems {
	private List<News> news = new ArrayList<News>();

	public List<News> getNews() {
		return this.news;
	}

	public void addNews(News news){
		this.news.add(news);
	}
}
