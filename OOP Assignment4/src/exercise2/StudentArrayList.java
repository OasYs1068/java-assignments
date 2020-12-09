package exercise2;


import java.util.*;

/**
 * This class contains methods to process array lists of {@link Student}
 * objects.
 *
 * @author  autor name
 * @version  1.0.0
 * @see  Student
 * @see  ArrayList
 */
public class  StudentArrayList  {

	/**
	 * Returns an array list with three elements.
	 *
	 * @param first  a <code>Student</code> object.
	 * @param second  a <code>Student</code> object.
	 * @param third  a <code>Student</code> object.
	 * @return an array list with the objects <code>first</code>,
	 *           <code>second</code>, and <code>third</code>
	 */
	public static ArrayList<Student> makeArrayList(
		Student  first,
		Student  second,
		Student  third)  {

		/* PLACE YOUR CODE HERE */
		ArrayList<Student> students = new ArrayList<Student>();
		students.add(first);
		students.add(second);
		students.add(third);

		return students; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Returns an array list with the same elements of the specified array
	 * arranged in the same order.
	 *
	 * @param array  an array with <code>Student</code> objects .
	 * @return an array list with the same elements of the specified array
	 *         arranged in the same order
	 */
	public static ArrayList<Student> makeArrayListFromArray(Student[] array) {
		ArrayList<Student> students = new ArrayList<Student>(Arrays.asList(array));
		/* PLACE YOUR CODE HERE */

		return students; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Returns <code>true</code> if the specified array list contains a
	 * student whose id matches the specified ID.
	 *
	 * @param arrayList  an array list of <code>Student</code> objects.
	 * @param id  a student ID.
	 * @return  <code>true</code> if the specified array list contains a
	 *          student whose ID matches the specified ID;
	 *          <code>false</code> otherwise.
	 */
	public static boolean hasStudent(
		ArrayList<Student>  arrayList,
		int  id)  {
		Iterator<Student> studentsIt = arrayList.iterator();
		while(studentsIt.hasNext()){
			if(((Student)studentsIt.next()).getId()==id){
				return true;
			}
		}
		return false;

		/* PLACE YOUR CODE HERE */

	}

	/**
	 * Returns the number of students in the specified array list whose
	 * grade is greater than or equal to the specified grade.
	 *
	 * @param arrayList  an array list of <code>Student</code> objects.
	 * @param grade  a grade.
	 * @return  the number of students in the specified array list whose
	 *          grade is greater than or equal to the specified grade.
	 */
	public static int countGradeGreaterOrEqual(
		ArrayList<Student> arrayList,
		int grade)  {
		int counter = 0;
		Iterator<Student> studentsIt = arrayList.iterator();
		while(studentsIt.hasNext()){
			if(((Student)studentsIt.next()).getGrade()>=grade){
				counter++;
			}
		}


		/* PLACE YOUR CODE HERE */

		return counter; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Returns the smallest grade of the students in the specified array list.
	 * <p>
	 * This method assumes that the array list is not empty.
	 *
	 * @param arrayList  an array list of <code>Student</code> objects.
	 * @return  the smallest grade of the students in the specified array list.
	 */
	public static int getMinGrade(ArrayList<Student> arrayList)  {
		int minGrade = arrayList.get(0).getGrade();
		Iterator<Student> studentsIt = arrayList.iterator();
		while(studentsIt.hasNext()){
			int currentGrade = ((Student)studentsIt.next()).getGrade();
			if(currentGrade<=minGrade){
				minGrade = currentGrade;
			}
		}

		/* PLACE YOUR CODE HERE */

		return minGrade; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Returns the average grade of the students in the specified array list.
	 *
	 * @param arrayList  an array list of <code>Student</code> objects.
	 * @return  the average grade of the students in the specified array list.
	 */
	public static double getGradeAverage(ArrayList<Student>  arrayList)  {
		double aveGrade = 0;
		int counterStudents = 0;
		Iterator<Student> studentsIt = arrayList.iterator();
		while(studentsIt.hasNext()){
			int currentGrade = ((Student)studentsIt.next()).getGrade();
			aveGrade = aveGrade + currentGrade;
			counterStudents++;
		}

		aveGrade = aveGrade/counterStudents;
		/* PLACE YOUR CODE HERE */

		return aveGrade; // REMOVE; USED SO THIS FILE COMPILES
	}

	/**
	 * Removes all students in the specified array list whose grade
	 * is less than the specified grade.
	 *
	 * @param arrayList  an array list of <code>Student</code> objects.
	 * @param grade  a grade.
	 */
	public static void removeGradeLess(
		ArrayList<Student>  arrayList,
		int  grade)  {
		Iterator<Student> studentIterator = arrayList.iterator();
		while(studentIterator.hasNext()){
			Student ObStudent = studentIterator.next();
			if(ObStudent.getGrade()<grade){
				studentIterator.remove();
			}
		}

	}

	/**
	 * Returns the string representation of the objects in the specified
	 * array list.
	 * <p>
	 * A new line character ( \n ) should separate the string
	 * representations of the objects. For example:
	 * </p>
	 * <pre>
	 * Student[328,Galileo Galilei,80]\nStudent[123,Albert Einstein,100]
	 * </pre>
	 * <p>
	 * Note that the string does <i>not</i> end with a new line character ( \n )
	 * </p>
	 *
	 * @param arrayList  an array list of <code>Student</code> objects.
	 * @return  the string representation of the objects in the specified
	 *          array list.
	 */
	public static String displayAll(ArrayList<Student>  arrayList)  {
		StringBuffer str = new StringBuffer();
		Iterator<Student> studentIterator = arrayList.iterator();
		while(studentIterator.hasNext()){
			str.append(studentIterator.next().toString());
			str.append("\n");
		}
		str.deleteCharAt(str.lastIndexOf("\n"));
		String string = str.toString();
		return string; // REMOVE; USED SO THIS FILE COMPILES
	}
}