import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ErrorCheck {
    public static final int QUIT_CODE = -1;
    private static final BufferedReader INPUT = new BufferedReader(new InputStreamReader(System.in));

    public static int getInt() {
        while (true) {
            String input = readLine();
            if (input == null) {
                return QUIT_CODE;
            }
            input = input.trim();
            if (input.equalsIgnoreCase("q")) {
                return QUIT_CODE;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number or q to quit.");
            }
        }
    }

    public static boolean shouldQuit() {
        try {
            if (!INPUT.ready()) {
                return false;
            }
            return isQuit(INPUT.readLine());
        } catch (IOException e) {
            return false;
        }
    }

    private static String readLine() {
        try {
            return INPUT.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    private static boolean isQuit(String input) {
        return input != null && input.trim().equalsIgnoreCase("q");
    }
}
