package se.uog.appview.pages;

import java.util.UUID;
import javax.swing.JPanel;

public abstract class PageView extends JPanel {

    String pageName = "";
    String id = "";

    public PageView() {
        id = UUID.randomUUID().toString();
    }

    public void setName(String pageName) {
        this.pageName = pageName;
    }

    final public String getPageID() {
        return String.format("%s:%s", pageName, id);
    }
}
