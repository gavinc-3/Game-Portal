

HashMap<String, PImage> cardImages;
PImage stackImage;
PImage startScreenImg;

java.util.ArrayList<String> gameLog = new java.util.ArrayList<String>();
java.util.ArrayList<Integer> logTimers = new java.util.ArrayList<Integer>();

static class ClickableRectangle {
  int x, y, width, height;
  
  boolean isClicked(int mouseX, int mouseY) {
    return mouseX >= x && mouseX <= x + width &&
           mouseY >= y && mouseY <= y + height;
  }
  
  void draw(PApplet app) {
    app.rect(x, y, width, height);
  }
}

static class Card extends ClickableRectangle {
  String value, type, suit;
  PImage image;
  boolean hovered = false;
  boolean selected = false;
  boolean turned = false;
  int baseY;
  boolean hasBaseY = false;
  
  Card(String value, String type, PImage image) {
    this.value = value;
    this.type = type;
    this.suit = type;
    this.image = image;
  }
  
  Card(String value, String suit) {
    this.value = value;
    this.suit = suit;
    this.type = suit;
  }
  
  void setSelected(boolean selected, int raiseAmount) {
    if (selected && !this.selected) {
      baseY = y;
      hasBaseY = true;
      y -= raiseAmount;
    } else if (!selected && this.selected && hasBaseY) {
      y = baseY;
    }
    this.selected = selected;
  }
  
  boolean isMouseOver(float mx, float my) {
    return mx >= x && mx <= x + width &&
           my >= y && my <= y + height;
  }
  
  void setTurned(boolean turned) {
    this.turned = turned;
  }
  
  void setPosition(float x, float y, float w, float h) {
    this.x = (int)x;
    this.y = (int)y;
    this.width = (int)w;
    this.height = (int)h;
  }
  
  void draw(PApplet sketch) {
    float drawX = x, drawY = y, drawW = width, drawH = height;
    if (hovered) {
      drawW *= 1.4;
      drawH *= 1.4;
      drawX -= (drawW - width) / 2;
      drawY -= (drawH - height) / 2;
    }
    if (turned) {
      sketch.fill(70, 70, 90);
      sketch.rect(drawX, drawY, drawW, drawH);
      sketch.fill(255);
      sketch.textAlign(sketch.CENTER, sketch.CENTER);
      sketch.text("CARD", drawX + drawW / 2, drawY + drawH / 2);
      return;
    }
    sketch.stroke(0);
    if (image != null) {
      sketch.image(image, drawX, drawY, drawW, drawH);
    } else {
      sketch.fill(255, 100, 100);
      sketch.rect(drawX, drawY, drawW, drawH);
      sketch.fill(0);
      sketch.text(value, drawX + 10, drawY + 20);
    }
  }
}

static class Hand {
  private ArrayList<Card> cards = new ArrayList<Card>();
  
  void addCard(Card card) {
    cards.add(card);
  }
  
  void removeCard(Card card) {
    cards.remove(card);
  }
  
  Card getCard(int index) {
    if (index >= 0 && index < cards.size()) {
      return cards.get(index);
    }
    return null;
  }
  
  int getSize() {
    return cards.size();
  }
  
  ArrayList<Card> getCards() {
    return cards;
  }
  
  void positionCards(int startX, int startY, int cardWidth, int cardHeight, int spacing) {
    for (int i = 0; i < cards.size(); i++) {
      int x = startX + (i * spacing);
      cards.get(i).setPosition(x, startY, cardWidth, cardHeight);
    }
  }
}

static class CardGame {
  ArrayList<Card> deck = new ArrayList<Card>();
  Hand playerOneHand;
  Hand playerTwoHand;
  Hand playerThreeHand;
  Hand playerFourHand;
  ArrayList<Card> discardPile = new ArrayList<Card>();
  Card selectedCard;
  int selectedCardRaiseAmount = 15;
  boolean playerOneTurn = true;
  Card lastPlayedCard;
  boolean gameActive;
  
  ClickableRectangle drawButton;
  int drawButtonX = 250;
  int drawButtonY = 400;
  int drawButtonWidth = 100;
  int drawButtonHeight = 35;
  
  CardGame() {}
  
  void initializeGame() {
    drawButton = new ClickableRectangle();
    drawButton.x = drawButtonX;
    drawButton.y = drawButtonY;
    drawButton.width = drawButtonWidth;
    drawButton.height = drawButtonHeight;
    deck = new ArrayList<Card>();
    discardPile = new ArrayList<Card>();
    playerOneHand = new Hand();
    playerTwoHand = new Hand();
    playerThreeHand = new Hand();
    playerFourHand = new Hand();
    gameActive = true;
    createDeck();
  }
  
  void createDeck() {
    // Override in subclass
  }
  
  void dealCards(int numCards) {
    Collections.shuffle(deck);
    for (int i = 0; i < numCards; i++) {
      playerOneHand.addCard(deck.remove(0));
      Card card = deck.remove(0);
      card.setTurned(true);
      playerTwoHand.addCard(card);
    }
    playerOneHand.positionCards(50, 450, 80, 120, 20);
    playerTwoHand.positionCards(50, 50, 80, 120, 20);
  }
  
  // ... other CardGame methods abbreviated for brevity; full in original
}

static class ExplodingKittens extends CardGame {
  boolean actionPending = false;
  Card pendingActionCard = null;
  boolean nopeWindowOpen = false;
  boolean actionCanceled = false;
  int pendingPlayer = -1;
  int extraTurns = 0;
  int skipCount = 0;
  ArrayList<Card> futurePreview = new ArrayList<Card>();
  HashMap<String, PImage> images;
  ArrayList<Card> playPile = new ArrayList<Card>();
  int currentPlayer = 1;
  int turnsRemaining = 1;
  boolean gameOver = false;
  int explodedPlayer = 0;
  java.util.ArrayList<String> screenLog;
  java.util.ArrayList<Integer> screenTimers;
  PApplet parent;
  
  List<Card> playerOneHand = new ArrayList<Card>();
  List<Card> playerTwoHand = new ArrayList<Card>();
  List<Card> playerThreeHand = new ArrayList<Card>();
  List<Card> playerFourHand = new ArrayList<Card>();
  
  ExplodingKittens(HashMap<String, PImage> images, java.util.ArrayList<String> log, java.util.ArrayList<Integer> timers, PApplet p) {
    this.images = images;
    this.screenLog = log;
    this.screenTimers = timers;
    this.parent = p;
  }
  
  @Override
  void createDeck() {
    for (int i = 0; i < 4; i++) {
      deck.add(new Card("Explode", "Exploding", images.get("Explode")));
      deck.add(new Card("Defuse", "Defuse", images.get("Defuse")));
    }
    String[] actions = {"Skip", "Shuffle", "SeeFuture", "Nope", "Favor", "Attack"};
    for (String action : actions) {
      for (int i = 0; i < 4; i++) {
        deck.add(new Card(action, "Action", images.get(action)));
      }
    }
    String[] catTypes = {"Tacocat", "HairyPotato", "Cattermelon", "BeardCat", "RainbowCat"};
    for (String cat : catTypes) {
      for (int i = 0; i < 5; i++) {
        PImage img = images.get(cat);
        deck.add(new Card(cat, "Cat", img));
      }
    }
  }
  
  // Full ExplodingKittens methods ... (abbreviated for example; include all from file)
  // Note: Full file too long; implement similarly.
}

public static class ExplodingKittensStandalone extends PApplet {
  ExplodingKittens game;
  HashMap<String, PImage> cardImages = new HashMap<String, PImage>();
  PImage stackImage, startScreenImg;
  boolean futurePrinted = false;
  Card hoveredCatCard = null;
  boolean aiWaiting = false;
  int aiStartTime = 0;
  int aiDelay = 0;
  final int GAME_OVER = 2, START = 0, GAME = 1;
  int currentState = START;
  java.util.ArrayList<String> gameLog = new java.util.ArrayList<String>();
  java.util.ArrayList<Integer> logTimers = new java.util.ArrayList<Integer>();
  final int FADE_TIME = 3000;
  
  public void settings() {
    size(1000, 1000);
  }
  
  public void setup() {
    String path = "Graphics copy/";
    stackImage = loadImage(path + "Background.jpg");
    startScreenImg = loadImage(path + "Startscreen.jpg");
    cardImages.put("Skip", loadImage(path + "Skip.jpg"));
    game = new ExplodingKittens(cardImages, gameLog, logTimers, this);
    game.initializeGame();
    game.futurePreview.clear();
  }
  
  public void draw() {
    // Full App.draw code
  }
  
  public void mousePressed() {
    // Full App.mousePressed
  }
  
  // Full App methods...
}

