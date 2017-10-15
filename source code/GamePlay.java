package Game;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class GamePlay extends JPanel implements KeyListener,ActionListener {

    boolean sound;

    private boolean play = false;
    private int score = 0;

    private Timer time;
    public int delay=4;

    private int playerX=200;

    int bollX=210;
    int bollY=60;
    int bollDX=-1;
    int bollDY=-2;

    GamePlay(){
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        time = new Timer(delay,this);
        time.start();

        repaint();

    }

    //........................................................................

    public void paint(Graphics graphics){

        //background

        graphics.setColor(Color.black);
        graphics.fillRect(0,0,700,700);

        //paddle

        graphics.setColor(Color.yellow);
        graphics.fillRect(playerX,600,140,10);

        //boll

        graphics.setColor(Color.red);
        graphics.fillOval(bollX,bollY,20,20);

        //score
        graphics.setFont(new Font("score",Font.BOLD,15));
        graphics.drawString("Score : "+score,550,80);

        //NPI
        graphics.setFont(new Font("NPI",Font.BOLD,25));
        graphics.drawString("Narsingdi Polytechnic Institute",150,30);

        graphics.setFont(new Font("NPI",Font.BOLD,15));
        graphics.drawString("CMT-2/5",610,30);


      //  graphics.setColor(Color.blue);
        graphics.fillRect(0,40,700,10);

        //game over

        if (bollY>=593){
            play=false;
            playerX = 250;
            bollX=100;


            graphics.setFont(new Font("text",Font.BOLD,30));
            graphics.drawString("Game over.",250,300);

            graphics.setFont(new Font("Score",Font.BOLD,20));
            graphics.drawString("Score : "+score,250,325);

            //  Restart

            graphics.setFont(new Font("restart",Font.BOLD,20));
            graphics.drawString("press enter to restart",250,350);


            // game over sound

            if (sound == true){
                gameOver();
                sound=false;
            }

        }



    }
//..................................................................................



    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (playerX >= 560) {
                playerX = 560;
            } else {
                moveRight();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 5) {
                playerX = 5;
            } else {
                moveLeft();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (play==false) {
                play = true;
                bollX=210;
                bollY=60;
                score=0;
                sound=true;

            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }

    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        // paddle detacttor
         if (new Rectangle(bollX,bollY,20,20).intersects(new Rectangle(playerX,600,140,8))){
             bollDY=-bollDY;

             //Score counter

             score+=1;


             //play sound

             URL audio = GamePlay.class.getResource("sound.wav");
             AudioClip clip = Applet.newAudioClip(audio);
             clip.play();
         }

        if (play){
            bollX+=bollDX;
            bollY-=bollDY;
        }

        if (bollY<50){
            bollDY=-bollDY;
        }
        if (bollX<0){
            bollDX=-bollDX;
        }
        if (bollX>670){
            bollDX=-bollDX;
        }

        repaint();
    }

    //.......................................................... Other function .......

   void moveRight(){
        play=true;
        playerX=playerX+10;
    }

    void moveLeft(){
        play=true;
        playerX=playerX-10;
    }

    void gameOver(){

        URL over = GamePlay.class.getResource("GameOver.wav");
        AudioClip c = Applet.newAudioClip(over);
        c.play();
    }

    }


