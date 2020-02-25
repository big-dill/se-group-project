package se.uog.appview.pages;



import javax.swing.*;
import java.awt.*;

public class HomePage extends PageView {

    private static final String TITLE = "<html><h1><strong><i>Staff Management Application</i></strong></h1><hr></html>";
    private static final String SUB_TITLE = "<html><i>Please select your role below </i><html>"; // extra space necessary to stop it looking strange

    public HomePage(){

        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        add(new JLabel(TITLE), gbc);
        add(new JLabel(SUB_TITLE), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        JButton director = new JButton("PTT Director");
        buttons.add(director, gbc);

        JButton admin = new JButton("Administrator");
        buttons.add(admin, gbc);

        JButton teacher = new JButton("Teacher");
        buttons.add(teacher, gbc);

        gbc.weighty = 1;
        add(buttons, gbc);
    }


    @Override
    public String getName() {
        return "Home";
    }


}
