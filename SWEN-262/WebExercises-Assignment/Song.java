public class Song {

    private String title;
    private Artist artist;

    public Song(String title, Artist artist) {
        this.title = title;
        this.artist = artist;
    }

    @Override
    public String toString() {
        String str = title + " by " + artist;
        return str;
    }
}
