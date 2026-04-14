import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public abstract class Game {
    public String getGameName() {
        return getClass().getSimpleName();
    }

    public abstract void play(); // must be able to play a game

    public abstract String getScore(); // get a score - if there is no "score" you can return return "N/A" or something.

    public abstract boolean isHighScore(String score, String currentHighScore);

    public boolean shouldQuitPortal() {
        return false;
    }

    public void writeHighScore(File f) {
        String score = getScore();
        String highScore = getBestScore(f);
        System.out.println("Thanks for playing! Your score was " + score);

        if (isHighScore(score, highScore)) {
            System.out.println("You got a new high score, which beats the previous high score of " + highScore);
            try {
                Scanner myReader = new Scanner(f);
                StringBuilder newFile = new StringBuilder();
                while (myReader.hasNextLine()) {
                    String line = myReader.nextLine();
                    String[] data = line.split(",");
                    if (!data[0].equals(getGameName())) {
                        newFile.append(line).append("\n");
                    }
                }
                myReader.close();
                newFile.append(getGameName()).append(",").append(score).append("\n");

                FileWriter myWriter = new FileWriter(f);
                myWriter.write(newFile.toString());
                myWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getBestScore(File highscoreFile) {
        String highScore = null;
        try {
            Scanner myReader = new Scanner(highscoreFile);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(",");
                if (data.length != 2 || !data[0].equals(getGameName())) {
                    continue;
                }
                highScore = data[1];
                break;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            try {
                highscoreFile.createNewFile();
            } catch (IOException ioException) {
                System.err.println("Could not create file " + highscoreFile.getName() + " in " + highscoreFile.getPath());
            }
        }
        return highScore;
    }
}
