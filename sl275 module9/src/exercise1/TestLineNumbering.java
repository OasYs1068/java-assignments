package exercise1;

import java.io.*;

public class TestLineNumbering {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File(args[0]);
		
		try {
			 BufferedReader in
		        = new BufferedReader(new InputStreamReader(System.in));
			 
			 PrintWriter out
		        = new PrintWriter(new FileOutputStream(file));
			 int LnNumber=1;
		     String s;
		     
		     System.out.print("Enter file text.  ");
		     System.out.println("[Type end to stop.]");
		     
		      do{
		    	 s = in.readLine();
		    	 if(s.equals("end")) {
		    		 System.out.println("The input sequence has ended.");
		    		 break;
		    	 }
		    	 System.out.println(LnNumber + "\t" + s);
		         out.println(LnNumber + "\t" + s);
		         LnNumber++;
		      }while (true);
		      
		     in.close();
		     out.close();	     
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
