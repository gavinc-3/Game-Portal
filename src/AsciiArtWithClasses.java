import java.util.Random;

public class AsciiArtWithClasses extends Game {
    private static final int WIDTH = Math.max(20, getTerminalWidth() - 1);
    private static final int HEIGHT = 30;
    private static final int FRAME_COUNT = 120;
    private static final Random rand = new Random();
    private int artPiecesShown;
    private boolean quitRequested;

    public static void main(String[] args) throws InterruptedException {
        new AsciiArtWithClasses().play();
    }

    @Override
    public void play() {
        System.out.println("Press q then Enter to quit.");
        char[][] nextRows = new char[HEIGHT][WIDTH];
        for (int i = 0; i < HEIGHT; i++) {
            nextRows[i] = emptyRow();
        }

        ArtImage mouse = new ArtImage(getMouse());
        ArtImage bunny = new ArtImage(getElephant());
        ArtImage fish = new ArtImage(getFish());
        ArtImage butterfly = new ArtImage(getButterfly());
        ArtImage penguin = new ArtImage(getPenguin());

        ArtImage[] images = { mouse, bunny, fish, butterfly, penguin };
        artPiecesShown = 0;
        quitRequested = false;

        for (int frame = 0; frame < FRAME_COUNT; frame++) {
            if (quitRequested()) {
                quitRequested = true;
                break;
            }
            for (int x = 0; x < WIDTH - 20; x += 20) {
                if (rand.nextDouble() < 0.08) {
                    ArtImage art = images[rand.nextInt(images.length)];
                    if (canPlaceImage(nextRows, art, x)) {
                        loadNextRowsWithImage(nextRows, art, x);
                        artPiecesShown++;
                    }
                }
            }
            System.out.println(new String(nextRows[0]));
            shiftRowsUp(nextRows);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    @Override
    public String getScore() {
        return Integer.toString(artPiecesShown);
    }

    @Override
    public boolean isHighScore(String score, String currentHighScore) {
        if (currentHighScore == null) {
            return true;
        }
        return Integer.parseInt(score) > Integer.parseInt(currentHighScore);
    }

    @Override
    public boolean shouldQuitPortal() {
        return quitRequested;
    }

    private static void shiftRowsUp(char[][] nextRows) {
        for (int i = 1; i < nextRows.length; i++) nextRows[i - 1] = nextRows[i];
        nextRows[nextRows.length - 1] = emptyRow();
    }


    private static void loadNextRowsWithImage(char[][] nextRows, ArtImage art, int x) {
        for (int iy = 0; iy < art.height && iy < nextRows.length; iy++) {
            for (int ix = 0; ix < art.width && x + ix < WIDTH; ix++) {
                char c = art.img[iy][ix];
                if (c != ' ') nextRows[iy][x + ix] = c;
            }
        }
    }

    private static char[] emptyRow() {
        char[] row = new char[WIDTH];
        for (int i = 0; i < WIDTH; i++) row[i] = ' ';
        return row;
    }

    private static int getTerminalWidth() {
         String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            return getUnixTerminalWidth();
        } else {
            return 80; // fallback for unknown OS
        }
    }

     private static int getUnixTerminalWidth() {
        try {
            // Try to get terminal size from environment variables first
            String columns = System.getenv("COLUMNS");
            if (columns != null && !columns.isEmpty()) {
                return Integer.parseInt(columns);
            }

            // Fallback to stty command
            ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", "stty size </dev/tty");
            pb.redirectErrorStream(true);
            Process process = pb.start();
            java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream()));
            String output = reader.readLine();
            if (output != null && !output.isEmpty()) {
                String[] parts = output.trim().split(" ");
                return Integer.parseInt(parts[1]); // columns
            }
        } catch (Exception ignored) {
            // Silently ignore errors and fall back to default
        }
        return 80; // fallback
    }

    private static boolean canPlaceImage(char[][] nextRows, ArtImage art, int x) {
        for (int iy = 0; iy < art.height && iy < nextRows.length; iy++) {
            for (int ix = 0; ix < art.width && x + ix < WIDTH; ix++) {
                if (nextRows[iy][x + ix] != ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean quitRequested() {
        return ErrorCheck.shouldQuit();
    }

    private static class ArtImage {
        private final char[][] img;
        private final int height;
        private final int width;

        private ArtImage(String art) {
            String[] lines = art.split("\n");
            height = lines.length;

            int widestLine = 0;
            for (String line : lines) {
                if (line.length() > widestLine) {
                    widestLine = line.length();
                }
            }
            width = widestLine;

            img = new char[height][width];
            for (int row = 0; row < height; row++) {
                for (int col = 0; col < width; col++) {
                    if (col < lines[row].length()) {
                        img[row][col] = lines[row].charAt(col);
                    } else {
                        img[row][col] = ' ';
                    }
                }
            }
        }
    }

    // art


  private static String getElephant() {

    // This ascii was made by Yiannis; credit goes to him 
    return """
   
        ()      |~|    
        /||\\  __ |~| __ 
        /\\  /  \\~~~\\  
    ,----(    - -   )
    /               / 
    / |        (\\  |(  
    ^  \\   /__\\  /\\ |   
        |__|  |__|-"  
      
        """;
}

 private static String getButterfly(){

    // This ascii was made by Catherine 
    return """
     ♥♥        ♥♥  
     ♥  ♥      ♥  ♥ 
     ♥ o ♥    ♥ o ♥ 
      ♥   ♥  ♥   ♥  
       ♥-  ♥♥  -♥   
      ♥   ♥  ♥   ♥  
     ♥ o ♥    ♥ o ♥ 
     ♥  ♥      ♥  ♥ 
      ♥♥        ♥♥  
        """;
 }

private static String getPenguin() {

    // this is Ari's ascii penguin; credit goes to him
    return """
                
         .--.                            
        |o_o |                                      
        |/_/ |                                      
       //   \\ \\                                     
      (|     | )                                   
       /'\\_   _/`\\ 
     \\___)=(___/ AH
    
     """;
}




    private static String getFish() {
        return """                                                                                                               
                    AA                                                                                               
                    A AA                                                                                             
                    A   AA                                                                                           
                   AA    AAA                                                                                         
            ---    A       AA                                                                                        
           ---    AA        AA                                                                                       
          - --    AA          A                                                                                      
         -  -AAAAAA    AAAAAAAA                                                                                      
         - A-A        AA                                                                                             
         -A           A                                                                                              
         BB   ))      A                                                                                              
        B  ►►   ))    A ----                                                                                         
       BB  ►►   )   BB-- --                                                                                          
       B       )   BBB   --                                                                                          
       B       BBBB-----                                                                                             
       BBBBBBBBB            G.C  
            """;
    }

    private static String getMouse() {
        return """
                        @@@@                                                                                         
     @@@@             @@@  @@@                                                                                       
    @@   @@          @  ... @@                                                                                       
     @     @@ -------@@ . .@@                                                                                        
   @  ....  @@       -@----@                                                                                         
   @  .  ..                @)                                                                                        
   @  .                     ))                                                                                       
   @   .                     )                                                                                       
   @    ...    [ ] )) [ ]                                                                                            
    @@         | ►    | ►     )                                                                                      
      @@@ !    L ]  ) L ]                                                                                            
         @!                 WWWWWW                                                                                   
          !      ----        W0W0W                                                                                   
          !!!       --]      -WWW                                                                                    
            1!        L----&&&                                                                                       
             ****     *&&&&                                                                                          
                *-***-*   G.C 
            """;
    }


}

 
    
 
