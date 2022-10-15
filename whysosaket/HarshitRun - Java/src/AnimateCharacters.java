import javax.swing.*;

public class AnimateCharacters {
    Timer timer;
    boolean harshitRunning=false;
    AnimateCharacters(){
        timer = new Timer(150, (e)->{
            if(harshitRunning)
            if(Game.harshit.getIcon()==Game.harshitImage1) Game.harshit.setIcon(Game.harshitImage2);
            else Game.harshit.setIcon(Game.harshitImage1);

            if(Game.chaser.getIcon()==Game.chaser1) Game.chaser.setIcon(Game.chaser2);
            else if (Game.chaser.getIcon()==Game.chaser2) Game.chaser.setIcon(Game.chaser3);
            else Game.chaser.setIcon(Game.chaser1);
        });
    }


    void start(){
        timer.start();
        harshitRunning=true;
    }

    void stop(){
        Game.harshit.setIcon(Game.harshitImage1);
        harshitRunning=false;
    }

    void setTimer(){
        timer.setDelay(900/Game.speed);
    }
}
