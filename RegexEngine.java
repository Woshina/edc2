import java.util.Scanner;  // Import the Scanner class 
import java.util.HashMap;
import javax.lang.model.util.ElementScanner14;
import java.util.Collections;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
@SuppressWarnings("unchecked")

class RegexEngine{

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
// genrate a transition list from user input

  
  public static void main(String[] args) {
    String regex=vaildInput();
    List<HashMap> adjList= new ArrayList<HashMap>();
    adjList=Setedge(regex);
    
   
    

    // boolean con = true;
    // Scanner in = new Scanner(System.in);
    // String input = in.nextLine();
    ArrayList<String> express=expression(regex);
    ArrayList<ArrayList<HashMap>> enfa=Setenfa(regex);
    for(int i=0;i<enfa.size();i++){
        ArrayList<HashMap> sb= enfa.get(i);
        System.out.println(sb);
    }
    
    System.out.println(express);
    Scanner keyboard = new Scanner(System.in);
    String input = keyboard.nextLine();
    System.out.println(check(input, enfa));

    }
}