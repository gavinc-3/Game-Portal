import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GamePortal {
    private static final List<Game> games = new ArrayList<>();
    private static final File HIGHSCORE_FILE = new File("Highscore.csv");

    public static void main(String[] args) {
        loadgames();

        while (true) {
            System.out.println("Which game would you like to play? Enter q to quit.");
            printGameChoices();
            Game game = getGameChoice();
            if (game == null) {
                System.out.println("Thanks for playing!");
                return;
            }
            System.out.println("You're playing " + game.getGameName());

            game.play();
            game.writeHighScore(HIGHSCORE_FILE);
            if (game.shouldQuitPortal()) {
                System.out.println("Thanks for playing!");
                return;
            }
        }
    }

    private static void loadgames() {
        games.clear();
        games.add(new AsciiArtWithClasses());
        games.add(new Quiz());
        games.add(new Election());
        games.add(new ProcessingGameWrapper()); 
    }

    private static void printGameChoices() {
        int n = 1;
        for (Game game : games) {
            System.out.println("[" + (n++) + "]: " + game.getGameName());
        }
    }

    private static Game getGameChoice() {
        int choice = ErrorCheck.getInt();
        if (choice == ErrorCheck.QUIT_CODE) {
            return null;
        }

        while (choice < 1 || choice > games.size()) {
            System.out.println("We don't have this number. Try again.");
            choice = ErrorCheck.getInt();
            if (choice == ErrorCheck.QUIT_CODE) {
                return null;
            }
        }

        return games.get(choice - 1);
    }
}
