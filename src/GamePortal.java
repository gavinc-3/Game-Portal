import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GamePortal {
    private static final List<Game> GAMES = new ArrayList<>();
    private static final File HIGHSCORE_FILE = new File("Highscore.csv");

    public static void main(String[] args) {
        loadGames();

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

    private static void loadGames() {
        GAMES.clear();
        GAMES.add(new AsciiArtWithClasses());
        GAMES.add(new Quiz());
        GAMES.add(new Election());
        GAMES.add(new ProcessingGameWrapper()); 
    }

    private static void printGameChoices() {
        int n = 1;
        for (Game game : GAMES) {
            System.out.println("[" + (n++) + "]: " + game.getGameName());
        }
    }

    private static Game getGameChoice() {
        int choice = ErrorCheck.getInt();
        if (choice == ErrorCheck.QUIT_CODE) {
            return null;
        }

        while (choice < 1 || choice > GAMES.size()) {
            System.out.println("We don't have this number. Try again.");
            choice = ErrorCheck.getInt();
            if (choice == ErrorCheck.QUIT_CODE) {
                return null;
            }
        }

        return GAMES.get(choice - 1);
    }
}
