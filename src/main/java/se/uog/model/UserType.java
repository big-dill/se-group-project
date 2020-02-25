package se.uog.model;

public class UserType {

    private static UserType instance;
    private static UserEnum userEnum;

    private UserType(UserEnum userEnum){
        this.userEnum = userEnum;
    }

    public static UserEnum getUserEnum() {
        return userEnum;
    }

    public static void setUserEnum(UserEnum userEnum) {
        UserType.userEnum = userEnum;
    }

    public static UserType getInstance() {
        if (instance == null) {
            instance = new UserType(UserEnum.UNASSIGNED);
        }
        return instance;
    }

}
