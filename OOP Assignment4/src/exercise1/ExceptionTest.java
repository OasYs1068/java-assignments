package exercise1;

public class ExceptionTest {
    public static void testMException() throws MyException {
        System.out.println("MyException is thrown.");
        throw new MyException();
    }

    public static void testMSException() throws MySpecialException{
        System.out.println("MySpecialException is thrown.");
        throw new MySpecialException();
    }
    public static void main(String[] args){
        try{
            testMSException();
            testMException();
        }catch(MySpecialException e1){
            System.out.println("MySpecialException is caught");
        }catch (MyException e1){
            System.out.println("MyException is caught");
        }finally {
            System.out.println("Here is finally clause.");
        }

        System.out.println("This is the end of main() method.");
    }
}
