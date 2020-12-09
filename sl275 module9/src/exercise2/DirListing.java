package exercise2;

import java.io.*;
import java.util.Enumeration;
import java.util.Properties;

public class DirListing {
	public static void main(String[] args){
		File file = new File(args[0]);
		try{
			PrintWriter out	= new PrintWriter(new FileOutputStream(file));
			Properties props = System.getProperties();
			Enumeration prop_names = props.propertyNames();

		    while ( prop_names.hasMoreElements() ) {
		      String prop_name = (String) prop_names.nextElement();
		      String property = props.getProperty(prop_name);
		      
		      System.out.println("property '" + prop_name
					 + "' is '" + property + "'");
		      out.println("property '" + prop_name
						 + "' is '" + property + "'");
		     
		    } 
		    File directoryF = new File("src\\exercise2");
		    System.out.println(directoryF.getCanonicalPath());//获取标准的路径 
		    System.out.println(directoryF.getAbsolutePath());//获取绝对路径 
		  
		    
		    String directory = props.getProperty("user.dir");
		    System.out.println(directory);
		    out.close();
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
