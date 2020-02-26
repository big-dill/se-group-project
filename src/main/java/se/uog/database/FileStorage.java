package se.uog.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileStorage implements JSONStorageInterface {
    String filename;

    public FileStorage(String filename) {
        this.filename = filename;
    }

    @Override
    public String getJSON() throws IOException {

        return new String(Files.readAllBytes(Paths.get(filename)));
    }

    @Override
    public void storeJSON(String json) throws IOException {
        Files.write(Paths.get(filename), json.getBytes());
    }
}
