package kz.edu.astanait;

public class resultantTable {
    private String name;
    private String surname;
    private String email;
    private String major;
    private String group;
    private String year;

    public resultantTable(String[] res){
        this.name = res[0];
        this.surname = res[1];
        this.email = res[2];
        this.major = res[3];
        this.group = res[4];
        this.year = res[5];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
