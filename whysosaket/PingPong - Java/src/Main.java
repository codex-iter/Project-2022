import javax.swing.*;

public class Main {
    static JFrame frame;
    public static void main(String[] args) {
        frame = new JFrame("Ping Pong");
        frame.setDefaultCloseOperation(3);
        frame.setSize(1000,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Game game = new Game();

        frame.add(game);
        frame.setVisible(true);
    }
}
