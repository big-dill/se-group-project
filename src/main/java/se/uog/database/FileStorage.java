package se.uog.database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A JSONStorageInterface class which writes and reads to a specified file.
 */
public class FileStorage implements JSONStorageInterface {
    private String filename;

    /**
     * Creates a FileStorage object for reading and writing JSON to disk.
     *
     * @param filename The filename to read/write to
     */
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
