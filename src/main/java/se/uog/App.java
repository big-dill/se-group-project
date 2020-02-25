package se.uog;

import se.uog.controller.AppController;
import se.uog.model.AppModel;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        AppModel appModel = new AppModel();
        new AppController(appModel);
    }

}
