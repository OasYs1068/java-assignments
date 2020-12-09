package exercise3;

public class TestPrinter {
    public static void main(String[] args) {
        Producer fred = new Producer(5,5,
                2000,"Fred");
        Producer elizabeth = new Producer(25,5,
                10000,"Elizabeth");
        Producer simon = new Producer(5,5,
                10000,"Simon");

        Printer printer = Printer.getSingletonInstance();
        Thread printerManager = new Thread(printer);
        printerManager.setPriority(Thread.MAX_PRIORITY);
        printerManager.start();

        Thread fredThread = new Thread(fred);
        Thread elizabethThread = new Thread(elizabeth);
        Thread simonThread = new Thread(simon);

        fredThread.start();
        elizabethThread.start();
        simonThread.start();

        try {
            fredThread.join();
            elizabethThread.join();
            simonThread.join();
            while (true) {
                if (printerManager.getState() == Thread.State.WAITING) {
                    printer.halt();
                    printerManager.interrupt();
                    break;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
