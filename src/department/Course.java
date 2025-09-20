package department;

import java.util.Arrays;

/**
 * This class defines a record of a course with {@link AttendsCourses attenders}
 * and the total time of the course including lecture an lab hours.
 * 
 * @author Carson Fujita
 * @since 1.0.0
 */
public class Course {

	/**
	 * A singular name for this {@link Course course} acting as primary key to
	 * identify it.
	 * 
	 * @since 1.0.0
	 */
	private final String NAME;

	/**
	 * The total amount of hours for this {@link Course course} including the
	 * lecture and lab hours.
	 * 
	 * @since 1.0.0
	 */
	private final int TOTAL_HOURS;

	/**
	 * A reference of all {@link AttendsCourses} sub-interfaces that make up this
	 * {@link Course}.
	 * 
	 * @since 1.0.0
	 */
	private AttendsCourses[] attendance;

	/**
	 * Initializes a {@link Course} with a specified name and total hours. The
	 * {@link Course course} will have no one in {@link #attendance}
	 * 
	 * @param name       - the specified name of the {@link Course course}
	 * @param totalHours - the specified total hours of the {@link Course course}
	 * @see #NAME
	 * @see #TOTAL_HOURS
	 * @since 1.0.0
	 */
	public Course(String name, int totalHours) {
		NAME = name;
		TOTAL_HOURS = totalHours;
		attendance = new AttendsCourses[0];
	}

	/**
	 * Determines if the specified {@link AttendsCourses classmate} already exists
	 * within this {@link Course course's} {@link #attendance}
	 * 
	 * @param classmate - the specified {@link AttendsCourses classmate}
	 * @return true if the specified {@link AttendsCourses classmate} is already in
	 *         {@link #attendance}; otherwise, false.
	 */
	public boolean has(AttendsCourses classmate) {
		for (AttendsCourses attender : attendance) {
			if (attender.equals(classmate)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return the {@link #NAME name} for this {@link Course course}.
	 * @since 1.0.0
	 */
	public String getName() {
		return NAME;
	}

	/**
	 * @return the {@link #TOTAL_HOURS total hours} for this {@link Course course}.
	 * @since 1.0.0
	 */
	public int getTotalHours() {
		return TOTAL_HOURS;
	}

	/**
	 * Invokes {@link #has(AttendsCourses)} to determine if the
	 * {@link AttendsCourses classmate} already exists on the {@link #attendance}.
	 * Otherwise appends to {@link #attendance} the specified {@link AttendsCourses
	 * new registered classmate}.
	 * 
	 * @param newRegister - the specified {@link AttendsCourses new registered
	 *                    classmate}
	 * @throws RuntimeException if the specified {@link AttendsCourses new
	 *                          registered classmate} already exists within
	 *                          {@link #attendance}
	 * @since 1.0.0
	 */
	public void add(AttendsCourses newRegister) throws RuntimeException {
		if (has(newRegister)) {
			throw new RuntimeException("Attempting to add already existing classmate");
		}
		attendance = Arrays.copyOf(attendance, attendance.length + 1);
		attendance[attendance.length - 1] = newRegister;
	}
}