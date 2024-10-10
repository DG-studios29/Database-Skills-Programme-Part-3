import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayLists {
    public static void main(String[] args) {
        // Create albums1 and add 5 albums
        List<Album> albums1 = new ArrayList<>();
        albums1.add(new Album("Album1", 10, "Artist1"));
        albums1.add(new Album("Album2", 8, "Artist2"));
        albums1.add(new Album("Album3", 12, "Artist3"));
        albums1.add(new Album("Album4", 15, "Artist4"));
        albums1.add(new Album("Album5", 9, "Artist5"));

        // Print albums1
        System.out.println("Albums1:");
        for (Album album : albums1) {
            System.out.println(album);
        }

        // Sort albums1 by numberOfSongs
        Collections.sort(albums1, (a1, a2) -> Integer.compare(a1.getNumberOfSongs(), a2.getNumberOfSongs()));

        // Print sorted albums1
        System.out.println("\nAlbums1 sorted by numberOfSongs:");
        for (Album album : albums1) {
            System.out.println(album);
        }

        // Swap elements at positions 1 and 2 in albums1
        Collections.swap(albums1, 1, 2);

        // Print albums1 after swapping
        System.out.println("\nAlbums1 after swapping positions 1 and 2:");
        for (Album album : albums1) {
            System.out.println(album);
        }

        // Create albums2 and add 5 albums
        List<Album> albums2 = new ArrayList<>();
        albums2.add(new Album("Album6", 11, "Artist6"));
        albums2.add(new Album("Album7", 14, "Artist7"));
        albums2.add(new Album("Album8", 13, "Artist8"));
        albums2.add(new Album("Album9", 7, "Artist9"));
        albums2.add(new Album("Album10", 10, "Artist10"));

        // Print albums2
        System.out.println("\nAlbums2:");
        for (Album album : albums2) {
            System.out.println(album);
        }

        // Copy all albums from albums1 to albums2
        albums2.addAll(albums1);

        // Add two new albums to albums2
        albums2.add(new Album("Dark Side of the Moon", 9, "Pink Floyd"));
        albums2.add(new Album("Oops!... I Did It Again", 16, "Britney Spears"));

        // Sort albums2 alphabetically by album name
        Collections.sort(albums2, (a1, a2) -> a1.toString().compareTo(a2.toString()));

        // Print albums2 after sorting
        System.out.println("\nAlbums2 sorted alphabetically by album name:");
        for (Album album : albums2) {
            System.out.println(album);
        }

        // Search for "Dark Side of the Moon" in albums2 and print its index
        String searchAlbum = "Dark Side of the Moon";
        int index = -1;
        for (int i = 0; i < albums2.size(); i++) {
            if (albums2.get(i).getAlbumName().equals(searchAlbum)) {
                index = i;
                break;
            }
        }
        System.out.println("\nIndex of \"" + searchAlbum + "\" in albums2: " + index);
    }
}