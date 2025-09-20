package department;

public class Student extends Person {

	public Student(String firstName, String lastName) {
		super(firstName, lastName);
	}

	@Override
	public int getParticipatingHours(int courseHours) {
		return courseHours;
	}
	@Override
	public String toString() {
		return "Student " + getFirstName() + " " + getLastName() + " Total Hours: " + getTotalHours();
	}
}
