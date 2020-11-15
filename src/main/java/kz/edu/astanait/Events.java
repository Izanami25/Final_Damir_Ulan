package kz.edu.astanait;

public class Events extends template{
    public Events(String[] list) {
        this.id = list[0];
        this.name = list[1];
        this.date = list[2];
        this.moderator = list[3];
    }
    public Events(String id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }
}
