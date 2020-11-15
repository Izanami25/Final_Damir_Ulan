package kz.edu.astanait;

public class Clubs extends template{
    public Clubs(String[] list) {
        this.id = list[0];
        this.name = list[1];
        this.moderator = list[2];
    }
    public Clubs(String id, String name){
        this.id = id;
        this.name = name;
    }
}
