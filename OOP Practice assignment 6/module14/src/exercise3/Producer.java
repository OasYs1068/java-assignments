package exercise3;

public class Producer implements Runnable {
    private int sizeOfJobs;
    private int numberOfJobs;
    private int delayBetweenJobs;
    private String producerName;

    public Producer(int sizeOfJobs,
                    int numberOfJobs,
                    int delayBetweenJobs,
                    String producerName){
        this.sizeOfJobs = sizeOfJobs;
        this.numberOfJobs = numberOfJobs;
        this.delayBetweenJobs = delayBetweenJobs;
        this.producerName = producerName;
    }

    @Override
    public void run() {
        PrintJob job;
        for(int i = 0;i<numberOfJobs;i++){
            job = new PrintJob(producerName+"#"+(i+1),sizeOfJobs);
            try {
                Printer printer = Printer.getSingletonInstance();
                printer.addJob(job);
                Thread.sleep(delayBetweenJobs);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (FullQueueException e1){
                System.out.println("The queue is already full");
            }
        }

    }
}
