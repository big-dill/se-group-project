package se.uog.appview.pages;

import se.uog.appview.AppView;
import se.uog.model.UserEnum;
import se.uog.model.UserType;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginPage extends JPanel {

    private static final String TITLE = "<html><h1><strong><i>Staff Management Application</i></strong></h1><hr></html>";
    // extra space necessary to stop it looking strange
    private static final String SUB_TITLE = "<html><i>Please select your role below </i><html>";

    public LoginPage(AppView appView) {

        appView.setMenuEnabled(false);

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
            appView.setPage("Courses");
            appView.setMenuEnabled(true);
            UserType.getInstance().setUserEnum(UserEnum.DIRECTOR);
        });
        buttons.add(director, gbc);

        JButton admin = new JButton("Administrator");
        admin.addActionListener(e -> {
            appView.setPage("Courses");
            appView.setMenuEnabled(true);
            UserType.getInstance().setUserEnum(UserEnum.ADMINISTRATOR);
        });


        buttons.add(admin, gbc);

        JButton teacher = new JButton("Teacher");
        teacher.addActionListener(e -> {
            appView.setPage("Courses");
            appView.setMenuEnabled(true);
            UserType.getInstance().setUserEnum(UserEnum.TEACHER);
        });

        buttons.add(teacher, gbc);

        gbc.weighty = 1;
        add(buttons, gbc);


    }
}
