import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Movie theMovie = Movie.getMovie("Science", "Star Wars");
//        theMovie.watchMovie();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a type (A for Adventure, C for Comedy, S for Science Fiction or Q to Quit");
            String type = scanner.nextLine();
            if("Qq".contains(type)) {
                break;
            }

            System.out.println("Enter movie title");
            String title = scanner.nextLine();
            Movie movie = Movie.getMovie(type, title);
            movie.watchMovie();
        }
    }
}