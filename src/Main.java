import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String name;

        LinkedList list = new LinkedList();

        while (true) {
            System.out.println("C <Name> ---> create a person profile\nS <Name> <Song> ---> add a song for a person profile or create a person" +
                    " profile and then add a song\nE <Name> <Song> ---> to delete a son for a profile\nL <Name> --->  Lists the songs of the person" +
                    "\nN ---> List all name of registered people\nM ---> List all the songs that liked by anyone\nR ---> Recommends the most popular 3 different songs\nStop ---> Exit the program");
            String process = input.nextLine().toLowerCase();
            String[] listOfInput = (process.toLowerCase().split(" "));

            switch (listOfInput[0]) {
                case "c":
                    if (listOfInput.length == 2)
                        list.create(listOfInput[1]);
                    else
                        System.out.println("Please enter a name.");
                    break;
                case "s":
                    if (listOfInput.length >= 3) {
                        String song = "";
                        if (list.searchPerson(listOfInput[1]) != null) {
                            for (int j = 2; j < listOfInput.length; j++) {
                                song += listOfInput[j] + " ";
                            }
                            Person person = list.searchPerson(listOfInput[1]);
                            person.getLikedSong().addSong(song.trim());

                        } else {
                            System.out.println("First you should create a profile.");
                        }
                    } else
                        System.out.println("Missing input. Please check the guidance.");
                    break;
                case "e":
                    if (listOfInput.length >= 3) {
                        name = listOfInput[1];
                        String likedSong = "";
                        for (int j = 2; j < listOfInput.length; j++) {
                            likedSong += listOfInput[j] + " ";
                        }

                        list.deleteSong(name, likedSong.trim());
                    } else
                        System.out.println("Missing input. Please check the guidance.");
                    break;
                case "l":
                    if (listOfInput.length == 1)
                        System.out.println("You should enter a name.");
                    else {
                        name = listOfInput[1];
                        list.printSong(name);
                    }
                    break;
                case "n":
                    if (listOfInput.length == 1)
                        list.printListAsPersonName();
                    else
                        System.out.println("Invalid input. Please check the guidance. ");
                    break;
                case "m":
                    if (listOfInput.length == 1)
                        list.printListAsSong();
                    else
                        System.out.println("Invalid input. Please check the guidance. ");
                    break;
                case "r":
                    if (listOfInput.length == 1) {
                        System.out.println("Recommended 3 songs:");
                        list.recSongs();
                    } else
                        System.out.println("Invalid input. Please check the guidance. ");
                    break;
                case "stop":
                    System.exit(0);
                default:
                    System.out.println("Invalid input. Please check the guidance. ");
            }


        }
    }
}