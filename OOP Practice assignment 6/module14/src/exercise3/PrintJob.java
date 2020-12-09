package exercise3;

public class PrintJob {
    private String jobName;
    private int pages;

    public PrintJob(String jobName,int pages){
        this.jobName = jobName;
        this.pages = pages;
    }

    public String getName() {
        return jobName;
    }

    public int getPages() {
        return pages;
    }
}
