import java.util.Scanner;  // Import the Scanner class 
import java.util.HashMap;
import javax.lang.model.util.ElementScanner14;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.ArrayList;
@SuppressWarnings("unchecked")

class RegexEngine {
    static Character e = '\u03b5'; 
   


      
     
    public static List<HashMap> Setedge(String regex) {
        List<HashMap> adjList= new ArrayList<HashMap>();
        HashMap<String, String> edge = new HashMap<String, String>();
        edge.put("source", "0");
        edge.put("condiciton", e.toString());
        edge.put("destination", "1");
        adjList.add(edge);
        String prDis;
        String source;
        String condiciton;
        String destination;
        int number;
        int sonum;
        for (int i = 0 ; i < regex.length(); i++) {
            char c = regex.charAt(i);
            System.out.println(c);
           
            if (Character.isLetter(c)||Character.isDigit(c)) {
                HashMap<String, String> trans= new HashMap<String, String>();
                HashMap<String, String> nedge = new HashMap<String, String>();
                trans=adjList.get(adjList.size() - 1);
                prDis=trans.get("destination");
                nedge.put("source", prDis);
                condiciton=""+String.valueOf(c);
                nedge.put("condiciton", condiciton);
                 number = Integer.parseInt(prDis)+1;
                destination=String.valueOf(number);
                nedge.put("destination", destination);
                adjList.add(nedge);

            }if (c=='*') {
                HashMap<String, String> trans= new HashMap<String, String>();
                HashMap<String, String> nedge = new HashMap<String, String>();
                HashMap<String, String> nedge1= new HashMap<String, String>();
                HashMap<String, String> nedge2= new HashMap<String, String>();
                trans=adjList.get(adjList.size() - 1);
                prDis=trans.get("destination");
                nedge.put("source", prDis);
                nedge1.put("source", prDis);
                nedge.put("condiciton", e.toString());
                nedge2.put("condiciton", e.toString());
                number = Integer.parseInt(prDis)+1;
                destination=String.valueOf(number);
                nedge.put("destination", destination);
                nedge2.put("destination", destination);
                number = Integer.parseInt(prDis)-1;
                source=String.valueOf(number);
                nedge1.put("destination", source);
                nedge2.put("source", source);
                condiciton=""+String.valueOf(regex.charAt(i-1));
                nedge1.put("condiciton", condiciton);
                adjList.add(nedge);
                adjList.add(nedge1);
                adjList.add(nedge2);
                





            }
        }
        return adjList;
    }
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
    if (regex.charAt(len-1)=='|'){
        express.add('|'+String.valueOf(last));}
    else if (Character.isLetter(last)||Character.isDigit(last)){
        express.add(String.valueOf(last));
    }
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
    System.out.println(adjList);
    

    boolean con = true;
    Scanner in = new Scanner(System.in);
    String input = in.nextLine();
    ArrayList<String> express=expression(regex);
    System.out.println(express);
    

    }
}