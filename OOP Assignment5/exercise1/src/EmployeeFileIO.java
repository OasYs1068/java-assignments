import java.util.*;
import java.io.*;

/**
 * This class provides two file I/O methods for handling employee data.
 *
 * @author  author name
 * @version  1.0.0
 * @see  Employee
 */
public class  EmployeeFileIO {

	/**
	 * Creates an <code>ArrayList</code> of <code>Employee</code> objects
	 * from a file that contains employee data.
	 * <p>
	 * Every line in the specified file should contain three fields: ID, name,
	 * and salary of an employee in the following format: ID_name_salary
	 * </p>
	 *
	 * @param filename  the name of a file containing employee data.
	 * @return  an <code>ArrayList</code> of <code>Employee</code> objects.
	 * @throws FileNotFoundException  if the specified file does not exist.
	 * @throws IOException  if an I/O error occurs.
	 * @throws NoSuchElementException  if the data in the file is incomplete.
	 * @throws NumberFormatException  if the file contains invalid numbers.
	 */
	public static ArrayList<Employee> read(String filename)
		throws FileNotFoundException,
			IOException,
			NoSuchElementException,
			NumberFormatException  {
		ArrayList<Employee> employees = new ArrayList<Employee>();
		FileReader fileIn = new FileReader(filename);
		BufferedReader br = new BufferedReader(fileIn);
		String temp = "";
		StringBuffer strbf;
		while((temp=br.readLine())!=null){
			strbf = new StringBuffer();
			int i = 0;
			int underScoreCount = 1;
			Integer id = 0;
			String name = "";
			Double salary = 0.0;
			for(i=0;i<temp.length();i++){
				if (temp.charAt(i)!='_'&&temp.charAt(i)!='\n') {
					strbf.append(temp.charAt(i));
				} else if(underScoreCount==1&&temp.charAt(i)=='_'){
					id = Integer.parseInt(strbf.toString());
					strbf = new StringBuffer();
					underScoreCount++;
					continue;
				}else if(underScoreCount==2&&temp.charAt(i)=='_') {
					name = strbf.toString();
					strbf = new StringBuffer();
					underScoreCount++;
				}if(i==temp.length()-1){
					salary = Double.parseDouble(strbf.toString());
					employees.add(new Employee(id,name,salary));
					break;
				}
			}
		}
		fileIn.close();
		br.close();
		return employees;
	}

	/**
	 * Creates a file of employee data from an <code>ArrayList</code> of
	 * <code>Employee</code> objects.
	 * <p>
	 * Every line in the file should contain the ID, name, and salary of an
	 * employee separated by an underscore: ID_name_salary
	 * </p>
	 *
	 * @param filename  the name of the file that will store the employee data.
	 * @param arrayList  an <code>ArrayList</code> of <code>Employee</code>
	 *                   objects.
	 * @throws IOException  if an I/O error occurs.
	 */
	public static void write(String filename, ArrayList<Employee> arrayList)
		throws IOException  {
		FileWriter fileWriter = new FileWriter(filename);
		PrintWriter in = new PrintWriter(fileWriter);
		Iterator<Employee> employeeIterator = arrayList.iterator();
		while(employeeIterator.hasNext()){
			Employee employee = employeeIterator.next();
			in.write(employee.getId()+"_"+employee.getName()+"_"+employee.getSalary()+"\n");
		}
		fileWriter.close();
		in.close();
	}
}