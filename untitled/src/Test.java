public class Test {
    public static void main(String[] args) {
        Double a = new Double(1.0);
        Double b = new Double(1.0);
        Float c = new Float(1.0f);
        System.out.println(a.equals(b));
        System.out.println(c.equals(b));
        System.out.println(a == b);
    }
}
