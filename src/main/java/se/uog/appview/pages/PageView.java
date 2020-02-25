package se.uog.appview.pages;

import javax.swing.JPanel;

/**
 * An abstract class which extends JPanel. Needs to guarantee it has a name so it can be consumed by
 * the AppView.addPage() method.
 */
public abstract class PageView extends JPanel {

    /**
     * Returns a name which is consumed by the menu / cardLayout in the main AppView This method
     * must be implemented in the classes.
     */
    public abstract String getName();
}
