package exercise1;

public class TestThreeThread {
    public static void main(String[] args) {
        PrintMe thread = new PrintMe();


        Thread t1 = new Thread(thread,"T1");

        Thread t2 = new Thread(thread,"T2");

        Thread tDF = new Thread(thread,"TDarkFate");

        t1.start();
        t2.start();
        tDF.start();
    }



}
