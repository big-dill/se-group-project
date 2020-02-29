package se.uog;

import java.util.UUID;

public abstract class IDReferenced {

    private String id;

    public IDReferenced() {
        id = UUID.randomUUID().toString();
    }

    final public String getID() {
        return id;
    }
}
