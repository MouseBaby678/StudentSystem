package model;

public class Student {
    private int id;
    private String studentId;
    private String name;
    private String sex;
    private String year;
    private String month;
    private String day;
    private String politics_status;
    private String address;
    private String phone_num;
    private String dormitory_num;
    private Integer secondaryId;
    private String secondary_name;
    private Integer classId;
    private String class_name;

    public Student() {
        super();
    }

    public Student(int id, String studentId, String name, String sex, String year, String month, String day, String politics_status, String address, String phone_num, String dormitory_num, Integer secondaryId,  Integer classId) {
        super();
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.sex = sex;
        this.year = year;
        this.month = month;
        this.day = day;
        this.politics_status = politics_status;
        this.address = address;
        this.phone_num = phone_num;
        this.dormitory_num = dormitory_num;
        this.secondaryId = secondaryId;
        this.classId = classId;
    }

    public Student(String studentId, String name) {
        super();
        this.studentId = studentId;
        this.name = name;
    }

    public Student(String studentId, String name, Integer secondaryId, Integer classId) {
        super();
        this.studentId = studentId;
        this.name = name;
        this.secondaryId = secondaryId;
        this.classId = classId;
    }

    public Student(String studentId) {
        this.studentId = studentId;
    }

    public String getSecondary_name() {
        return secondary_name;
    }

    public void setSecondary_name(String secondary_name) {
        this.secondary_name = secondary_name;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Student(String studentId, String name, String sex, String year, String month, String day, String politics_status, String address, String phone_num, String dormitory_num, Integer secondaryId, Integer classId) {
        super();
        this.studentId = studentId;
        this.name = name;
        this.sex = sex;
        this.year = year;
        this.month = month;
        this.day = day;
        this.politics_status = politics_status;
        this.address = address;
        this.phone_num = phone_num;
        this.dormitory_num = dormitory_num;
        this.secondaryId = secondaryId;
        this.classId = classId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getPolitics_status() {
        return politics_status;
    }

    public void setPolitics_status(String politics_status) {
        this.politics_status = politics_status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getDormitory_num() {
        return dormitory_num;
    }

    public void setDormitory_num(String dormitory_num) {
        this.dormitory_num = dormitory_num;
    }

    public Integer getSecondaryId() {
        return secondaryId;
    }

    public void setSecondaryId(Integer secondaryId) {
        this.secondaryId = secondaryId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}
