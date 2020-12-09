public class BankCard {
    private String cardNum;
    private long cardPassword;
    private Account account;
    private String firstName,lastName;

    public BankCard(String cardNum){
        this.cardNum = cardNum;
        cardPassword = 123456;
        account = new Account(0,0.5);
    }

    public BankCard(String cardNum,double balance ,String firstName, String lastName){
        this.cardNum = cardNum;
        this.cardPassword = 123456;
        account = new Account(balance,0.5);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public BankCard(String cardNum,long cardPassword, double balance ,String firstName, String lastName){
        this.cardNum = cardNum;
        this.cardPassword = cardPassword;
        account = new Account(balance,0.5);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getCardPassword() {
        return cardPassword;
    }

    public Account getAccount(){
        return account;
    }

    public String getCardNum(){
        return cardNum;
    }

    public void setCardPassword(long newPassword){
        cardPassword = newPassword;
    }

    public boolean isCorrectMatch(String cardNum,long cardPassword){
        if(cardNum == this.cardNum||cardPassword == this.cardPassword){
            return true;
        }
        else{
            return false;
        }
    }
}
