package se.uog.model;

public class UserType {

    private UserEnum userEnum;
    private static UserType instance;

    private UserType(UserEnum userEnum){
        this.userEnum = userEnum;
    }

    public UserEnum getUserEnum() {
        return userEnum;
    }

    public void setUserEnum(UserEnum userEnum) {
        this.userEnum = userEnum;
    }

    public static UserType getInstance() {
        if (instance == null) {
            instance = new UserType(UserEnum.UNASSIGNED);
        }
        return instance;
    }


    // Can make singleton?
}
