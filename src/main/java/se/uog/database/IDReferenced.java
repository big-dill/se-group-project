package se.uog.database;

import java.util.UUID;

/**
 * This abstract class gives classes that extend from it an ID which is employed by the model
 * serializer / deserializer.
 */
public abstract class IDReferenced {

    private String id;

    public IDReferenced() {
        id = UUID.randomUUID().toString();
    }

    final public String getID() {
        return id;
    }
}
