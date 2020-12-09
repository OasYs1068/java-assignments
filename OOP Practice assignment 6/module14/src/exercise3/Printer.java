package exercise3;

import java.io.ObjectInputStream;

public class Printer implements Runnable {
    private Queue printQueue;
    private boolean stateIsRunning;
    private final Object lock = new Object();
    private static Printer printerInstance;

    private Printer(){
        printQueue = new CircularQueue(5);
        stateIsRunning = true;
    }

    public static Printer getSingletonInstance(){
        if (printerInstance == null) {
            printerInstance = new Printer();
        }
        return printerInstance;
    }

    synchronized public void halt(){
        stateIsRunning = false;
    }

    public void addJob(PrintJob job) throws FullQueueException {
        synchronized (lock) {
            while (true) {
                try {
                    printQueue.addBack(job);
                    System.out.println("adding " + job.getName() + " to the Queue");
                    lock.notifyAll();
                    break;
                } catch (FullQueueException e) {
                    System.out.println("The queue is already full.");
                    try{
                        lock.wait();
                    }catch (InterruptedException e1){
                        e1.printStackTrace();
                    }
                }
            }
        }
    }

    private PrintJob getJob() throws EmptyQueueException, InterruptedException {
        synchronized (lock) {
            try {
                PrintJob job;
                job = (PrintJob) printQueue.getFront();
                printQueue.removeFront();
                return job;
            } catch (EmptyQueueException e) {
                System.out.println("Printer waiting for a job.");
                try{
                    lock.wait();
                } catch (InterruptedException e1){
                    throw new InterruptedException();
                }
                throw new EmptyQueueException(printQueue);
            }
        }
    }

    @Override
    public void run() {
        System.out.println("Print Manager is starting up.");
        synchronized (lock) {
            while(stateIsRunning){
                try {
                    PrintJob theJob = getJob();
                    System.out.println("The job "+theJob.getName()+" is starting.");
                    System.out.println("Processing job...");
                    Thread.sleep(500*theJob.getPages());
                    System.out.println("The job "+theJob.getName()+" is completed.");
                    lock.notifyAll();
                    Thread.sleep(100);
                } catch (EmptyQueueException | InterruptedException ignored) { }
            }
        }
        System.out.println("Print manager is halted.");
    }
}
