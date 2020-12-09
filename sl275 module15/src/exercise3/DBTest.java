package exercise3;
import java.io.*;
import java.util.Scanner;

public class DBTest{
    public static void addRecord(RandomAccessFile raf){
        try{
            Scanner in = new Scanner(System.in);
            System.out.print("Record name >");
            String tempName = in.nextLine();
            System.out.print("Record quantity >");
            Integer tempQuantity = in.nextInt();
            System.out.println("\n");
            raf.seek(raf.length());
            raf.writeChars(tempName+" "+tempQuantity+'\n');
            raf.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateRecord(RandomAccessFile raf){
        try {
            Scanner in = new Scanner(System.in);
            System.out.println("Which record would you like to update?");
            System.out.print("input name>");
            String name = in.nextLine();
            System.out.print("input quantity>");
            int tempQuantity = in.nextInt();
            in.nextLine();
            long pinDown = 0;
            StringBuffer readIn = null;
            char c;
            raf.seek(0);
            while(true){
                pinDown = raf.getFilePointer();
                StringBuffer strIn = new StringBuffer();
                pinDown = raf.getFilePointer();
                readIn = new StringBuffer();
                try {
                    while (true) {
                        c = raf.readChar();
                        readIn.append(c);
                        if(c=='\n'){
                            break;
                        }
                    }
                } catch (IOException e) {
                    raf.seek(pinDown);
                    readIn = new StringBuffer(raf.readLine());
                }
                if(readIn.toString().contains(name)){
                    raf.seek(pinDown);
                    raf.writeChars(name+" "+tempQuantity);
                    System.out.println("Update success.");
                    raf.seek(pinDown);
                    System.out.println(raf.readLine());
                    break;
                }
                if(raf.getFilePointer()>=raf.length()){
                    System.out.println("No such record.");
                    break;
                }
            }
            raf.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void displayRecord(RandomAccessFile raf){
        try{
            Scanner in = new Scanner(System.in);
            System.out.println("Which record do you wish to view?");
            System.out.print("input name>");
            String name = in.nextLine();
            StringBuffer readIn = null;
            long pinDown;
            char c;
            raf.seek(0);
            while(true){
                StringBuffer record = new StringBuffer();
                pinDown = raf.getFilePointer();
                readIn = new StringBuffer();
                try {
                    while (true) {
                        c = raf.readChar();
                        readIn.append(c);
                        if(c=='\n'){
                            break;
                        }
                    }
                } catch (EOFException e) {
                    raf.seek(pinDown);
                    readIn = new StringBuffer(raf.readLine());
                }
                if(readIn.toString().contains(name)){
                    raf.seek(pinDown);
                    record.append(raf.readLine());
                    System.out.println(record.toString());
                    break;
                }
                if(raf.getFilePointer()>=raf.length()){
                    System.out.println("No such record.");
                    break;
                }
            }
            raf.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    private static void run(){
        File file = new File("flat.db");
        try {
            while(true){
                RandomAccessFile raf = new RandomAccessFile(file,"rw");
                int choice = getChoice();
                switch (choice) {
                    case 1:
                        addRecord(raf);
                        break;
                    case 2:
                        updateRecord(raf);
                        break;
                    case 3:
                        displayRecord(raf);
                        break;
                    default:
                        System.out.println("Quit successful.");
                        break;
                }
                if(choice==0){
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private static int getChoice(){
        int temp = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("DataBase Accessed.");
        System.out.println("Choose your action:");
        System.out.println("[0] Quit");
        System.out.println("[1] Add a record");
        System.out.println("[2] Update a record");
        System.out.println("[3] display records");
        System.out.print("choice >");
        temp = in.nextInt();
        return temp;
    }

    public static void main(String args[]){
        DBTest test = new DBTest();
        test.run();
    }

}


