package se.uog.appview.pages;

import javax.swing.JPanel;

public abstract class PageView extends JPanel {

    /**
     * Returns a name which is consumed by the menu / cardLayout in the main AppView This method
     * must be implemented in the classes.
     */
    public abstract String getName();
}
