import java.util.Random;

public class ScrollArt {


    public static void main(String[] args) throws InterruptedException {
        int screenWidth = getTerminalWidth(); 
        int heightterminal = 60;
        char[][] screen = new char[screenWidth][heightterminal];

        Random random = new Random();

        AsciiArt mouseArt = new AsciiArt(getMouse());
        AsciiArt fishArt = new AsciiArt(getFish());

        while (true) {
            for (int x = 0; x < screen.length; x++) {
                for (int y = 0; y < screen[x].length; y++) {
                    screen[x][y] = ' ';
                }
            }
            char[][] mouseImg = mouseArt.getImg();
            char[][] fishImg = fishArt.getImg();

            int mousew = mouseImg.length; 
            int mouseh = mouseImg[0].length;
            int fishw = fishImg.length;
            int fishH = fishImg[0].length;

            int mousex = random.nextInt(screenWidth - mousew);
            int mousey = random.nextInt(heightterminal - mouseh);
            int fishx = random.nextInt(screenWidth - fishw);
            int fishy = random.nextInt(heightterminal - fishH);

            // Loop through the screen and place the mouse and fish
            for (int x = 0; x < mousew; x++) {
                for (int y = 0; y < mouseh; y++) {
                    if (mouseImg[x][y] != ' ') {
                        screen[mousex + x][mousey + y] = mouseImg[x][y];
                    }
                }
            }

            for (int x = 0; x < fishw; x++) {
                for (int y = 0; y < fishH; y++) {
                    if (fishImg[x][y] != ' ') {
                        screen[fishx + x][fishy + y] = fishImg[x][y];
                    }
                }
            }

            for (int y = 0; y < screen[0].length; y++) {
                for (int x = 0; x < screen.length; x++) {
                    System.out.print(screen[x][y]);
                }
                System.out.println();
            }

            Thread.sleep(1000); // Pause before the next iteration
        }
    }
    
static char[][] getMouse() {
        
    char[][] mouse = new char[34][19]; 

    
            for (int y = 0; y < 19; y++) {
                for (int x = 0; x < 34; x++) {
                    mouse[x][y] = ' '; 
                }
            }

            mouse[5][3] = '@';
            mouse[6][3] = '@';
            mouse[7][3] = '@';
            mouse[8][3] = '@';
            mouse[22][3] = '@';
            mouse[23][3] = '@';
            mouse[24][3] = '@';
            mouse[27][3] = '@';
            mouse[28][3] = '@';
            mouse[29][3] = '@';
            mouse[24][2] = '@';
            mouse[25][2] = '@';
            mouse[26][2] = '@';
            mouse[27][2] = '@';
            mouse[4][4] = '@';
            mouse[5][4] = '@';
            mouse[9][4] = '@';
            mouse[10][4] = '@';
            mouse[21][4] = '@';
            mouse[24][4] = '.';
            mouse[25][4] = '.';
            mouse[26][4] = '.';
            mouse[28][4] = '@';
            mouse[29][4] = '@';
            mouse[5][5] = '@';
            mouse[11][5] = '@';
            mouse[12][5] = '@';
            mouse[14][5] = '-';
            mouse[15][5] = '-';
            mouse[16][5] = '-';
            mouse[17][5] = '-';
            mouse[18][5] = '-';
            mouse[19][5] = '-';
            mouse[20][5] = '-';
            mouse[21][5] = '@';
            mouse[22][5] = '@';
            mouse[24][5] = '.';
            mouse[26][5] = '.';
            mouse[27][5] = '@';
            mouse[28][5] = '@';
            mouse[3][6] = '@';
            mouse[6][6] = '.';
            mouse[7][6] = '.';
            mouse[8][6] = '.';
            mouse[9][6] = '.';
            mouse[12][6] = '@';
            mouse[13][6] = '@';
            mouse[21][6] = '-';
            mouse[22][6] = '@';
            mouse[23][6] = '-';
            mouse[24][6] = '-';
            mouse[25][6] = '-';
            mouse[26][6] = '-';
            mouse[27][6] = '@';
            mouse[3][7] = '@';
            mouse[6][7] = '.';
            mouse[9][7] = '.';
            mouse[10][7] = '.';
            mouse[27][7] = '@';
            mouse[28][7] = ')';
            mouse[3][8] = '@';
            mouse[6][8] = '.';
            mouse[28][8] = ')';
            mouse[29][8] = ')';
            mouse[3][9] = '@';
            mouse[7][9] = '.';
            mouse[29][9] = ')';
            mouse[3][10] = '@';
            mouse[8][10] = '.';
            mouse[9][10] = '.';
            mouse[10][10] = '.';
            mouse[15][10] = '[';
            mouse[17][10] = ']';
            mouse[22][10] = '[';
            mouse[24][10] = ']';
            mouse[19][10] = ')';
            mouse[20][10] = ')';
            mouse[4][11] = '@';
            mouse[5][11] = '@';
            mouse[15][11] = '|';
            mouse[17][11] = '►';
            mouse[22][11] = '|';
            mouse[24][11] = '►';
            mouse[30][11] = ')';
            mouse[6][12] = '@';
            mouse[7][12] = '@';
            mouse[8][12] = '@';
            mouse[10][12] = '!';
            mouse[15][12] = 'L';
            mouse[17][12] = ']';
            mouse[22][12] = 'L';
            mouse[24][12] = ']';
            mouse[20][12] = ')';
            mouse[9][13] = '@';
            mouse[10][13] = '!';
            mouse[28][13] = 'W';
            mouse[29][13] = 'W';
            mouse[30][13] = 'W';
            mouse[31][13] = 'W';
            mouse[32][13] = 'W';
            mouse[33][13] = 'W';
            mouse[10][14] = '!';
            mouse[17][14] = '-';
            mouse[18][14] = '-';
            mouse[19][14] = '-';
            mouse[20][14] = '-';
            mouse[29][14] = 'W';
            mouse[30][14] = '0';
            mouse[31][14] = 'W';
            mouse[32][14] = '0';
            mouse[33][14] = 'W';
            mouse[10][15] = '!';
            mouse[11][15] = '!';
            mouse[12][15] = '!';
            mouse[20][15] = '-';
            mouse[21][15] = '-';
            mouse[22][15] = ']';
            mouse[29][15] = '-';
            mouse[30][15] = 'W';
            mouse[31][15] = 'W';
            mouse[32][15] = 'W';
            mouse[12][16] = '1';
            mouse[13][16] = '!';
            mouse[22][16] = 'L';
            mouse[23][16] = '-';
            mouse[24][16] = '-';
            mouse[25][16] = '-';
            mouse[26][16] = '-';
            mouse[27][16] = '&';
            mouse[28][16] = '&';
            mouse[29][16] = '&';
            mouse[13][17] = '*';
            mouse[14][17] = '*';
            mouse[15][17] = '*';
            mouse[16][17] = '*';
            mouse[22][17] = '*';
            mouse[23][17] = '&';
            mouse[24][17] = '&';
            mouse[25][17] = '&';
            mouse[26][17] = '&';
            mouse[16][18] = '*';
            mouse[17][18] = '-';
            mouse[18][18] = '*';
            mouse[19][18] = '*';
            mouse[20][18] = '*';
            mouse[21][18] = '-';
            mouse[22][18] = '*';
            mouse[26][18] = 'G';
            mouse[27][18] = '.';
            mouse[28][18] = 'C';

            return mouse;
        }



    static char[][] getFish() {
            char[][] fish = new char[34][19];

            for (int y = 0; y < 19; y++) {
                for (int x = 0; x < 34; x++) {
                    fish[x][y] = ' ';
                }
            }

            fish[18][3] = 'A';
            fish[19][3] = 'A';
            fish[18][4] = 'A';
            fish[20][4] = 'A';
            fish[21][4] = 'A';
            fish[18][5] = 'A';
            fish[22][5] = 'A';
            fish[23][5] = 'A';
            fish[17][6] = 'A';
            fish[18][6] = 'A';
            fish[23][6] = 'A';
            fish[24][6] = 'A';
            fish[25][6] = 'A';
            fish[10][7] = '-';
            fish[11][7] = '-';
            fish[12][7] = '-';
            fish[17][7] = 'A';
            fish[25][7] = 'A';
            fish[26][7] = 'A';
            fish[9][8] = '-';
            fish[10][8] = '-';
            fish[11][8] = '-';
            fish[16][8] = 'A';
            fish[17][8] = 'A';
            fish[26][8] = 'A';
            fish[27][8] = 'A';
            fish[8][9] = '-';
            fish[10][9] = '-';
            fish[11][9] = '-';
            fish[16][9] = 'A';
            fish[17][9] = 'A';
            fish[28][9] = 'A';
            fish[7][10] = '-';
            fish[10][10] = '-';
            fish[11][10] = 'A';
            fish[12][10] = 'A';
            fish[13][10] = 'A';
            fish[14][10] = 'A';
            fish[15][10] = 'A';
            fish[16][10] = 'A';
            fish[21][10] = 'A';
            fish[22][10] = 'A';
            fish[23][10] = 'A';
            fish[24][10] = 'A';
            fish[25][10] = 'A';
            fish[26][10] = 'A';
            fish[27][10] = 'A';
            fish[28][10] = 'A';
            fish[7][11] = '-';
            fish[9][11] = 'A';
            fish[10][11] = '-';
            fish[11][11] = 'A';
            fish[20][11] = 'A';
            fish[21][11] = 'A';
            fish[7][12] = '-';
            fish[8][12] = 'A';
            fish[20][12] = 'A';
            fish[7][13] = 'B';
            fish[8][13] = 'B';
            fish[12][13] = ')';
            fish[13][13] = ')';
            fish[20][13] = 'A';
            fish[6][14] = 'B';
            fish[9][14] = '►';
            fish[10][14] = '►';
            fish[14][14] = ')';
            fish[15][14] = ')';
            fish[20][14] = 'A';
            fish[22][14] = '-';
            fish[23][14] = '-';
            fish[24][14] = '-';
            fish[25][14] = '-';
            fish[5][15] = 'B';
            fish[6][15] = 'B';
            fish[9][15] = '►';
            fish[10][15] = '►';
            fish[14][15] = ')';
            fish[18][15] = 'B';
            fish[19][15] = 'B';
            fish[20][15] = '-';
            fish[21][15] = '-';
            fish[23][15] = '-';
            fish[24][15] = '-';
            fish[5][16] = 'B';
            fish[13][16] = ')';
            fish[17][16] = 'B';
            fish[18][16] = 'B';
            fish[19][16] = 'B';
            fish[23][16] = '-';
            fish[24][16] = '-';
            fish[5][17] = 'B';
            fish[13][17] = 'B';
            fish[14][17] = 'B';
            fish[15][17] = 'B';
            fish[16][17] = 'B';
            fish[17][17] = '-';
            fish[18][17] = '-';
            fish[19][17] = '-';
            fish[20][17] = '-';
            fish[21][17] = '-';
            fish[5][18] = 'B';
            fish[6][18] = 'B';
            fish[7][18] = 'B';
            fish[8][18] = 'B';
            fish[9][18] = 'B';
            fish[10][18] = 'B';
            fish[11][18] = 'B';
            fish[12][18] = 'B';
            fish[13][18] = 'B';
            
            fish[26][18] = 'G';
            fish[27][18] = '.';
            fish[28][18] = 'C';

            return fish;
        }


      
    
    public static int getTerminalWidth() {
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

}

