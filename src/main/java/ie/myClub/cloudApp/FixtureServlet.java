package ie.myClub.cloudApp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.WebApplicationContext;

public class FixtureServlet extends HttpServlet {

	private WebApplicationContext ctx;

	@Override
	public void init() throws ServletException {
		ctx = WebApplicationContextUtils
				.getWebApplicationContext(getServletContext());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		FixtureList fList = getFList(req);
		req.setAttribute("fixtures", fList.getFixtures());
		doForward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		FixtureList fList = getFList(req);
		Fixture fixture = new Fixture();
		fixture.setVenue(req.getParameter("venue"));
		fixture.setWhen(req.getParameter("when"));
		boolean t = req.getParameter("training") != null;
		//fixture.setWhen(t.toString);
		fixture.setTraining(t);
		fList.addFixture(fixture);
		req.setAttribute("fixtures", fList.getFixtures());
		doForward(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer index = Integer.valueOf(req.getParameter("fixtureId"));
		FixtureList fList = getFList(req);
		Fixture fixture = fList.getFixtures().get(index - 1);
		fixture.setTraining(!fixture.isTraining());
		req.setAttribute("fixtures", fList.getFixtures());
		doForward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer index = Integer.valueOf(req.getParameter("fixtureId"));
		FixtureList fList = getFList(req);
		fList.getFixtures().remove(index - 1);
		req.setAttribute("fixtures", fList.getFixtures());
		doForward(req, resp);
	}

	private FixtureList getFList(HttpServletRequest req) {
		return ctx.getBean(FixtureList.class);
	}

	private void doForward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/fixture_List.jsp");
		rd.forward(req, resp);
	}

}
