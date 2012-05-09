package ie.myClub.cloudApp;

public class Fixture {
	private int id;
	private String venue;
	private String when;
	private boolean training;

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}

	public String getWhen() {
		return when;
	}

	public void setWhen(String when) {
		this.when = when;
	}

	public boolean isTraining() {
		return training;
	}

	public void setTraining(boolean training) {
		this.training = training;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}