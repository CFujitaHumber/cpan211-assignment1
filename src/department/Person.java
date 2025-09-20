package department;

public abstract class Person implements ParticipateHours, AttendsCourses{
	private final String FIRST_NAME;
	private final String LAST_NAME;
	private int totalHours;
	
	
	public Person(String firstName, String lastName) {
		this.FIRST_NAME = firstName;
		this.LAST_NAME = lastName;
		this.totalHours = 0;
	}
	public String getFirstName() {
		return FIRST_NAME;
	}
	
	public String getLastName() {
		return LAST_NAME;
	}
	public int getTotalHours() {
		return totalHours;
	}
	
	public void addHours(int hours) {
        if (hours > 0) {
            this.totalHours += hours;
        }
    }
	
	@Override
	public void attend(Course course) {
		addHours(getParticipatingHours(course.getTotalHours()));
		course.add(this);
	}
	
	@Override
	public String toString() {
		return "Person " + FIRST_NAME + " " + LAST_NAME + " Total Hours: " + totalHours;
	}
}
