import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame();
        frame.setTitle("SpaceBoom");
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        Game game = new Game();

        //this.add(background);
        frame.add(game);
        frame.setVisible(true);
    }

}
