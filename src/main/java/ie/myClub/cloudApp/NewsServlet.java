package ie.myClub.cloudApp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class NewsServlet extends HttpServlet {

	private WebApplicationContext ctx;
	
	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils
		.getWebApplicationContext(getServletContext());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NewsItems nList = getNList(req);
		req.setAttribute("news", nList.getNews());
		doForward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NewsItems nList = getNList(req);
		News news = new News();
		news.setHeading(req.getParameter("heading"));
		news.setBody(req.getParameter("body"));
		nList.addNews(news);

		req.setAttribute("news", nList.getNews());
		doForward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer index = Integer.valueOf(req.getParameter("newsId"));
		NewsItems nList = getNList(req);
		nList.getNews().remove(index - 1);
		req.setAttribute("news", nList.getNews());
		doForward(req, resp);
	}

	private NewsItems getNList(HttpServletRequest req) {
		return ctx.getBean(NewsItems.class);
	}

	private void doForward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/news_Items.jsp");
		rd.forward(req, resp);
	}

}
