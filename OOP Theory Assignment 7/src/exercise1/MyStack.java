package exercise1;

public class MyStack {
    public char[] array = new char[5];
    private static MyStack instanceOfMyStack;
    public Object lock = new Object();

    private MyStack(){
        for(int i = 0;i<5;i++){
            array[i] = '\0';
        }
    }

    public static MyStack getInstanceOfStack(){
        if (instanceOfMyStack == null) {
            instanceOfMyStack = new MyStack();
        }
        return instanceOfMyStack;
    }

    //put char c to the end of the array
    public void push(char c) {
        synchronized (lock) {
            for (int i = 0; i < 5; i++) {
                if (array[i] == '\0') {
                    array[i] = c;
                    System.out.println("P: Pushed letter " + c);
                    lock.notifyAll();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return;
                }
            }
            try {
                System.out.println("P: Array is full.");
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //get the first char out of the array
    public char pop() {
        synchronized (lock) {
            while (true) {
                for (int i = 0; i < 5; i++) {
                    if (array[i] != '\0') {
                        char temp = array[i];
                        array[i] = '\0';
                        System.out.println("C: Pop letter " + temp);
                        lock.notify();
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return temp;
                    }
                }
                try {
                    System.out.println("C: Array is empty.");
                    long start = System.currentTimeMillis();
                    lock.wait(5000);
                    long end = System.currentTimeMillis();
                    if (end - start > 5000) {
                        System.exit(0);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
