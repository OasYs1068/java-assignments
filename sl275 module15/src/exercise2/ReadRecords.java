package exercise2;
/***
**** What classes need to be imported?
***/
import java.io.*;

public class ReadRecords {
  public static void main(String[] args) {
    Record record;

    try {
      /***
      **** Step 1: declare and initialize the record input stream
      ***/
        FileInputStream fileIn = new FileInputStream("record.db");
        RecordInputStream recIn = new RecordInputStream(new DataInputStream(fileIn));

      /***
      **** Step 2: read the five records from the record input stream
      **** Don't forget to close the top-level stream.
      ***/
      for(int i = 0; i<5;i++){
          record = recIn.readRecord();
          System.out.println(record.toString());
      }
      fileIn.close();

      // Handle excpetions
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
