package ie.myClub.cloudApp;

import java.util.ArrayList;
import java.util.List;

public class FixtureList {
	private List<Fixture> fixtures = new ArrayList<Fixture>();

	public List<Fixture> getFixtures() {
		return fixtures;
	}

	public void addFixture(Fixture fixture) {
		fixtures.add(fixture);
	}
}