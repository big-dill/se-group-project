package se.uog.swing;

public class TeacherTableConfiguration implements TableModelConfiguration<Teacher> {

    final static int NAME_COLUMN = 0;
    final static int ISCOOL_COLUMN = 1;
    final static int AGE_COLUMN = 2;

    String[] columnNames = new String[] {"Name", "Is Cool?", "Age"};
    Class<?>[] columnClasses = new Class<?>[] {String.class, Boolean.class, Integer.class};

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Teacher getDefaultElement() {
        return new Teacher("", true, 10);
    }

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public Class<?>[] getColumnClasses() {
        return columnClasses;
    }

    @Override
    public boolean isColumnEditable(int columnIndex) {
        return true;
    }

    @Override
    public Object columnGetter(Teacher element, int columnIndex) {
        switch (columnIndex) {
            case NAME_COLUMN:
                return element.getName();

            case ISCOOL_COLUMN:
                return element.isCool();

            case AGE_COLUMN:
                return element.getAge();

            default:
                return null;
        }
    }

    @Override
    public void columnSetter(Teacher element, Object value, int columnIndex) {
        switch (columnIndex) {
            case NAME_COLUMN:
                element.setName((String) value);
                return;

            case ISCOOL_COLUMN:
                element.setCool((Boolean) value);
                return;

            case AGE_COLUMN:
                element.setAge((Integer) value);
                return;
        }
    }
}
