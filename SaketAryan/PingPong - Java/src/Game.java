import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Game extends JPanel implements KeyListener, ActionListener {

    ImageIcon plate = new ImageIcon("plate.png");
    JLabel plateP1;                     //for player 1
    JLabel plateP2;                     //for player 2
    Timer timer;                        //game Timer
    Random random = new Random();
    AutoPlay autoPlay;
    AutoPlay sim;
    boolean play = false, doublePlayer=true, simulate=false, win, lose;

    static int ballPosX, ballPosY, ballSpeedX, ballSpeedY, ballSpeed=8;
    final int resetSpeed=6;
    private int pointsP1, pointsP2;

    // for animation player 1
    boolean moveDownP1 = false; int downTargetP1 =0;
    boolean moveUpP1 = false; int upTargetP1 =0;

    // for animation player 2
    boolean moveDownP2 = false; int downTargetP2 =0;
    boolean moveUpP2 = false; int upTargetP2 =0;
    Game(){
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.setFocusable(true);
        this.addKeyListener(this);

        plateP1 = new JLabel();
        plateP1.setIcon(plate);

        plateP2 = new JLabel();
        plateP2.setIcon(plate);

        defaultValues();

        //setting timer
        timer = new Timer(30,this);

        autoPlay = new AutoPlay(plateP2,2);
        sim = new AutoPlay(plateP1,1);


        this.add(plateP1);
        this.add(plateP2);
    }

    public void paint(Graphics g){

        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.white);

        if(play) {
            g2d.fillOval(ballPosX,ballPosY,30,30);
            g2d.fillRect(Main.frame.getWidth()/2,0,5,Main.frame.getHeight());
        }

        if(!play){
            g2d.setFont(new Font(null,Font.BOLD,10));
            //controls
            g2d.drawString("Use UP/DOWN Arrow Keys to Move Plate 1",400,50);
            g2d.drawString("Use W/S Arrow Keys to Move Plate 2",400,70);
            g2d.drawString("Use 'X' to Toggle Computer as Player 2",400,90);
            g2d.drawString("Use 'D' to change difficulty levels",400,110);
            g2d.drawString("Use 'B' to change ball speed",400,130);
            g2d.drawString("Use 'SPACE' to Simulate Game",400,150);
            g2d.drawString("Use 'R' to Reset Game",400,170);

            g2d.setFont(new Font(null,Font.BOLD,50));
            if(lose&&!doublePlayer) {
                g2d.setPaint(Color.red);
                g2d.drawString("GAME OVER", 350, 250);
            }
            else if(win&&!doublePlayer){
                g2d.setPaint(Color.green);
                g2d.drawString("YOU WON", 360, 250);
            } else if (win&&doublePlayer) {
                g2d.setPaint(Color.green);
                g2d.drawString("PLAYER 1 WON", 320, 250);
            }
            else if (lose&&doublePlayer) {
                g2d.setPaint(Color.green);
                g2d.drawString("PLAYER 2 WON", 320, 250);
            }else if(!win&&!lose){
                g2d.setPaint(Color.yellow);
                g2d.drawString("PiNG PoNG", 360, 250);
            }
            g2d.setPaint(Color.white);
            g2d.drawString("PRESS SPACE TO START",200,380);
        }else if(!simulate) {
            g2d.setFont(new Font(null,Font.BOLD,10));
            switch (ballSpeed){
                case 2-> g2d.drawString("Speed: "+1,920,450);
                case 4-> g2d.drawString("Speed: "+2,920,450);
                case 6-> g2d.drawString("Speed: "+3,920,450);
                case 8-> g2d.drawString("Speed: "+4,920,450);
                case 10-> g2d.drawString("Speed: "+5,920,450);
                case 12-> g2d.drawString("Speed: "+6,920,450);
                case 14-> g2d.drawString("Speed: "+7,920,450);
            }

        }

        if(!doublePlayer){
            g2d.setPaint(Color.green);
            g2d.setFont(new Font(null,Font.BOLD,10));
            g2d.drawString("Computer",920,20);

            switch (autoPlay.difficulty){
                case 0->{
                    g2d.drawString("Easy",920,30);
                }
                case 1->{
                    g2d.setPaint(Color.yellow);
                    g2d.drawString("Medium",920,30);
                }
                case 2->{
                    g2d.setPaint(Color.red);
                    g2d.drawString("Hard",920,30);
                }
                case 10-> {
                    g2d.setPaint(Color.WHITE);
                    g2d.drawString("Simulate",920,30);
                }
            }
        }
        if(!simulate&&play)
        {
            g2d.setPaint(Color.WHITE);
            g2d.setFont(new Font(null, Font.BOLD, 20));
            g2d.drawString(pointsP1 + "", Main.frame.getWidth() / 2 - 100, 30);
            g2d.drawString(pointsP2 + "", Main.frame.getWidth() / 2 + 90, 30);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case 37-> {
               //left
            }
            case 39-> {
                //right
            }
            case 40-> {
                //down
                moveDownP1 =true;
                downTargetP1 =plateP1.getY()+19+ballSpeed;
            }
            case 38-> {
                //up
                moveUpP1 =true;
                upTargetP1 =plateP1.getY()-19-ballSpeed;
            }

            case 87-> {
                //W
                if(doublePlayer) {
                    moveUpP2 = true;
                    upTargetP2 = plateP2.getY() - 19-ballSpeed;
                }
            }

            case 83-> {
                //S
                if(doublePlayer) {
                    moveDownP2 = true;
                    downTargetP2 = plateP2.getY() + 19+ballSpeed;
                }
            }
            case 32-> {
                //space
                if(!play){
                    play = true;
                    defaultValues();
                    timer.start();
                }
                else{
                    simulateGame();
                }
            }

            case 88-> {
                //X
                resetBall();
                resetScores();
                autoPlay.difficulty=1;
                if(doublePlayer) autoPlay.start();
                else autoPlay.stop();
                doublePlayer=!doublePlayer;
            }

            case 68-> {
                //D
                autoPlay.setDifficulty();
            }

            case 66-> {
                //B
                ballSpeed();
            }
            case 82-> defaultValues();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    // All game actions
    @Override
    public void actionPerformed(ActionEvent e) {
        moveBall();
        points();
        checkCollision();
        movePlates();
        checkPosition();
        repaint();
    }

    private void defaultValues(){
        win=false;
        lose=false;
        pointsP1=0;
        pointsP2=0;
        plateP1.setBounds(20,170,10,60);
        plateP2.setBounds(970,250,10,60);
        ballSpeedX=-resetSpeed;
        ballSpeedY=resetSpeed;
        resetBall();
    }

    void simulateGame(){
        simulate=!simulate;
        if(simulate){
            doublePlayer=false;
            autoPlay.start();
            sim.start();
            sim.difficulty=10;
            autoPlay.difficulty=10;
            ballSpeedX*=2;
            ballSpeedY*=2;
        }else {
            doublePlayer=true;
            autoPlay.difficulty=8;
            autoPlay.stop();
            sim.stop();
            defaultValues();
        }
    }

    void points(){

        if(pointsP1>=10) {
            win=true;
            play=false;
            timer.stop();
        }else if(pointsP2>=10){
            lose=true;
            play=false;
            timer.stop();
        }
        //lose case
        if(ballPosX<10) {
            pointsP2++;
            resetBall();
            if(simulate) simulateGame();
        }

        //win case
        if(ballPosX>970){
            pointsP1++;
            resetBall();
            if(simulate) simulateGame();
        }
    }

    void resetBall(){
        ballPosX=Main.frame.getWidth()/2;
        ballPosY=random.nextInt(30,400);
        ballSpeedX=((ballSpeedX)/Math.abs(ballSpeedX))*-1*resetSpeed;
        ballSpeedY=((ballSpeedY)/Math.abs(ballSpeedY))*-1*resetSpeed;
    }

    void checkCollision(){
        collision(plateP1);
        collision(plateP2);

    }

    private void collision(JLabel plateP) {
        if(new Rectangle(plateP.getX(), plateP.getY(), plateP.getWidth(), plateP.getHeight()).intersects(new Rectangle(ballPosX,ballPosY,30,30))){
            ballSpeedX=((ballSpeedX)/Math.abs(ballSpeedX))*-1*ballSpeed;
            ballSpeedY=((ballSpeedY)/Math.abs(ballSpeedY))*ballSpeed;
        }
    }

    void moveBall(){
        ballPosX -= ballSpeedX;

        //for Y-Direction
        if(ballPosY<2) {
            ballPosY+=ballSpeedY;
            ballSpeedY*=-1;
        }
        else if(ballPosY<440) ballPosY-=ballSpeedY;
        else {
            ballPosY+=ballSpeedY;
            ballSpeedY*=-1;
        }
    }

    void movePlates(){
        //moving plate 1
        if(moveDownP1){
            moveUpP1 =false;
            if(plateP1.getY()< downTargetP1)
                plateP1.setBounds(plateP1.getX(),plateP1.getY()+7,10,60);
            else moveDownP1 =false;
        }

        if(moveUpP1){
            moveDownP1 =false;
            if(plateP1.getY()> upTargetP1)
                plateP1.setBounds(plateP1.getX(),plateP1.getY()-7,10,60);
            else moveUpP1 =false;
        }

        // moving plate 2
        if(moveDownP2){
            moveUpP2 =false;
            if(plateP2.getY()< downTargetP2)
                plateP2.setBounds(plateP2.getX(),plateP2.getY()+7,10,60);
            else moveDownP2 =false;
        }

        if(moveUpP2){
            moveDownP2 =false;
            if(plateP2.getY()> upTargetP2)
                plateP2.setBounds(plateP2.getX(),plateP2.getY()-7,10,60);
            else moveUpP2 =false;
        }
    }

    void checkPosition(){
        if(plateP1.getY()>405) plateP1.setBounds(plateP1.getX(),405,plateP1.getWidth(),plateP1.getHeight());
        else if(plateP1.getY()<5) plateP1.setBounds(plateP1.getX(),5,plateP1.getWidth(),plateP1.getHeight());

        if(plateP2.getY()>405) plateP2.setBounds(plateP2.getX(),405,plateP2.getWidth(),plateP2.getHeight());
        else if(plateP2.getY()<5) plateP2.setBounds(plateP2.getX(),5,plateP2.getWidth(),plateP2.getHeight());

        if(plateP1.getY()+18>405) downTargetP1=405;
        else if(upTargetP1<5) upTargetP1=5;

        if(plateP2.getY()+18>405) downTargetP2=405;
        else if(upTargetP2<5) upTargetP2=5;
    }

    void ballSpeed(){
        ballSpeed=(ballSpeed+1)%14+1;
        ballSpeedX=(ballSpeedX/Math.abs(ballSpeedX))*ballSpeed;
        ballSpeedY=(ballSpeedY/Math.abs(ballSpeedY))*ballSpeed;
    }

    void resetScores(){
        pointsP1=0;
        pointsP2=0;
    }
}
