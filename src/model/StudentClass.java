package model;

public class StudentClass {
    private int id;
    private String studentClass_name;
    private String studentClass_desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentClass_name() {
        return studentClass_name;
    }

    public void setStudentClass_name(String studentClass_name) {
        this.studentClass_name = studentClass_name;
    }

    public String getStudentClass_desc() {
        return studentClass_desc;
    }

    public void setStudentClass_desc(String studentClass_desc) {
        this.studentClass_desc = studentClass_desc;
    }

    public StudentClass() {
        super();
    }

    public StudentClass(String studentClass_name, String studentClass_desc) {
        super();
        this.studentClass_name = studentClass_name;
        this.studentClass_desc = studentClass_desc;
    }

    public StudentClass(int id, String studentClass_name, String studentClass_desc) {
        super();
        this.id = id;
        this.studentClass_name = studentClass_name;
        this.studentClass_desc = studentClass_desc;
    }

    @Override
    public String toString() {
        return studentClass_name;
    }
}
