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
    int stateconter=0;
   


      
    public static ArrayList<ArrayList<HashMap>> star(String regex, ArrayList<ArrayList<HashMap>> n){
        HashMap<String, String> trans= new HashMap<String, String>();
        HashMap<String, String> trans1= new HashMap<String, String>();
        ArrayList<HashMap> State = new ArrayList<HashMap>();
        
        int nextState=n.size()+1;
        int prState=n.size()-1;
        String PS=String.valueOf(prState);
        String NS=String.valueOf(nextState);
        String condiciton=""+String.valueOf(regex.charAt(0));
        ArrayList<HashMap> prList=n.get(prState);        
        trans.put("condiciton",e.toString() );
        trans.put("destination",NS );
        State.add(trans);
        trans1.put("condiciton",condiciton );
        trans1.put("destination",PS );
        State.add(trans1);
        n.add(State);
        prList.add(trans);
        n.set(prState,prList);

        
        return n;
    }
    public static ArrayList<ArrayList<HashMap>> plus(String regex, ArrayList<ArrayList<HashMap>> n){
        HashMap<String, String> trans= new HashMap<String, String>();
        HashMap<String, String> trans1= new HashMap<String, String>();
        HashMap<String, String> trans2= new HashMap<String, String>();
        ArrayList<HashMap> State = new ArrayList<HashMap>();
        ArrayList<HashMap> State1 = new ArrayList<HashMap>();
        int nextState=n.size()+1;
        String NS=String.valueOf(nextState);
        String CS=String.valueOf(nextState-1);
        String NSS=String.valueOf(nextState+1);
        String condiciton=""+String.valueOf(regex.charAt(0));        
        trans.put("condiciton",e.toString() );
        trans.put("destination",NS );
        State.add(trans);
        n.add(State);
        trans1.put("condiciton",condiciton );
        trans1.put("destination",CS);
        trans2.put("condiciton",e.toString( ));
        trans2.put("destination",NSS);
        State1.add(trans1);
        State1.add(trans2);
        n.add(State1);
        return n;
    }
        public static ArrayList<ArrayList<HashMap>> basic(String regex, ArrayList<ArrayList<HashMap>> n){
        HashMap<String, String> trans= new HashMap<String, String>();
        ArrayList<HashMap> State = new ArrayList<HashMap>();
        int nextState=n.size()+1;
        String NS=String.valueOf(nextState);
         
        trans.put("condiciton",regex );
        trans.put("destination",NS );
        State.add(trans);
        n.add(State);
        
        return n;
    }
    public static ArrayList<ArrayList<HashMap>> alternation(int index,ArrayList<ArrayList<HashMap>> n){
        HashMap<String, String> trans= new HashMap<String, String>();
        HashMap<String, String> trans1= new HashMap<String, String>();
        ArrayList<HashMap> State = new ArrayList<HashMap>();
        int nextState=n.size()+1;
        String NS1=String.valueOf(nextState);
        String NS2=String.valueOf(nextState);
        ArrayList<HashMap> prList=n.get(index); 
        trans.put("condiciton",e.toString() );
        trans.put("destination",NS2 );

        
        
        prList.add(trans);
        n.set(index,prList);

        return n;
    }
   
    public static ArrayList<ArrayList<HashMap>> Setenfa(String regex ) {
        ArrayList<ArrayList<HashMap>> enfa= new ArrayList<ArrayList<HashMap>>();
        HashMap<String, String> trans= new HashMap<String, String>();
        ArrayList<HashMap> State = new ArrayList<HashMap>();
        List<Integer> toEnd = new ArrayList<Integer>();
        trans.put("condiciton",e.toString());
        trans.put("destination","1" );
        State.add(trans);
        enfa.add(State);
        int index=0;
        for (int i = 0 ; i < regex.length(); i++) {


          char c = regex.charAt(i);
          String condition= new String();
          condition=""+String.valueOf(c);
          if (Character.isLetter(c)||Character.isDigit(c)) {
            condition=""+String.valueOf(c);
            enfa=basic(condition,enfa);
            

          }else if (c=='*'){
            condition=""+String.valueOf(regex.charAt(i-1));
            enfa=star(condition,enfa);}

          else if (c=='+'){
            condition=""+String.valueOf(regex.charAt(i-1));
            enfa=plus(condition,enfa);}
            else if (c=='|'){

               
                enfa=alternation(index,enfa);
                ArrayList<HashMap> SE = new ArrayList<HashMap>();
                enfa.add(SE);
                toEnd.add(enfa.size()-1);
                
        }
    }
    ArrayList<HashMap> SE = new ArrayList<HashMap>();
                enfa.add(SE);
    toEnd.add(enfa.size()-1);
    int nextState=enfa.size();
    String NS=String.valueOf(nextState);
    HashMap<String, String> path= new HashMap<String, String>();
    path.put("condiciton",e.toString() );
    path.put("destination",NS );
for(int i=0;i<toEnd.size();i++){
    int counter=toEnd.get(i);
    ArrayList<HashMap> end=enfa.get(counter); 
    
    end.add(path);
    enfa.set(counter,end);

}     

        
 return enfa;

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
   

    }
}