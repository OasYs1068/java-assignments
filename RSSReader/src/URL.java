public class URL {
    private String name;
    private String address;

    public URL(String name,String address){
        this.address = address;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String toString(){
        return this.name;
    }
}
