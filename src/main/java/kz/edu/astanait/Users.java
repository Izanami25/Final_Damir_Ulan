package kz.edu.astanait;

public class Users extends user{
    public Users(String[] list){
        this.id = list[0];
        this.name = list[1];
        this.surname = list[2];
        this.email = list[3];
        this.username = list[4];
        this.password = list[5];
        this.major = list[6];
        this.group = list[7];
        this.year = list[8];
        this.role = list[9];
    }
    public Users(String id, String name, String surname, String email, String username, String password, String role, String major, String group, String year){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.major = major;
        this.group = group;
        this.year = year;
    }
}
