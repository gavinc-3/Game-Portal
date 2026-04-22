import processing.core.PApplet;
import java.util.HashMap;
import java.util.List;
import processing.core.PImage;


public class App extends PApplet {


    ProcessingGameWrapper wrapper;
    ExplodingKittens game;

    HashMap<String, PImage> cardImages = new HashMap<>();
    PImage stackImage;
    boolean futurePrinted = false;
    Card hoveredCatCard = null;

    boolean aiWaiting = false;
    int aiStartTime = 0;
    int aiDelay = 0;

    final int GameOverState = 2;
    final int Startstate = 0;
    final int gamestate = 1;

    int currentState = Startstate;

    PImage startScreenImg;

    java.util.ArrayList<String> gameLog = new java.util.ArrayList<>();
    java.util.ArrayList<Integer> logTimers = new java.util.ArrayList<>();

    final int FADE_TIME = 3000;


    @Override
    public void settings() {
        size(1000, 1000);
    }

     public App(ProcessingGameWrapper wrapper) {
        this.wrapper = wrapper;
    }

    @Override
    public void setup() {
        String path = "src/Graphics/";

        stackImage = loadImage(path + "Background.jpg");
        startScreenImg = loadImage(path + "Startscreen.jpg");

        cardImages.put("Skip", loadImage(path + "Skip.jpg"));
        cardImages.put("Explode", loadImage(path + "Explode.jpg"));
        cardImages.put("Defuse", loadImage(path + "Defuse.jpg"));
        cardImages.put("Shuffle", loadImage(path + "Shuffle_.jpg"));
        cardImages.put("SeeFuture", loadImage(path + "SeeFuture_.jpg"));
        cardImages.put("Nope", loadImage(path + "Nope.jpg"));
        cardImages.put("Favor", loadImage(path + "Favor.jpg"));
        cardImages.put("Attack", loadImage(path + "Attack.jpg"));

        cardImages.put("Tacocat", loadImage(path + "Tacocat_.jpg"));
        cardImages.put("HairyPotato", loadImage(path + "HairyPotato_.jpg"));
        cardImages.put("Cattermelon", loadImage(path + "Cattermelon_.jpg"));
        cardImages.put("BeardCat", loadImage(path + "Beardcat_.jpg"));
        cardImages.put("RainbowCat", loadImage(path + "Rainbowcat_.jpg"));

        for (String key : cardImages.keySet()) {
            if (cardImages.get(key) == null) {
                println("Still missing image for: " + key);
            }
        }

        game = new ExplodingKittens(cardImages, gameLog, logTimers, this);
        game.initializeGame();
        game.futurePreview.clear();

        aiWaiting = false;
        aiStartTime = 0;
        aiDelay = 0;
    }

    @Override
    public void draw() {
        background(40, 45, 50);

        if (currentState == GameOverState) {
            drawGameOverScreen();
            drawGameLog();
            return;
        }

        if (currentState == Startstate) {
            if (startScreenImg != null) {
                image(startScreenImg, 0, 0, width, height);
            } else {
                textAlign(CENTER, CENTER);
                textSize(32);
                text("Exploding Kittens\nClick to Start", width / 2, height / 2);
            }
            drawGameLog();
            return;
        }

        if (game.actionPending && frameCount % 120 == 0) {
            game.resolvePendingAction();
        }

        if (game.isGameOver()) {
            aiWaiting = false;
            currentState = GameOverState;
            return;
        }

        displayGameInfo();
        drawHand(game.playerOneHand, height - 350, "Your Hand", false);
        drawStacks();
        drawCenterDecks();
        drawPlayPile();

        if (!game.futurePreview.isEmpty()) {
            drawFuturePreview();
        }

        handleAITurn();
        drawGameLog();
    }

    private void handleAITurn() {
        if (game.currentPlayer == 1) return;

        boolean aiCanAct = !game.actionPending && game.futurePreview.isEmpty();

        if (!aiCanAct) {
            aiWaiting = false;
            return;
        }

        if (!aiWaiting) {
            aiWaiting = true;
            aiStartTime = millis();
            aiDelay = (int) random(2000, 4000);
        }

        if (millis() - aiStartTime >= aiDelay) {
            takeAITurn(game.currentPlayer);
            aiWaiting = false;
        }
    }

    @Override
    public void mousePressed() {
        if (currentState == GameOverState) {
            restartGame();
            return;
        }

        if (currentState == Startstate) {
            currentState = gamestate;
            return;
        }

        if (game.currentPlayer != 1 || game.actionPending) return;

        float cardW = 100;
        float cardH = 150;
        float deckX = width / 2f - cardW - 20;
        float deckY = height / 2f - cardH / 2f;

        if (mouseX >= deckX && mouseX <= deckX + cardW &&
            mouseY >= deckY && mouseY <= deckY + cardH) {

            game.drawCard(game.playerOneHand);
            game.nextTurn();
            return;
        }

        for (int i = game.playerOneHand.size() - 1; i >= 0; i--) {
            Card c = game.playerOneHand.get(i);

            if (c.isMouseOver(mouseX, mouseY)) {
                boolean played = game.playCard(c, game.playerOneHand);

                if (played && !game.actionPending) {
                    game.nextTurn();
                }
                return;
            }
        }
    }

    public void restartGame() {
        gameLog.clear();
        logTimers.clear();

        game = new ExplodingKittens(cardImages, gameLog, logTimers, this);
        game.initializeGame();

        currentState = gamestate;
    }

    public void displayGameInfo() {
        fill(255);
        textSize(20);
        text("Player " + game.currentPlayer + "'s Turn", 20, 20);
    }

    public void drawHand(List<Card> hand, float y, String label, boolean shrink) {
        float startX = 50;
        float spacing = 110;

        for (int i = 0; i < hand.size(); i++) {
            Card c = hand.get(i);
            float x = startX + i * spacing;

            c.hovered = c.isMouseOver(mouseX, mouseY);
            c.setPosition(x, y, 100, 150);
            c.draw(this);
        }
    }

    public void drawStacks() {}
    public void drawCenterDecks() {}
    public void drawPlayPile() {}
    public void drawFuturePreview() {}
    public void drawGameLog() {}

    public void drawGameOverScreen() {
        background(20, 0, 0);
        fill(255);
        textAlign(CENTER, CENTER);
        textSize(40);
        text("Game Over", width / 2, height / 2);
    }

    public void takeAITurn(int player) {
        List<Card> hand = null;
        if (player == 2) {
            hand = game.playerTwoHand;
        } else if (player == 3) {
            hand = game.playerThreeHand;
        } else if (player == 4) {
            hand = game.playerFourHand;
        }

        if (hand == null || hand.isEmpty()) return;

        game.drawCard(hand);
        game.nextTurn();
    }

@Override
    public void exit() {
        super.exit();
    }

    @Override
    public void exitActual() {
        // Do nothing to prevent JVM exit for GamePortal
    }


}
