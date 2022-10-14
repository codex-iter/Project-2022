import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Harshit Run");
        frame.setDefaultCloseOperation(3);
        frame.setSize(1000,500);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Game game = new Game();

        frame.add(game);
        frame.setVisible(true);
    }
}
