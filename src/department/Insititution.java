package department;

import java.util.Arrays;

/**
 * @author Carson Fujita This class defines an academic institute that is formed
 *         of {@link People} in titles of teaching assistant, professor and
 *         student.
 * 
 *         As the client class it will read from {@link Insititution#FILE} as
 *         required from Assignment 1 and produce the proper printout.
 */
public final class Insititution {

	/**
	 * Runs the assignment 1 on the terminal.
	 * 
	 * @param args - arguements
	 * @since 1.0.0
	 */
	public static void main(String[] args) {
		// Create Insitution
		Insititution Humber = new Insititution();
		try {
			Humber.parseFile();
			Humber.printRegistry();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The record specified in Assignment 1.
	 * 
	 * @since 1.0.0
	 */
	public static final String FILE = "Professor 	Isaac Newton 	Physics 	6\n"
			+ "TA 		Marie Curie 		Physics 	6\n" + "Professor 	Isaac Newton 	Calculus 	4\n"
			+ "Student 	Amy Adams 		Calculus 	4\n" + "Student	Will Smith 		Calculus 	4\n"
			+ "Student 	Brad Pitt 		Physics 	6\n" + "Student	Will Smith 		Physics 	6\n"
			+ "Professor 	Dmitri Mendeleev 	Chemistry 	6\n" + "TA 		Carl Gauss		Calculus	4\n"
			+ "Student 	Amy Adams 		Economics 	3\n" + "Professor 	Adam Smith 		Economics 	3\n"
			+ "TA 		Marie Curie 		Chemistry 	6\n" + "Student 	Brad Pitt 		Chemistry 	6\n"
			+ "Student 	Will Smith 		Chemistry 	6\n" + "end";

	/**
	 * This {@link Insititution institute's} course selection. The {@link Course}s
	 * that make up this institute.
	 * 
	 * @since 1.0.0
	 */
	private Course[] courses;

	/**
	 * The registry of all {@link Person people} that make up this institute.
	 * 
	 * @since 1.0.0
	 */
	private Person[] registry;

	/**
	 * Initializes an empty {@link Insititution Institute}
	 * 
	 * @since 1.0.0
	 */
	public Insititution() {
		courses = new Course[0];
		registry = new Person[0];
	}

	/**
	 * This {@link Insititution Institute} will search it's {@link #courses
	 * registered courses} to determine if there is already a matching
	 * {@link Course} under the specified name.
	 * 
	 * @param name - specified {@link Course course} name
	 * @returns true if a matching {@link Course} is found under the specified name;
	 *          otherwise, false.
	 */
	public boolean hasCourse(String name) {
		// Check for already existing course
		for (Course course : courses) {
			if (course.getName() == name) {
				return true; // it already exists
			}
		}
		return false;
	}

	/**
	 * Invokes {@link #hasCourse(String, int)} to determine if a {@link Course}
	 * exists under the specified name and total hours. If a match is found no
	 * course will be added; otherwise, it is added to this {@link Insititution
	 * Institute}'s {@link #courses registered courses}.
	 * 
	 * @param name       - specified {@link Course course} name
	 * @param totalHours - total hours of the {@link Course course}
	 */
	public void addCourse(String name, int totalHours) {
		// Do nothing if it already exists
		if (hasCourse(name)) {
			return;
		}

		// append new course since it doesn't exist
		courses = Arrays.copyOf(courses, courses.length + 1);
		courses[courses.length - 1] = new Course(name, totalHours);
	}

	/**
	 * This {@link Insititution Institute} will search it's {@link #courses
	 * registered courses} to determine if there is a matching {@link Course} under
	 * the specified name.
	 * 
	 * @param name - specified {@link Course course} name
	 * @return the matching {@link Course course} under the specified name, or
	 *         {@code null} if not found.
	 * @since 1.0.0
	 */
	public Course getCourse(String name) {
		for (Course course : courses) {
			if (course.getName() == name) {
				return course;
			}
		}
		return null;
	}

	/**
	 * This {@link Insititution Institute} will iterate over it's entire
	 * {@link #registry} to invoke every {@link Person}'s {@link Person#toString()}
	 * and output this line-by-line directly to {@link System.out}.
	 * 
	 * @see #registry
	 * @since 1.0.0
	 */
	public void printRegistry() {
		for (Person person : registry) {
			System.out.println(person);
		}
	}

	/**
	 * Parses {@link #FILE} data into {@link Professor}, {@link TeachingAssistant},
	 * {@link Student} into it's {@link #courses} and {@link #registry}
	 * 
	 * @throws Exception on invalid titles
	 */
	public void parseFile() throws Exception {
		String[] lines = FILE.split("\\R");// split by new line
		for (String line : lines) {
			// This regex took forever to figure out
			// essentially we are using \\s+ to find all types of whitespaces
			// then then finding any amount of them {1,} and replacing it with a comma
			// this is to clean up the FILE
			line = line.replaceAll("\\s+{1,}", ",");
			String[] tokens = line.split(","); // delimited by spaces

			// first token should be title or end
			String title = tokens[0];

			// determine if it's the end line
			if (title.equals("end")) {
				return;
			}

			// get the rest of the values
			String firstName = tokens[1];
			String lastName = tokens[2];
			String courseName = tokens[3];
			int courseTotalHours = Integer.parseInt(tokens[4]);

			// adds the course and if it exists it won't add
			addCourse(courseName, courseTotalHours);

			// the course has to exist now; retrieve it
			Course course = getCourse(courseName);

			// create a new person
			Person newRegister;

			switch (title) {
			case "Professor":
				newRegister = new Professor(firstName, lastName);
				break;
			case "Student":
				newRegister = new Student(firstName, lastName);
				break;
			case "TA":
				newRegister = new TeachingAssistant(firstName, lastName);
				break;
			default:
				throw new Exception("Invalid title: " + title + " valid titles are 'Professor' 'Student' and 'TA'");
			}
			newRegister.attend(course);

			// append this new person to registry
			registry = Arrays.copyOf(registry, registry.length + 1);
			registry[registry.length - 1] = newRegister;
		}
	}
}