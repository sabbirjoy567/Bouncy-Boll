package Game;


import javax.swing.*;

public class main {

    public static void main(String[] args){
        JFrame jFrame = new JFrame("Buncy boll");
        GamePlay gamePlay = new GamePlay();




        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(3);
        jFrame.setSize(700,650);
        jFrame.setVisible(true);

        jFrame.add(gamePlay);




    }



}
