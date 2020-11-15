package kz.edu.astanait;

public class News extends template{
    private String text;
    public News(String[] list){
        this.id = list[0];
        this.name = list[1];
        this.date = list[2];
        this.text = list[3];
        this.moderator = list[4];
    }

    public News(String id, String name, String date, String text){
        this.id = id;
        this.name = name;
        this.date = date;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
