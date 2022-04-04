package POJO;

public class Song {

    private int number;
    private String title;
    private Artist artist;

    public Song(int number, String title, Artist artist) {
        this.number = number;
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        String str = number + ") " + title + " by " + artist;
        return str;
    }
}
