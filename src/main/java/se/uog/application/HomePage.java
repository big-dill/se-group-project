package se.uog.application;

import se.uog.user.User;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class HomePage extends JPanel {

    private static final String TITLE = "<html><h1><strong><i>Staff Management Application</i></strong></h1><hr></html>";
    // extra space necessary to stop it looking strange
    private static final String SUB_TITLE = "<html><i>Please select your role below </i><html>";

    private static final String LANDING_PAGE = "Courses";

    public HomePage(AppController appController, AppView appView) {

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
        director.addActionListener(e -> {
            appController.setUser(User.DIRECTOR);
            appView.setPage(LANDING_PAGE);
        });
        buttons.add(director, gbc);

        JButton admin = new JButton("Administrator");
        admin.addActionListener(e -> {
            appController.setUser(User.ADMINISTRATOR);
            appView.setPage(LANDING_PAGE);
        });
        buttons.add(admin, gbc);

        JButton courseDirector = new JButton("Course Director");
        courseDirector.addActionListener(e -> {
            appController.setUser(User.CLASS_DIRECTOR);
            appView.setPage(LANDING_PAGE);
        });
        buttons.add(courseDirector, gbc);


        gbc.weighty = 1;
        add(buttons, gbc);
    }
}
