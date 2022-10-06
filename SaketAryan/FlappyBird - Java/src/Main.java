import javax.swing.*;

public class Main {
    static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame("Flappy Bird");
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);

        Game game = new Game();

        frame.add(game);
        frame.setVisible(true);
    }
}
