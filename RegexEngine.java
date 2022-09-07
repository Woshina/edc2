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
    static Character e = '\u03b5'; 
 
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
public static ArrayList<String> expression(String regex) {
    
    ArrayList<String> express= new ArrayList<String>();
    int len=regex.length()-1;
    
    for (int i = 0 ; i < regex.length()-1 ; i++) {
        char c = regex.charAt(i);
        if (Character.isLetter(c)||Character.isDigit(c)) {
            String ad= ""+c;
            if(i!=0&&regex.charAt(i-1)=='|'){
                 ad= "|"+ad;
                }
            if (regex.charAt(i+1)=='*'||regex.charAt(i+1)=='+'||regex.charAt(i+1)=='|'){
                    ad= ad+regex.charAt(i+1);
            }
             if(i<regex.length()-2){
                    if (regex.charAt(i+2)=='|'){
                        if (regex.charAt(i+1)=='*'||regex.charAt(i+1)=='+'){ad= ad+"|";}
                         }
                }
                express.add(ad);
                
           
        }


    }
    char last=regex.charAt(len);
    if(len-1>=0){
    if (regex.charAt(len-1)=='|'){
        express.add('|'+String.valueOf(last));}
    else if (Character.isLetter(last)||Character.isDigit(last)){
        express.add(String.valueOf(last));
    }}else{ express.add(String.valueOf(last));}
    int j=-1;
    ArrayList<String> efinal= new ArrayList<String>();
    for(int i=0;i < express.size();i++){
        String element= express.get(i);
        if (element.charAt(0)=='|'){
            efinal.set(j,efinal.get(j)+element.substring(1));
        }else{efinal.add(element);
        j++;}
    }
    
     return efinal;
  }
  
  public static void main(String[] args) {
    String regex=vaildInput();
    List<HashMap> adjList= new ArrayList<HashMap>();
    adjList=Setedge(regex);
    

    }
}