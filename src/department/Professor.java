package department;

public class Professor extends Person implements AttendsCourses, ParticipateHours {

	public Professor(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	@Override
	public int getParticipatingHours(int courseHours) {
		int labHours = courseHours > 4 ? 2: 1;
		return courseHours - labHours;		
	}
	
	@Override
	public String toString() {
		return "Professor " + getFirstName() + " " + getLastName() + " " + getTotalHours();
	}
}
