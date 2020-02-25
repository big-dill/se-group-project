package se.uog.appview.pages;

import se.uog.appview.AppMenu;
import se.uog.appview.AppView;
import se.uog.controller.AppController;
import se.uog.model.Course;
import se.uog.model.Teacher;
import se.uog.model.UserEnum;
import se.uog.model.UserType;
import se.uog.table.JObjectTable;
import se.uog.table.ObjectTableModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends PageView {

    private static final String TITLE = "<html><h1><strong><i>Staff Management Application</i></strong></h1><hr></html>";
    private static final String SUB_TITLE = "<html><i>Please select your role below </i><html>"; // extra space necessary to stop it looking strange

    public HomePage(AppView appView){


        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());


        if(UserType.getInstance().getUserEnum() == UserEnum.UNASSIGNED){
            appView.toggleMenuBar();
        }

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
        // DIRTY HACK to test
        director.addActionListener(e -> {
            appView.toggleMenuBar();
            UserType.getInstance().setUserEnum(UserEnum.DIRECTOR);
            appView.setPage("Courses");
        });

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
