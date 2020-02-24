package se.uog.appview;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import se.uog.appview.pages.CoursePage;
import se.uog.appview.pages.PageView;
import se.uog.appview.pages.QualificationPage;
import se.uog.appview.pages.TeacherPage;
import se.uog.controller.AppController;
import se.uog.model.AppModel;
import se.uog.model.CourseTableModel;
import se.uog.model.QualificationTableModel;
import se.uog.model.TeacherTableModel;

public class AppView extends JFrame {

    private static final String WINDOW_TITLE = "AppMenu"; // Obvs can be changed later
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);

    private AppMenu menuBar;

    public AppView(AppModel appModel, AppController appController) {
        super();

        this.menuBar = new AppMenu(appController);
        setJMenuBar(menuBar);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(WINDOW_TITLE);

        setSize(600, 400);

        add(mainPanel);
    }

    public void addPage(PageView page, int menuMneumonic) {
        mainPanel.add(page, page.getPageID());
        menuBar.addPage(page.getPageID(), menuMneumonic);
    }

    public void setPage(String pageID) {
        cardLayout.show(mainPanel, pageID);
    }

}
