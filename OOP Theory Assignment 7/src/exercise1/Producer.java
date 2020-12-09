package exercise1;

public class Producer implements Runnable {
    @Override
    public void run() {
        MyStack stackInstance = MyStack.getInstanceOfStack();
        for(char character = 'a' ; character <= 'z' ; character++){
            stackInstance.push(character);
        }
    }
}
