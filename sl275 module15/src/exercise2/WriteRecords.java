package exercise2;
/***
**** What classes need to be imported?
***/
import java.io.*;


public class WriteRecords {
  public static void main(String[] args) {
    Record rec1 = new Record("record #1", 2, Math.sqrt(2));
    Record rec2 = new Record("record #2", 3, Math.E);
    Record rec3 = new Record("record #3", 5, Math.PI);
    Record rec4 = new Record("record #4", 7, 4.0);
    Record rec5 = new Record("record #5", 9, Math.E + Math.PI);

    try {
      /***
      **** Step 1: declare and initialize the record output stream
      ***/
      FileOutputStream fileOut = new FileOutputStream("record.db");
      RecordOutputStream recOut = new RecordOutputStream(new DataOutputStream(fileOut));


      /***
      **** Step 2: write the five records to the record output stream
      **** Don't forget to close the top-level stream.
      ***/
      recOut.writeRecord(rec1);
      recOut.writeRecord(rec2);
      recOut.writeRecord(rec3);
      recOut.writeRecord(rec4);
      recOut.writeRecord(rec5);

      fileOut.close();

      System.out.println("Five records have been saved to the 'record.db' file.");

      // Handle excpetions
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
