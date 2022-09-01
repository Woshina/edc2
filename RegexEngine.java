import java.util.Scanner;  // Import the Scanner class 
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.util.ElementScanner14;

import java.util.regex.Pattern;
import java.util.ArrayList;

class Main {

    public static String vaildInput() {
        boolean isVaild = false;
        Scanner keyboard = new Scanner(System.in);
        String input = keyboard.nextLine();

    // Validate the input.
    
        while (isVaild==false) {
            for (int i = 0 ; i != input.length() ; i++) {
                char c = input.charAt(i);
                if (i==0&&(c==')'||c=='*'||c=='+'||c=='|')){
                    isVaild=false;
                    input = keyboard.nextLine();
                    break;

                }else if(!Character.isLetter(c)&&c!='('&&c!=')'&&c!='*'&&c!='+'&&c!='|'&&!Character.isDigit(c)) {
                    isVaild=false;
                    input = keyboard.nextLine();
                    break;

                }else{
                    isVaild=true;
                    break;
                }
                
                
            }
            
        }
        System.out.println("ready");
        return input;
    }

  public static void RegexEngine(String[] args) {
    String regex=vaildInput();
    boolean con = true;
    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    int Com=checkComplex(regex);
    

    }
}