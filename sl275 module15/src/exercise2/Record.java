package exercise2;

public class Record {
    private String stringfield;
    private int integerField;
    private double doubleField;

    public Record(String paraString, int paraInt, double paraDouble){
        stringfield = paraString;
        integerField = paraInt;
        doubleField = paraDouble;
    }

    public String getStringfield(){
        return stringfield;
    }

    public int getIntegerField(){
        return integerField;
    }

    public double getDoubleField() {
        return doubleField;
    }

    public String toString(){
        return ("Record['"+getStringfield()+"', "+getIntegerField()+", "+getDoubleField()+"]");
    }
}
