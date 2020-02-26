package se.uog.appview.pages;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HomePage extends JPanel {

    private static final String TITLE = "<html><h1><strong><i>Staff Management Application</i></strong></h1><hr></html>";
    // extra space necessary to stop it looking strange
    private static final String SUB_TITLE = "<html><i>Please select your role below </i><html>";

    public HomePage() {

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
}
