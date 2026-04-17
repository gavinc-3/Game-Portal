import java.io.File;
import java.util.concurrent.CountDownLatch;
import processing.core.PApplet;

public class ProcessingGameWrapper extends Game {

    private CountDownLatch latch;
    private boolean quitPortal = false;

    @Override
    public String getGameName() {
        return "Exploding Kittens (Processing)";
    }

    @Override
    public void play() {
        App app = new App(this);
        String[] args = {"Exploding Kittens"};
        PApplet.runSketch(args, app);
        latch = new CountDownLatch(1);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void signalGameOver(boolean quitPortal) {
        this.quitPortal = quitPortal;
        if (latch != null) latch.countDown();
    }

    @Override
    public void writeHighScore(File file) {
        // optional
    }

    @Override
    public boolean shouldQuitPortal() {
        return quitPortal;
    }

    @Override
    public String getScore() { 
        return "0";
    }

    @Override
    public boolean isHighScore(String score, String currentHighScore) {
        return false;
    }
}
