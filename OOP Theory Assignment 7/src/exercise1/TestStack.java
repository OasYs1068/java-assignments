package exercise1;

public class TestStack {
    public static void main(String[] args) {
        Thread tp = new Thread(new Producer());
        Thread tc = new Thread(new Consumer());

        tp.start();
        tc.start();
    }
}
