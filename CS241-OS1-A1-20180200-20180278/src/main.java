import java.awt.geom.QuadCurve2D;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Parser p = new Parser();
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equalsIgnoreCase("exit")) {
            p.parse(input);
            p.setArgumentsNull();
            input = in.nextLine();
        }
    }
}
