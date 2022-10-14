import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JPanel implements ActionListener, KeyListener {
    ImageIcon backgroundImage;
    ImageIcon birdImage;
    ImageIcon birdUpImage;
    ImageIcon birdDownImage;
    ImageIcon poleDownImage;
    ImageIcon poleUpImage;

    JLabel background;
    JLabel bird;


    JLabel [][] poleArray;


    Timer gameTimer;
    Random random;

    private int points;

    int newFlyHeight=0, lastX=1200, pointX, prevDig;
    boolean isFlying, isFalling, play = false, gameOver=false;

    Game(){
        this.setFocusable(true);
        this.setLayout(null);
        this.addKeyListener(this);

        //setting background
        backgroundImage = new ImageIcon("resources/background.jpg");
        background = new JLabel();
        background.setIcon(backgroundImage);
        background.setBounds(0,0,1000,700);

        //Adding bird to the layout
        birdImage = new ImageIcon("resources/bird.png");
        birdUpImage = new ImageIcon("resources/birdUp.png");
        birdDownImage = new ImageIcon("resources/birdDown.png");
        bird = new JLabel();

        //Adding Poles
        poleUpImage = new ImageIcon("resources/poleUp.png");
        poleDownImage = new ImageIcon("resources/poleDown.png");

        setPoleImages();

        // Setting the timer
        gameTimer = new Timer(5,this);
        gameTimer.start();

        //setting Random
        random = new Random();


        defaultValues();


        this.add(bird);
        this.add(background);
    }

    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.white);
        g2d.setFont(new Font(null,Font.BOLD,30));
        g2d.drawString(""+points,pointX,50);


        if(!play){
            g2d.setFont(new Font(null,Font.BOLD,10));
            g2d.drawString("Use 'SPACE' to Make Flappy Fly",420,50);

            g2d.setFont(new Font(null,Font.BOLD,50));
            if(gameOver){
                g2d.setPaint(Color.red);
                g2d.drawString("GAME OVER", 350, 380);
                g2d.setPaint(Color.white);
                g2d.setFont(new Font(null,Font.BOLD,10));
                g2d.drawString("Press Enter To Restart",440,70);
            }
            else{
                g2d.drawString("PRESS ENTER TO START",200,380);
            }
        }
    }

    // Game
    @Override
    public void actionPerformed(ActionEvent e) {
        if(play) {
            fall();
            fly();
            setIcon();
            movePoles();
            collision();

            if((int)Math.log10(points)+1>prevDig) {
                prevDig = (int) Math.log10(points) + 1;
                pointX -= 20;
            }
        }else {
            play=false;
            if(gameOver)fallAnimation();
        }
        repaint();
    }


    // Controls
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 32 -> {    // SPACE
                if (play) {
                    isFalling = false;
                    newFlyHeight = bird.getY() - 50;
                    isFlying = true;
                }
            }

            case 10 -> {    // ENTER
                if (!play) startGame();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // Game Methods
    void fly(){
        if(isFlying&&bird.getY()>newFlyHeight){
            bird.setBounds(bird.getX(), bird.getY()-3, bird.getWidth(), bird.getHeight());
        }
        if(bird.getY()<newFlyHeight) {
            isFlying = false;
            isFalling = true;
        }
        if (bird.getY()<0) {
            bird.setBounds(bird.getX(), 0, bird.getWidth(), bird.getHeight());
            isFlying=false;
            isFalling=true;
        }
    }

    void fall(){
        if (isFalling&&bird.getY()<640){
            bird.setBounds(bird.getX(), bird.getY()+2, bird.getWidth(), bird.getHeight());
        }
        if(bird.getY()>640) bird.setBounds(bird.getX(), 640, bird.getWidth(), bird.getHeight());
    }

    void setIcon(){
        if (isFalling) bird.setIcon(birdImage);
        else bird.setIcon(birdUpImage);
    }

    void setPoleImages(){
        poleArray = new JLabel[3][2];

        for (int i = 0; i < poleArray.length; i++) {
            // Setting UpLabel
            poleArray[i][0] = new JLabel();
            poleArray[i][0].setIcon(poleUpImage);
            poleArray[i][0].setIcon(poleUpImage);
            poleArray[i][0].setBounds(1000,0, 80,500);

            //Setting DownLabel
            poleArray[i][1] = new JLabel();
            poleArray[i][1].setIcon(poleDownImage);
            poleArray[i][1].setBounds(1000,0, 80,500);

            this.add(poleArray[i][0]);
            this.add(poleArray[i][1]);
        }
    }

    void setPoles(JLabel poleUp, JLabel poleDown){

        int randomX=random.nextInt(1100, 2000);
        for (int i = 0; i < poleArray.length; i++) {
            if(poleUp==poleArray[i][0]) continue;
            while ((poleUp!=poleArray[0][0] && (new Rectangle(randomX, 0, 200,1700).intersects(poleArray[0][0].getBounds()) || new Rectangle(poleArray[0][0].getX(), poleArray[i][0].getY(), 200, poleArray[0][0].getHeight()).intersects(new Rectangle(randomX, 0, 220,1700)))) || (poleUp!=poleArray[1][0] && ( new Rectangle(randomX, 0, 200,1700).intersects(poleArray[1][0].getBounds()) || new Rectangle(poleArray[1][0].getX(), poleArray[i][0].getY(), 200, poleArray[i][0].getHeight()).intersects(new Rectangle(randomX, 0, 220,1700)))) || (poleUp!=poleArray[2][0] && ( new Rectangle(randomX, 0, 200,1700).intersects(poleArray[2][0].getBounds()) || new Rectangle(poleArray[2][0].getX(), poleArray[i][0].getY(), 200, poleArray[i][0].getHeight()).intersects(new Rectangle(randomX, 0, 220,1700))))){
                randomX=random.nextInt(1100, 2000);
            }
        }
        int randomY = random.nextInt(630,730);
        int randY = random.nextInt(-350,-50);
        poleDown.setBounds(randomX, randY,poleDown.getWidth(),poleDown.getHeight());
        poleUp.setBounds(randomX, randY+randomY,poleUp.getWidth(),poleUp.getHeight());
    }

    void movePoles(){
        for (int i = 0; i < poleArray.length; i++) {
            if(poleArray[i][0].getX()>-200){
                poleArray[i][0].setBounds(poleArray[i][0].getX()-2, poleArray[i][0].getY(), poleArray[i][0].getWidth(), poleArray[i][0].getHeight());
                poleArray[i][1].setBounds(poleArray[i][1].getX()-2, poleArray[i][1].getY(), poleArray[i][1].getWidth(), poleArray[i][1].getHeight());
                if(poleArray[i][0].getX()==118||poleArray[i][0].getX()==117) points++;
            }else setPoles(poleArray[i][0], poleArray[i][1]);
        }
    }

    void collision(){
        for (int i = 0; i < poleArray.length; i++) {
            if(bird.getBounds().intersects(poleArray[i][0].getBounds())||bird.getBounds().intersects(poleArray[i][1].getBounds())){
                gameOver();
            }
        }

        // gameover by fall
        if(bird.getY()>=640) gameOver();
    }

    void fallAnimation(){
        bird.setIcon(birdDownImage);
        isFalling=true;
        gameTimer.setDelay(2);
        fall();
        if(bird.getX()<220) bird.setBounds(bird.getX()+1,bird.getY(),bird.getWidth(),bird.getHeight());
    }

    void gameOver(){
        play=false;
        gameOver=true;
    }

    void startGame(){
        defaultValues();
        play = true;
        gameOver=false;
    }

    void defaultValues(){
        bird.setIcon(birdImage);
        bird.setBounds(200,200,40,40);
        isFlying = false;
        isFalling = true;
        points=0;
        prevDig=1;
        pointX=940;
        gameTimer.setDelay(5);

        // Setting Poles,
        for (int i = 0; i < poleArray.length; i++) {
            setPoles(poleArray[i][0], poleArray[i][1]);
        }
    }
}
