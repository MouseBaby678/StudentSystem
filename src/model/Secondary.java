package model;

public class Secondary {
    private int id;
    private String secondary_name;
    private String secondary_desc;

    public Secondary(String secondary_name, String secondary_desc) {
        super();
        this.secondary_name = secondary_name;
        this.secondary_desc = secondary_desc;
    }

    public Secondary(int id, String secondary_name, String secondary_desc) {
        this.id = id;
        this.secondary_name = secondary_name;
        this.secondary_desc = secondary_desc;
    }

    public Secondary() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSecondary_name() {
        return secondary_name;
    }

    public void setSecondary_name(String secondary_name) {
        this.secondary_name = secondary_name;
    }

    public String getSecondary_desc() {
        return secondary_desc;
    }

    public void setSecondary_desc(String secondary_desc) {
        this.secondary_desc = secondary_desc;
    }

    @Override
    public String toString() {
        return secondary_name;
    }
}
