public class Artist {
    private String name;

    /**
     * Represents an artist -- designed to be able to add more features to an artist in future developments
     * @param name
     */
    public Artist (String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
