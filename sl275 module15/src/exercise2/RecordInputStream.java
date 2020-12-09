package exercise2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FilterInputStream;
import java.io.IOException;

public class RecordInputStream extends FilterInputStream {
    public RecordInputStream(DataInputStream dataIn){
        super(dataIn);
    }

    public Record readRecord() throws IOException {
        StringBuffer stringField = new StringBuffer();
        Integer integerField = 0;
        Double doubleField = 0.0;

        int temp;
        while ((temp = in.read()) != -1 && temp !=',' && temp != '\n') {
            stringField.append((char)temp);
        }
        StringBuffer tempstr1 = new StringBuffer();
        while ((temp = in.read())!=-1 && temp != ',' && temp != '\n'){
            tempstr1.append((char)temp);
        }
        integerField = Integer.parseInt(tempstr1.toString());

        StringBuffer tempstr2 = new StringBuffer();
        while ((temp = in.read())!=-1 && temp !=',' && temp !='\n'){
            tempstr2.append((char)temp);
        }
        doubleField = Double.parseDouble(tempstr2.toString());


        return new Record(stringField.toString(),integerField,doubleField);
    }
}
