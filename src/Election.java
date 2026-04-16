import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;

class County {
    String state;
    String county;
    int trumpVotes;
    int clintonVotes;

    County(String state, String county, int trumpVotes, int clintonVotes) {
        this.state = state;
        this.county = county;
        this.trumpVotes = trumpVotes;
        this.clintonVotes = clintonVotes;
    }
}

public class Election extends Game{


    static HashMap<String, HashMap<String, County>> data = new HashMap<>();
    static int nationalTrumpVotes = 0;
    static int nationalClintonVotes = 0;
    static int nationalCountyCount = 0;

    @Override
    public String getGameName() {
        return "Election";
    }

    public static void main(String[] args) throws Exception {

        new Election().play();
    
    }

        @Override
        public void play() {
            try {
                loadData("src/Election Analysis/data.csv");        
                Scanner sc = new Scanner(System.in);
                System.out.println("** 2016 U.S. Election Data Tool **");

                while (true) {
                    System.out.print("\nEnter state name (enter \"quit\" to exit): ");
                    String stateInput = sc.nextLine().trim().toLowerCase();
                    if (stateInput.equals("quit")) 
                        break;

                    System.out.print("Enter county name: ");
                    String countyInput = sc.nextLine().trim().toLowerCase();

                    County result = findCounty(stateInput, countyInput);

                    if (result != null) {
                        printResults(result);
                        showMenu(result, sc);
                    } else {
                        System.out.println("No data found for that state and county.");
                    }
                }

                System.out.println("I hope I was helpful!");
                sc.close();

    } catch (Exception e) {
        e.printStackTrace();
    }
}




    private static County findCounty(String stateInput, String countyInput) {
        for (String statekey : data.keySet()) {
            if (statekey.toLowerCase().equals(stateInput)) {

                HashMap<String, County> counties = data.get(statekey);
                for (String countyKey : counties.keySet()) {

                    if (countyKey.toLowerCase().equals(countyInput)) {
                        return counties.get(countyKey);
                    }
                }
            }
        }
        return null;
    }




    private static void printResults(County c) {
        System.out.println("\n2016 Presidential Vote Data");
        System.out.println("State: " + c.state);
        System.out.println("County: " + c.county);
        System.out.println("Trump: " + c.trumpVotes);
        System.out.println("Clinton: " + c.clintonVotes);
    }





    private static void loadData(String filename) throws Exception {


        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine();

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            String state = parts[0].trim().replace("\"", "");
            String county = parts[1].trim().replace("\"", "");

            int trumpVotes = Integer.parseInt(parts[3]);
            int clintonVotes = Integer.parseInt(parts[4]);

            County c = new County(state, county, trumpVotes, clintonVotes); 

            data.putIfAbsent(state, new HashMap<>()); 
            data.get(state).put(county, c);

            nationalClintonVotes += clintonVotes;
            nationalTrumpVotes += trumpVotes;   
            nationalCountyCount += 1;
        }

        br.close();
}

private static void showMenu(County c, Scanner sc){

        while (true) {
            System.out.println("\nCool Tools:");
            System.out.println("1. Compare Trump votes in this county to national county average");
            System.out.println("2. Compare Trump-to-Clinton vote ratio in this county");
            System.out.println("3. Return to state selection");
            System.out.println("4. Get the total amount of voters in the county");

            System.out.print("Selection: ");
            String choice = sc.nextLine().trim();

            if (choice.equals("1")) {
                compareTrumpToNationalAverage(c);
            } else if (choice.equals("2")) {
                printTrumpToClintonRatio(c);
            } else if (choice.equals("3")) {
                return;
            } else if(choice.equals("4")){
                int totalVoters = c.trumpVotes + c.clintonVotes;
                System.out.println("Total voters in " + c.county + ", " + c.state + ": " + totalVoters);
            }
            else {
                System.out.println("Invalid option.");
            }
        }

    }

        private static void compareTrumpToNationalAverage(County c) {

            double nationalAverage =  nationalTrumpVotes / nationalCountyCount;
            double percentDifference = ((c.trumpVotes - nationalAverage) / nationalAverage) * 100;

            System.out.println("\nTrump Vote Comparison");
            System.out.println("County Trump Votes: " + c.trumpVotes);
            System.out.println("National County Average: " + (int) nationalAverage);

            if (percentDifference > 0) {
                System.out.println("This county voted " + percentDifference + "% ABOVE the national county average for Trump.");
            } else if (percentDifference < 0) {
                System.out.println("This county voted " + Math.abs(percentDifference) + "% BELOW the national county average for Trump.");
            } else {
                System.out.println("This county matches the national county average for Trump.");
            }
        }      



        private static void printTrumpToClintonRatio(County c) {

            System.out.println("\nTrump-to-Clinton Vote Ratio in " + c.county + ", " + c.state);

            if (c.clintonVotes == 0 && c.trumpVotes == 0) {
                System.out.println("No votes recorded in this county.");
                return;
            }

            int trump = c.trumpVotes;
            int clinton = c.clintonVotes;

            double ratio;
            if (trump < clinton) {
                ratio = (double) clinton / trump;
                System.out.println("For every 1 Trump voter, there were " + Math.round(ratio) + " Clinton voter(s).");
            } else {
                ratio = (double) trump / clinton;
                System.out.println("For every 1 Clinton voter, there were " + Math.round(ratio) + " Trump voter(s).");
            }
            
            double basicRatioOfVotes = (double) trump / clinton;
            System.out.println("Trump-to-Clinton ratio: " + basicRatioOfVotes);

        }


          @Override
    public String getScore() {
        return Integer.toString(Question.getHighestScore());
    }

    @Override
    public boolean isHighScore(String score, String currentHighScore) {
        if (currentHighScore == null) {
            return true;
        }
        return Integer.parseInt(score) > Integer.parseInt(currentHighScore);
    }


   


}












