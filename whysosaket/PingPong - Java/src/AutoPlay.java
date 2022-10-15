import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoPlay implements ActionListener {
    private Timer timer;
    JLabel label;
    int player,curPosX,curPosY,curSpeedX,curSpeedY,steps,difficulty=2;
    boolean move=false;
    AutoPlay(JLabel label, int player){
        //setting up timer
        timer = new Timer(5,this);
        this.label=label;
        this.player=player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        curPosX= Game.ballPosX;
        curSpeedX = Game.ballSpeedX;
        if(player==2&&curPosX>Main.frame.getWidth()/2&&curSpeedX<0||player==1&&curPosX<Main.frame.getWidth()/2&&curSpeedX>0) {
            move=true;
            calculate();
        }else move=false;

        if(move) {
                if(label.getY()<curPosY+curSpeedY*steps-getDifficulty()) {
                    label.setBounds(label.getX(),label.getY()+getDifficulty(),label.getWidth(),label.getHeight());
                } else if (label.getY()>curPosY+curSpeedY*steps+getDifficulty()) {
                    label.setBounds(label.getX(), label.getY() - getDifficulty(), label.getWidth(), label.getHeight());
                }
        }
    }

    void calculate(){
        //calculating
        curPosX= Game.ballPosX;
        curPosY= Game.ballPosY;
        curSpeedX = Game.ballSpeedX;
        curSpeedY = Game.ballSpeedY;
        steps = (label.getX() - curPosX)/30;
    }

    void start(){
        timer.start();
    }

    void stop(){
        timer.stop();
    }

    void setDifficulty(){
        difficulty = (difficulty+1)%3;
    }
    int getDifficulty(){
        int speed=0;
        switch (difficulty){
            case 0-> speed=1;
            case 1-> speed=2;
            case 2-> speed=3+Game.ballSpeed%11;
            case 10-> speed=4;
        }
        return speed;
    }
}
