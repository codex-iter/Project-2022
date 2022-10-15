import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class tictactoe {
    public static void main(String[] args) {
        new MainMenu();
    }
}

// Below Class is for Main Menu Of The Game!
class MainMenu {
    private JFrame frame = new JFrame("Login");
    private JButton startButton = new JButton("Start the Game");
    ImageIcon st = new ImageIcon("tictactoe img.jpg");

    MainMenu() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setSize(500, 505);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);


        //Background
        JLabel background = new JLabel(new ImageIcon("tictactoe_gif.gif"));
        background.setLayout(null);
        background.setForeground(Color.gray);

        startButton.setBounds(171, 415, 150, 40);
        startButton.setBackground(Color.lightGray);
        startButton.setBorder(null);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                frame.dispose();
                new Game();
            }
        });

        frame.add(startButton);
        frame.add(background);
        frame.setVisible(true);

    }
}

class Game implements ActionListener {
    JFrame frame ;
    JButton buttons[][]=new JButton[3][3];
    JButton src = new JButton();

    JMenuBar mb = new JMenuBar();
    JMenu file;
    JMenuItem restart, exit;

    // Loading Images
    ImageIcon xi = new ImageIcon("blue_x.png");
    ImageIcon oi = new ImageIcon("red_o.png");

    int x,y;
    int input=2;

    Font font;
    Game()
    {

        frame=new JFrame("Tic Tac Toe");
        file = new JMenu("File");
        restart = new JMenuItem ("Restart");
        restart.addActionListener(this);
        file.add(restart);
        exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        file.add(exit);
        mb.add(file);
        frame.setJMenuBar(mb);

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                buttons[i][j] = new JButton();
                frame.add(buttons[i][j]);
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.gray);
                buttons[i][j].addActionListener(this);

            }
        }

        //setting grid layout of 8 rows and 8 columns
        frame.setLayout(new GridLayout(3,3,5,5));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        font = new Font ("Courier",Font.BOLD,00);
    }

    String r = null;
    int count =0;

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == restart)restart();
        if(e.getSource() == exit)System.exit(0);

        if(e.getSource() instanceof JButton)
            src = (JButton)e.getSource();

        if(src.getText()!=""){JOptionPane.showMessageDialog(frame,"invalid move"); }
        else
        {
            if(count %2!=0)
            {
                src.setText("o");
                src.setIcon(oi);
                src.setFont(font);

            }
            if(count %2==0)
            {
                src.setText("x");
                src.setIcon(xi);
                src.setFont(font);

            }
            count++;


            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(e.getSource()== buttons[i][j])
                    {x=i;y=j;}
                }
            }
            if(buttons[0][0].getText()== buttons[1][1].getText()&& buttons[0][0].getText()== buttons[2][2].getText()&& buttons[0][0].getText()!="")
            {
                r= buttons[0][0].getText();
                endAction(x,y, count);
            }
            else if(buttons[0][0].getText()== buttons[0][1].getText()&& buttons[0][2].getText()== buttons[0][0].getText()&& buttons[0][0].getText()!="")
            {
                r= buttons[0][0].getText();
                endAction(x,y, count);
            }
            else if(buttons[0][0].getText()== buttons[1][0].getText()&& buttons[2][0].getText()== buttons[0][0].getText()&& buttons[0][0].getText()!="")
            {
                r= buttons[0][0].getText();
                endAction(x,y, count);
            }
            else if(buttons[1][0].getText()== buttons[1][1].getText()&& buttons[1][2].getText()== buttons[1][0].getText()&& buttons[1][0].getText()!="")
            {
                r= buttons[1][0].getText();
                endAction(x,y, count);
            }
            else if(buttons[0][1].getText()== buttons[1][1].getText()&& buttons[2][1].getText()== buttons[1][1].getText()&& buttons[1][1].getText()!="")
            {
                r= buttons[0][1].getText();
                endAction(x,y, count);
            }

            if(buttons[2][2].getText()== buttons[1][2].getText()&& buttons[2][2].getText()== buttons[0][2].getText()&& buttons[0][2].getText()!="")
            {
                r= buttons[2][2].getText();
                endAction(x,y, count);
            }
            else if(buttons[2][2].getText()== buttons[2][1].getText()&& buttons[2][2].getText()== buttons[2][0].getText()&& buttons[2][0].getText()!="")
            {
                r= buttons[2][2].getText();
                endAction(x,y, count);
            }
            else if(buttons[2][0].getText()== buttons[1][1].getText()&& buttons[1][1].getText()== buttons[0][2].getText()&& buttons[0][2].getText()!="")
            {
                r= buttons[2][0].getText();
                endAction(x,y, count);
            }
            else if(count ==9)
            {
                r="d";
                draw();
            }
        }
    }
    public void restart()
    {
        frame.dispose();
        new Game();
    }

    public void endAction(int x1,int y1,int cnt1)
    {


        input = JOptionPane.showConfirmDialog(frame, buttons[x1][y1].getText()+" Won \n want to restart?");
        if(input == 1)System.exit(0);
        else if(input == 0)restart();


    }
    public void draw()
    {
        input = JOptionPane.showConfirmDialog(frame,"Draw\n want to restart ?");
        if(input == 1)System.exit(0);
        else if(input == 0)restart();
    }
}

