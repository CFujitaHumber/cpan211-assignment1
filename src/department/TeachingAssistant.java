package department;

public class TeachingAssistant extends Person {

	public TeachingAssistant(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public int getParticipatingHours(int courseHours) {
		return courseHours > 4? 2:1;
	}

	@Override
	public String toString() {
		return "TA " + getFirstName() + " " + getLastName() + " Total Hours: " + getTotalHours();
	}
}
