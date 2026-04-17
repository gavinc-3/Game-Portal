import java.util.ArrayList;
import java.util.Scanner;

import Game.Answer;

import java.util.Collections;
import java.util.List;
import java.util.Arrays;


public class Question {

    public static List<Answer> ansList;
    public static int[] WhichOneAreYou = {0, 0, 0, 0};
    public static boolean rerun = false;

    public static ArrayList<String> content = new ArrayList<>(List.of(
                // Question 1
            "Your good friend invited you to a dinner party in the evening a day in advance. You know that you will have limited time the next day, and will have to bring a dish to contribute.: A chipotle catering set with 10 custom bowls: A snack collection of all your friends favorite chips and candies: A fancy fruit plate with a great amount of variety: A dutch oven of chicken noodle soup",
            
            // Question 2
            "If you could add one additional class to the Horace Mann curriculum, what would it be?: Mental Fortification: Blindfolded Chess: Advanced Observation and Strategy: Tea Making 101",
            
            // Question 3
            "You walk into a Target and see a wide selection of different pillows on sale. Which do you choose?: White, medium-firm, rectangular pillow: Plush dog pillow: Memory-foam pillow that molds to your head shape: A pillow of a giant stick of lip balm",
            
            // Question 4
            "What type of watch are you choosing…? Assume they are all worth the same: G-Shock GA700: Studio Ghibli x Seiko Collaboration Watch: A really fancy one: Casio F-91W",
        
            // Question 5
            "You are being sent to a deserted island and have to take one of the following items with you. Which do you prefer?: A plain brick sitting on your porch that’s been there for years: A giant plush avocado: A perfectly stacked set of coasters that you never use but look aesthetically pleasing: A half-melted candle that smells vaguely like burnt toast",
            
            // Question 6
            "It’s a snow day! What are you going to do?: Lie in bed and scroll reels: Sleep in and work on homework: Roadtrip alone to Vermont to ski for the day: Bike to Soho with my friends and treat them to dinner",
            
            // Question 7
            "You are offered a variety of jobs. They all pay handsomely. Which will you choose?: Teacher: Therapist: I’ll start my own business!: Truck driver",
            
            // Question 8
            "You need 500,000 dollars, and fast! What are you going to do?: I’ll just get a loan!: I’ll just continue working at my current job and save.: I’ll trade stocks!: I’ll gamble all of my money!",
                
            // Question 9 
            "You’re picking a notebook for the school year. Which one feels right?: A simple, sturdy hardcover: A colorful spiral with fun patterns: A clean dotted journal: A soft pastel notebook with rounded edges",

            "You're deciding on a weekend activity. You…: Take a hike: Browse a local market: Work on a personal project: Lounge in a café with a book",

            "Your phone background is…: Solid blue: Photo collage of friends and family: Black and white image of NYC skyline: A random Pinterest illustration of a sunset",

            "If you were buying socks, you’d choose…: Thick gray crew socks: Yellow and white stripped socks: Black socks that fit with everyday outfits: Soft pastel socks with little clouds",

            "You find a single coin on the sidewalk. What will you do!: Pick it up and put it in your wallet!: Flip it three times for luck!: Examine the year and mint carefully to check value!: Place it in a small decorative dish at home!",

            "Choosing a tea, you would rather have...: Classic earl gray black tea: A wild fruit blend you’ve never tried: A rare imported green tea: Chamomile with a hint of lavender"
        ));

    public static void askQuestion(int questionNumber) throws InterruptedException {
        
        
        
        String[] parts = content.get(questionNumber - 1).split(": ");

        if (rerun == false) {
            System.out.println(parts[0]); //print question
        }
        rerun = false;

        Answer[] possibleAnswers = new Answer[4]; //print answers
        for (int i = 0; i < 4; i++) {
            possibleAnswers[i] = new Answer(parts[i+1], i+1);
        }

        ansList = Arrays.asList(possibleAnswers);
        Collections.shuffle(ansList); //shuffle answers
        for (int i = 0; i < ansList.size(); i++) { //print answers--including numbered formatting
            System.out.println("[" + (i+1) + "]: " + ansList.get(i).toString());
        }

        getResponse(new Scanner(System.in)); 

        // int[] WhichOneAreYou = {0, 0, 0, 0};

    }


    public static void getResponse(Scanner sc) throws InterruptedException {

        String input = sc.nextLine(); 

        int response = stringToInt(input);

        if (response != 1 && response != 2 && response != 3 && response != 4) {
            System.out.println("That isn't an option :( -- Please select one of the answers below:");
            rerun = true;
            askQuestion(Quiz.questionOrder[Quiz.currentQuestionNumber-1]);
        } else if(ansList.get(response-1).toID() == 1) {
            WhichOneAreYou[0]++;
            askNextQuestion();
        } else if(ansList.get(response-1).toID() == 2) {
            WhichOneAreYou[1]++;
            askNextQuestion();
        } else if(ansList.get(response-1).toID() == 3) {
            WhichOneAreYou[2]++;
            askNextQuestion();
        } else if(ansList.get(response-1).toID() == 4) {
            WhichOneAreYou[3]++;
            askNextQuestion();
        } 

    }


    public static int stringToInt (String answerString) {

        char[] charArray = answerString.toCharArray();
        for (char c : charArray) {
            if (c == '1' || c == '2' || c == '3' || c == '4') {
                int answerInt = Character.getNumericValue(c);
                return answerInt;
            }
        }
        return 0;
        
    }


    public static void askNextQuestion() throws InterruptedException {
        if (Quiz.currentQuestionNumber < Quiz.questionOrder.length) {
            Quiz.currentQuestionNumber++;
            askQuestion(Quiz.questionOrder[Quiz.currentQuestionNumber-1]);
        } else {
            Quiz.calculateResult();
        }
    }


    public static void resetState() {
        WhichOneAreYou = new int[]{0, 0, 0, 0};
        rerun = false;
        Quiz.currentQuestionNumber = 1;
    }

    public static int getTotalQuestions() {
     return content.size();
    }

    public static int getHighestScore() {
        int highestScore = 0;
        for (int score : WhichOneAreYou) {
            if (score > highestScore) {
                highestScore = score;
            }
        }
        return highestScore;
    }

}  
