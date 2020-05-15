//Import Commands.
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;

public class Quiz {
    
    //Global Variables.
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static ArrayList<String[]> scoreBoard = new ArrayList<String[]>();
    static ArrayList<Integer> generatedNumbers;
    static String[][] packets;
    static String[] userData;
    static int totalQuestions;
    static int input;
    static int defaultQuestions = 5;
    static int userQuestions;
    
    //Functions.
    static int findRandom(){
        int value;
        do{
            value = random.nextInt(packets.length);
        }while(generatedNumbers.contains(value));
        generatedNumbers.add(value);
        return value;
    }
    static void initialize(){
        packets = new String[defaultQuestions][3];

                    packets[0][0] = "Question number 1, answer \"next\" to proceed";
                    packets[0][1] = "next";
                    packets[0][2] = "the clue is \"next\"";
               
                    packets[1][0] = "Question number 2, answer \"skip\" to proceed";
                    packets[1][1] = "skip";
                    packets[1][2] = "the clue is \"skip\"";
                
                    packets[2][0] = "Question number 3, answer \"next\" to proceed";
                    packets[2][1] = "next";
                    packets[2][2] = "the clue is \"next\"";
                
                    packets[3][0] = "Question number 4, answer \"next\" to proceed";
                    packets[3][1] = "next";
                    packets[3][2] = "the clue is \"next\"";
                
                    packets[4][0] = "Question number 5, answer \"next\" to proceed";
                    packets[4][1] = "next";
                    packets[4][2] = "the clue is \"next\"";
                    
            if( userQuestions > 0){
                while(userQuestions > 0){
                    System.out.println("Input Question : ");
                    packets[(defaultQuestions-userQuestions)][0]= scanner.nextLine();
                    System.out.println("Input Answer : ");
                    packets[(defaultQuestions-userQuestions)][1]= scanner.nextLine();
                    System.out.println("Input Clue : ");
                    packets[(defaultQuestions-userQuestions)][2]= scanner.nextLine();
                    userQuestions--;
                }
            }
            }
    static void addQuestion(){
        System.out.println("Number of questions being added : ");
        userQuestions  = scanner.nextInt();
                         scanner.nextLine();
        defaultQuestions += userQuestions;
    }
    static void ask(){
        int score = 0;
        generatedNumbers = new ArrayList<Integer>();
        int questionNumber;
        String answer;
        for (int i = 1; i <= totalQuestions; i++) {
            questionNumber = findRandom();
            while(true){
                System.out.print("\n");
                System.out.print("\t" + i+"."+packets[questionNumber][0]+"\n\t  Answer : ");
                answer = scanner.nextLine();
                if(answer.contains(packets[questionNumber][1].toLowerCase())){
                    score += 10;
                    break;
                }else if(answer.equalsIgnoreCase("clue")){
                    System.out.println("clue : " + packets[questionNumber][2]);   
                }else{
                    break;
                }
            }
        }
        userData[1] = Integer.toString(score);
    }
    static void menu(){
        System.out.print("\n\n");
        System.out.println("\t|===== Main Menu =====|");
        System.out.println("\t| 1. Play Quiz        |");
        System.out.println("\t| 2. ScoreBoard       |");
        System.out.println("\t| 3. Search score     |");
        System.out.println("\t| 4. Quit             |");
        System.out.println("\t| 5. Add Question     |");
        System.out.println("\t|=====================|");
        System.out.print("\n\n");
        System.out.print("Masukkan Input: ");
    }
    static void selectionSort(){
        int max;
        for (int i = scoreBoard.size()-1; i > 0; i--) {
            max = i;
            for (int j = 0; j < i; j++) {
                if(Integer.parseInt(scoreBoard.get(j)[1])<Integer.parseInt(scoreBoard.get(max)[1])){
                    max = j;
                }
            }
            if(max != i){
                Collections.swap(scoreBoard, i, max);
            }
        }
    }
    static void search(){
        System.out.print("Input keyword: ");
        boolean found = false;
        String keyword = scanner.nextLine();
        System.out.println("\n");
        System.out.println("\t==== Result ====");
        for (int i = 0; i < scoreBoard.size(); i++) {
            if(scoreBoard.get(i)[0].equalsIgnoreCase(keyword)){
                System.out.print("\t" + keyword + " : ");
                System.out.println(scoreBoard.get(i)[1]);
                found = true;
            }
        }
        if(found == false){
            System.out.println("\tNot Found");
        }
        System.out.print("\t==========================\n");
        System.out.print("\n Type something to quit: ");
        scanner.next();
    }
    static void printScoreBoard(){
        System.out.print("\n");
        System.out.println("\t==== ScoreBoard ====");
        for(int i = 0 ; i < scoreBoard.size() ; i++)
            System.out.println("\t" + (i+1)+". "+scoreBoard.get(i)[0]+ " : " + scoreBoard.get(i)[1]);
        System.out.println("\t====================");
        System.out.print("\n Type something to quit: ");
        scanner.next();
    }
    static void stores(){
        scoreBoard.add(userData.clone());
    }
    static void option(){
        System.out.print("\n\n");
        System.out.println("\t|===== Game Over =====|");
        System.out.println("\t| 1. Restart          |");
        System.out.println("\t| 2. Quit             |");
        System.out.println("\t|=====================|");
        System.out.print("\n\n");
        System.out.print("Type Input: ");
    }
    static void inputUserData(){
        userData = new String[2];
        System.out.print("\n\n");
        System.out.print("Username : ");
        userData[0] = scanner.nextLine();
        System.out.print("Number Of Questions max(" + defaultQuestions + "): ");
        totalQuestions = scanner.nextInt();
                         scanner.nextLine();
                         if(totalQuestions > defaultQuestions)
                            totalQuestions = defaultQuestions;
        System.out.print("\n");
    }

    //Main Function.
    public static void main(final String[] args) {

        while(true){
            initialize();
            menu();
            input= scanner.nextInt();
                   scanner.nextLine();
            switch(input){
                case 1:
                    do{
                        inputUserData();
                        ask();
                        stores();
                        option();
                        input = scanner.nextInt();
                                scanner.nextLine();
                    }while(input == 1);
                break;
    
                case 2:
                    selectionSort();
                    printScoreBoard();
                break;
    
                case 3:
                    search();
                break;
    
                case 4:
                    System.out.println("Thank you!");
                return;
                
                case 5:
                    addQuestion();
                break;    
            }
        }

    }
}
