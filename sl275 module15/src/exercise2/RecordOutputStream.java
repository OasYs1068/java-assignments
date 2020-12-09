package exercise2;

import java.io.DataOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

public class RecordOutputStream extends FilterOutputStream {
    public RecordOutputStream(DataOutputStream dataOut){
        super(dataOut);
    }

    public void writeRecord(Record record) throws IOException {
        String stringField = record.getStringfield();
        Integer integerField = record.getIntegerField();
        Double doubleField = record.getDoubleField();

        for(int i = 0;i<stringField.length();i++){
            out.write(stringField.charAt(i));
        }
        out.write(',');
        for(int i = 0;i<integerField.toString().length();i++){
            out.write(integerField.toString().charAt(i));
        }
        out.write(',');
        for(int i = 0;i<doubleField.toString().length();i++){
            out.write(doubleField.toString().charAt(i));
        }
        out.write('\n');



    }
}
