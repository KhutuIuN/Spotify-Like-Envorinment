import java.util.*;

public class LinkedList {
    Person headP;
    Song headS;

    public void create(String personName) {
        if (searchPerson(personName) != null) {
            System.out.println("This person is already existed!");
            return;
        } else {
            Person new_node = new Person(personName);

            if (headP == null) {
                headP = new Person(personName);
                System.out.println("The person profile named " + "'" + personName + "'" + " is created.");
                return;
            }

            new_node.next = null;

            Person copyOfHead = headP;
            while (copyOfHead.next != null)
                copyOfHead = copyOfHead.next;

            copyOfHead.next = new_node;

        }
        System.out.println("The person profile named " + "'" + personName + "'" + " is created.");
    }// O(n)

    public void addSong(String songName) {
        if (searchSong(songName) != null) {
            System.out.println("This song has already in the list.");
        } else {
            Song new_node = new Song(songName);

            if (headS == null) {
                headS = new Song(songName);
                System.out.println("The song " + "'" + songName + "'" + " is added.");
                return;
            }

            new_node.next = null;

            Song copyOfHead = headS;
            while (copyOfHead.next != null)
                copyOfHead = copyOfHead.next;

            copyOfHead.next = new_node;
            System.out.println("The song " + "'" + songName + "'" + " is added.");
        }

    }// O(n)

    public void deleteSong(String personName, String songName) {
        Person selectedPerson = searchPerson(personName);
        Song temp = selectedPerson.getLikedSongs().headS, prev = null;
        if (temp == null) {
            System.out.println("There is no song in the list.");
            return;
        }
        if (temp.getSongName().equalsIgnoreCase(songName)) {
            selectedPerson.getLikedSongs().headS = temp.next;

            System.out.println("'" + selectedPerson.getPersonName() + "'" + " does not like the song " + "'" + songName + "'" + " anymore.");
            return;
        }
        while (temp != null) {
            if (temp.getSongName().equalsIgnoreCase(songName)) {
                prev.next = temp.next;
                System.out.println("'" + selectedPerson.getPersonName() + "'" + " does not like the song " + "'" + songName + "'" + " anymore.");
                return;
            }
            prev = temp;
            temp = temp.next;
        }
        System.out.println("The song is not in the list. ");

    }

    public void printListAsPersonName() {
        if (headP == null)
            System.out.println("There is no person registered.");
        else {
            Person currentPerson = headP;
            System.out.println("Registered people :");
            int numOfPeople = 1;
            while (currentPerson != null) {
                System.out.println(numOfPeople++ + ". " + currentPerson.getPersonName() + " ");
                currentPerson = currentPerson.next;
            }
        }
    }// O(n)

    public void printListAsSongName() {
        if (headP == null)
            System.out.println("There is no person registered.");
        else {
            Person currentPerson = headP;

            Set<String> allSongs = new HashSet<>();
            System.out.println("The songs that registered people like : ");
            int numOfSongs = 1;
            while (currentPerson != null) {
                Song currentSong = currentPerson.getLikedSongs().headS;
                while (currentSong != null) {
                    allSongs.add(currentSong.getSongName());
                    currentSong = currentSong.next;
                }
                currentPerson = currentPerson.next;
            }
            if (allSongs.isEmpty())
                System.out.println("No song found.");
            else
                for (String s : allSongs)
                    System.out.println(numOfSongs++ + ". " + s);


        }
    }// O(n^2)

    public void printSongsForPerson(String personName) {
        if (searchPerson(personName) == null)
            System.out.println("'" + personName + "'" + " does not exist. First you should create a profile.");
        else {
            Person currentPerson = searchPerson(personName);

            if (currentPerson.getLikedSongs().headS == null)
                System.out.println("There is no song in the list.");
            else {
                Song temp = currentPerson.getLikedSongs().headS;
                int numOfSong = 1;
                System.out.println("The liked songs for " + "'" + currentPerson.getPersonName() + "' :");
                while (temp != null) {
                    System.out.println(numOfSong + ". " + temp.getSongName());
                    numOfSong++;
                    temp = temp.next;
                }
            }
        }
    }// O(n)

    public void recSongs() {
        ArrayList<String> listOfAllSong = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Person copyOfHead = headP;
        while (copyOfHead != null) {
            Song currentSong = copyOfHead.getLikedSongs().headS;
            while (currentSong != null) {
                listOfAllSong.add(currentSong.getSongName());
                currentSong = currentSong.next;
            }
            copyOfHead = copyOfHead.next;
        }

        for (int i = 0; i < listOfAllSong.size(); i++) {
            int counter = 1;
            if (!listOfAllSong.get(i).equals(" ")) {
                for (int j = i + 1; j < listOfAllSong.size(); j++) {
                    if (listOfAllSong.get(i).equalsIgnoreCase(listOfAllSong.get(j)))
                        counter++;
                }
                map.put(listOfAllSong.get(i), counter);
                Collections.replaceAll(listOfAllSong, listOfAllSong.get(i), " ");
            }
        }
        if (map.isEmpty())
            System.out.println("There is no song in the environment.");
        else if (map.size() == 1) {
            for (Map.Entry<String, Integer> iterator : map.entrySet())
                System.out.println("The song " + "'" + iterator.getKey() + "'" + " is liked from " + iterator.getValue() + " people.");

        } else if (map.size() == 2) {
            String firstKey = "";
            int max = 0;
            for (Map.Entry<String, Integer> iterator : map.entrySet()) {
                if (iterator.getValue() > max) {
                    firstKey = iterator.getKey();
                    max = iterator.getValue();
                }
            }
            System.out.println("The song " + "'" + firstKey + "'" + " is liked from " + max + " people.");
            map.remove(firstKey);
            for (Map.Entry<String, Integer> iterator : map.entrySet())
                System.out.println("The song " + "'" + iterator.getKey() + "'" + " is liked from " + iterator.getValue() + " people.");

        } else {
            int max = 0;
            int counter = 0;
            String key = "";
            while (counter < 3) {

                for (Map.Entry<String, Integer> iterator : map.entrySet()) {
                    if (iterator.getValue() > max) {
                        max = iterator.getValue();
                        key = iterator.getKey();
                    }
                }
                System.out.println("The song " + "'" + key + "'" + " is liked from " + max + " people.");
                map.remove(key);
                max = 0;
                counter++;

            }
        }


    }// O(n^2)

    public Person searchPerson(String name) {
        Person temp = headP;
        while (temp != null) {
            if (temp.getPersonName().equalsIgnoreCase(name)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }// O(n)

    public Song searchSong(String name) {
        Song temp = headS;
        while (temp != null) {
            if (temp.getSongName().equalsIgnoreCase(name)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }// O(n)
}

