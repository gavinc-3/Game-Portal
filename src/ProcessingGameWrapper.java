import java.io.File;
import processing.core.PApplet;

public class ProcessingGameWrapper extends Game {

    private final Object lock = new Object();
    private boolean quitPortal = false;

    @Override
    public String getGameName() {
        return "Exploding Kittens";
    }

    @Override
    public void play() {
        App app = new App(this);

        String[] args = {"App"};
        PApplet.runSketch(args, app);

        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void signalGameOver(boolean quitPortal) {
        this.quitPortal = quitPortal;

        synchronized (lock) {
            lock.notify();
        }
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
    public boolean isHighScore(String name, String score) {
        return false;
    }
}