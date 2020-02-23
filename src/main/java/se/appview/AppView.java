package se.appview;

import javax.swing.*;


public class AppView extends JFrame {

    private String WINDOW_TITLE = "AppMenu"; //Obvs can be changed later

    public AppView(){
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(WINDOW_TITLE);
        setJMenuBar(new AppMenu());
        setSize(600, 400);
        setVisible(true);


    }


}
