
public class Testprogramm {
  private static String[] laenderSvg;
  public static void main(String[] args) {
    laenderSvg = {"A", "B"};
    test(["a", "b"]);
  }
  public static void test(String stringarray[]){
    System.out.println(stringarray[1]);
  }

}
