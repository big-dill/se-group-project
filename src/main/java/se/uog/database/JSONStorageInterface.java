package se.uog.database;

/**
 * An interface which DAOs need to have so that our app store / read its
 * serialized / deserialized model to.
 */
public interface JSONStorageInterface {
    public String getJSON() throws Exception;

    public void storeJSON(String json) throws Exception;
}
