
import java.awt.Dimension;
import java.awt.Toolkit;


public class Resolucao {
    public static void main(String[] args) {
        Toolkit tk;
        tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        System.out.print("Sua resolucao e de "+d.width);
        System.out.println(" X "+d.height);
    }
}
