import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
class User{
    public String username;
    public String password;
    public int score = 0;
}
class SignUp extends User{
    public String regexP = "^[a-zA-Z0-9]*.[^\\w][a-zA-Z0-9]{4,}$" + "|" + "[a-zA-Z0-9]{4,}.[^\\w][a-zA-Z0-9]*";
    public static SignUp signup(SignUp users[] , int n){
        Scanner input2 = new Scanner(System.in);
        int j=n-1;
        System.out.println("Please enter a username : ");
        users[j].username = input2.next();
        boolean b = true;
        for (int i=0;i<users.length && i!=j;i++){
            if (users[j].username.equals(users[i].username)){
                b=false;
            }
        }
        int count = 0;
        while (!b){
            System.out.println("This username used before,please try again : ");
            users[j].username = input2.next();
            for (int i=0;i<users.length;i++){
                if (users[j].username==users[i].username && i!=j){
                    count++;
                }
            }
            if (count==0){
                break;
            }
            count=0;
        }
        System.out.println("Please enter your password : ");
        users[j].password=input2.next();
        Pattern p = Pattern.compile(users[j].regexP);
        Matcher m = p.matcher(users[j].password);
        while (!m.find()){
            System.out.println("The password should be at least 6 characters and use letters and numbers and a particular character,please try again : ");
            users[j].password=input2.next();
            p = Pattern.compile(users[j].regexP);
            m = p.matcher(users[j].password);
        }
        System.out.println("You signed up succesfully =)");
        return users[j];
    }
}
class Game{
    String Person = "----\n|\n|\n|\n|\n|";
    void game(User u){
        String[] words={"tehran", "pizza", "banana", "new york", "advanced programming", "michael jordan",
                "lionel messi", "apple", "macaroni", "university", "intel", "kitten", "python", "java",
                "data structures", "algorithm", "assembly", "basketball", "hockey", "leader", "javascript",
                "toronto", "united states of america", "psychology", "chemistry", "breaking bad", "physics",
                "abstract classes", "linux kernel", "january", "march", "time travel", "twitter", "instagram",
                "dog breeds", "strawberry", "snow", "game of thrones", "batman", "ronaldo", "soccer",
                "hamburger", "italy", "greece", "albert einstein", "hangman", "clubhouse", "call of duty",
                "science", "theory of languages and automata"};
        int mistakes = 0;
        int maxmis=0;
        boolean b = true;
        boolean h = true;
        int help = 0;
        int random = (int)(Math.random() * 50);
        if (words[random].length()>9){
            maxmis = 14;
            b=false;
        }
        else {
            maxmis = 7;
        }
        String question = "";
        for (int i=0;i<words[random].length();i++){
            question=question+"-";
        }
        int k=0;
        int answer = 0;
        for (int i=0;i<words[random].length();i++){
            if (words[random].charAt(i)==' '){
                question = question.substring(0, i) + " " + question.substring(i + 1);
                k++;
            }
        }
        Scanner inputW = new Scanner(System.in);
        Scanner inputH = new Scanner(System.in);
        char guess = '0';
        int count = 0;
        int n=0;
        char[] guesses = new char[26];
        while (mistakes<=maxmis) {
            boolean g = true;//vase inke char tekrary nayad://///
            if (words[random].equals("united states of america")){
                System.out.println("YOUR WORD WAS <united states of america> SO,YOU RECEIVED 5 FREE POINTS!");
                u.score+=5;
                break;
            }
            if (b || (!b && mistakes%2==0)){
                if (mistakes==0 || (mistakes/2==0 && !b)){
                    System.out.println("----\n|\n|\n|\n|\n|");
                }
                else if ((mistakes==1 && b) || (!b && mistakes/2==1)){
                    System.out.println("----\n|  |\n|\n|\n|\n|");
                }
                else if ((mistakes==2 && b) || (!b && mistakes/2==2)){
                    System.out.println("----\n|  |\n|  o\n|\n|\n|");
                }
                else if ((mistakes==3 && b) || (!b && mistakes/2==3)){
                    System.out.println("----\n|  |\n|  o\n| /\n|\n|");
                }
                else if ((mistakes==4 && b) || (!b && mistakes/2==4)){
                    System.out.println("----\n|  |\n|  o\n| /|\n|\n|");
                }
                else if ((mistakes==5 && b) || (!b && mistakes/2==5)){
                    System.out.println("----\n|  |\n|  o\n| /|"+"\\"+"\n|\n|");
                }
                else if ((mistakes==6 && b) || (!b && mistakes/2==6)){
                    System.out.println("----\n|  |\n|  o\n| /|"+"\\"+"\n| /\n|");
                }
                else if ((mistakes==7 && b) || (!b && mistakes/2==7)){
                    System.out.println("----\n|  |\n|  o\n| /|"+"\\"+"\n| /"+" \\"+"\n|");
                }
            }
            for (int i = 0; i < n; i++) {
                System.out.println("Your guesses were : " + guesses[i] + " ");
            }
            System.out.println(question);
            System.out.println("IF YOU WANT HELP,YOU CAN PAY 10 SCORE AND ENTER 1 TO GIVE A LETTER,YOU CAN USE IT JUST ONCE!");
            System.out.println("IF YOU DONT WANT HELP OR USED IT BEFORE ENTER 0");
            help = inputH.nextInt();
            int randomH=0;
            if (help==1){
                if (!h){
                    System.out.println("YOU USED IT BEFORE!");
                }
                else {
                    randomH = (int) (Math.random() * words[random].length());
                    question = question.substring(0, randomH) + words[random].charAt(randomH) + question.substring(randomH + 1);
                    h = false;
                    u.score = u.score - 10;
                    answer++;
                    System.out.println(question);
                }
            }
            if (help == 0 || !h) {
                System.out.println("Enter your guess:)");
                guess = inputW.next().charAt(0);
                for (int i = 0; i < n; i++) {
                    if (guess==guesses[i]) {
                        System.out.println("You try this guess before,try again!!!");
                        g=false;
                        break;
                    }
                }
                if (!g){
                    continue;
                }
                for (int i = 0; i < words[random].length(); i++) {
                    if (i==randomH && !h){
                        continue;
                    }
                    if (words[random].charAt(0)==guess){
                        question = guess+question.substring(1);
                        count++;
                        answer++;
                    }
                    if (words[random].charAt(i) == guess && i!=0) {
                        count++;
                        answer++;
                        question = question.substring(0, i) + guess + question.substring(i + 1);
                    }
                }
                System.out.println(question);
                if (Person.equals("----\n|  |\n|  o\n| /|"+"\\"+"\n| /"+" \\"+"\n|") || question.equals(words[random])){
                    break;
                }
                if (count == 0) {
                    mistakes++;
                    System.out.println("YOUR GUESS WAS WRONG !!!");
                }
                guesses[n]=guess;
            }
            count=0;
            n++;
        }
        if (mistakes<maxmis && !words[random].equals("united states of america")){
            u.score+=5;
            System.out.println("!!!YOU WON!!!");
        }
        else {
            System.out.println("!!!GAME OVER!!!");
            System.out.println("your word was : "+words[random]);
        }
    }
}
public class MiniProject {
    public static void bubble_sort(SignUp users[] , int n){
        for (int i=0;i<n-1;i++){
            for (int j=1;j<n-1-i;j++){
                if (users[j-1].score < users[j].score){
                    SignUp temp=users[j];
                    users[j]=users[j-1];
                    users[j-1]=temp;
                }
            }
        }
    }
    public static void login(SignUp users[],int n){
        Scanner input2 = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        int voroodi=0;
        boolean b3 = false;
        System.out.println("Enter your username : ");
        String name = input2.next();
        int m=0;//baraye inke bbinm in username vase kodume baad pass ro check konm
        for (int z = 0; z < users.length-1; z++) {
            if (name.equals(users[z].username)) {
                b3 = true;
                m=z;
                break;
            }
        }
        //System.out.println(m);//baraye check krdnesh
        int j=n-1;
        if (!b3) {
            System.out.println("username didn't find,you should signup first :)");
            SignUp s = new SignUp();
            users[j] = s.signup(users, n);
            n++;
            login(users,n);
        }
        else {
            System.out.println("Enter your password : ");
            String password = input2.next();
            while (!password.equals(users[m].password)) {
                System.out.println("password isn't correct,try again");
                password=input2.next();
            }
            System.out.println("1.start game/2.show leaderboard");
            voroodi = input.nextInt();
            if (voroodi == 1) {
                Game g = new Game();
                g.game(users[m]);
                System.out.println("YOU SHOULD LOGIN AGAIN!");
                login(users,n);
            }
            else if (voroodi == 2) {
                String s = "";
                bubble_sort(users, n);
                for (int k = 0; k < n-1; k++) {
                    s = s+users[k].username;
                    for (int z = 0; z < 25 - n - users[k].username.length(); z++) {
                        s=s+"-";
                    }
                    s=s+" "+users[k].score+"\n";
                }
                System.out.println(s);
                System.out.println("If you want to exit,enter 0 else enter 1 :)");
                Scanner inputE = new Scanner(System.in);
                int inputExit = inputE.nextInt();
                if (inputExit==0){
                    try{
                        File file = new File("C:\\Users\\zettabyte\\Desktop\\Project\\game.txt");
                        if (file.createNewFile()){
                            System.out.println("file created successfully!!");
                        }
                        else {
                            System.out.println("file already exists!!");
                        }
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    try {
                        FileWriter writer = new FileWriter("C:\\Users\\zettabyte\\Desktop\\Project\\game.txt");
                        writer.write(s);
                        writer.close();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }
                    System.exit(0);
                }
                else if (inputExit==1) {
                    System.out.println("1.signup/2.login");
                    Scanner inputL = new Scanner(System.in);
                    int voroodiL = inputL.nextInt();
                    j=n-1;
                    if (voroodiL==1){
                        SignUp s2 = new SignUp();
                        users[j]=s2.signup(users,n);
                        n++;
                        login(users,n);
                    }
                    else if (voroodiL==2) {
                        login(users,n);
                    }
                }
            }
        }
    }
    public static void main(String[] args){
        int n=1;
        SignUp[] users = new SignUp[100];
        for (int i=0;i<100;i++){
            users[i]=new SignUp();
        }
        System.out.println("<<<<<HANGMAN>>>>>");
        System.out.println("1.signup/2.login");
        Scanner input = new Scanner(System.in);
        int voroodi = input.nextInt();
        int j=0;
        if (voroodi==1){
            SignUp s = new SignUp();
            users[j]=s.signup(users,n);
            j++;
            n++;
            login(users,n);
        }
        else if (voroodi==2) {
            login(users,n);
        }
    }
}
