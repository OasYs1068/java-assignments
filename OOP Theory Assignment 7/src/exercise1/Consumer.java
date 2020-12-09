package exercise1;

public class Consumer implements Runnable{
    @Override
    public void run() {
        MyStack instance = MyStack.getInstanceOfStack();
        while(true){
            instance.pop();
        }
    }
}
