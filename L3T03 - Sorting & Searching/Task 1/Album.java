class Album {
    private String albumName;
    private int numberOfSongs;
    private String albumArtist;

    public Album(String albumName, int numberOfSongs, String albumArtist) {
        this.albumName = albumName;
        this.numberOfSongs = numberOfSongs;
        this.albumArtist = albumArtist;
    }

    public String getAlbumName() {
        return albumName;
    }

    public int getNumberOfSongs() {
        return numberOfSongs;
    }

    @Override
    public String toString() {
        return "(" + albumName + ", " + albumArtist + ", " + numberOfSongs + ")";
    }
}
