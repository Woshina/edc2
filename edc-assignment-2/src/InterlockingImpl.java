import java.util.ArrayList;
import java.util.HashMap;

public class InterlockingImpl implements Interlocking{



    public void addTrain(String trainName, int entryTrackSection, int destinationTrackSection){
        if (Trainlist.size()>0) {
            for (int i = 0; i < Trainlist.size(); i++) {
                ArrayList<String> check= new ArrayList<String>();
                check = Trainlist.get(i) ;
                String checkname=check.get(0);
                String checkentry=check.get(3);
                int checkentrynum = Integer.parseInt(checkentry);
                if(checkname.equals(trainName)){
                    throw new IllegalArgumentException("train name repeat");

                } else if (checkentrynum==entryTrackSection) {
                    throw new IllegalArgumentException("this section have a train ");
                }

            }
        }

        switch(entryTrackSection) {
            case 1:
                if (destinationTrackSection==8||destinationTrackSection==9){
                    ArrayList<String> traninfor= new ArrayList<String>();
                    traninfor.add(trainName);
                    traninfor.add(String.valueOf(entryTrackSection));
                    traninfor.add(String.valueOf(destinationTrackSection));
                    traninfor.add(String.valueOf(entryTrackSection));
                    traninfor.add("South");
                    Trainlist.add(traninfor);

                }else{throw new IllegalArgumentException("invaild destination Section");}
                break;
            case 3:if (destinationTrackSection==4||destinationTrackSection==11){
                ArrayList<String> traninfor= new ArrayList<String>();
                traninfor.add(trainName);
                traninfor.add(String.valueOf(entryTrackSection));
                traninfor.add(String.valueOf(destinationTrackSection));
                traninfor.add(String.valueOf(entryTrackSection));
                traninfor.add("South");
                Trainlist.add(traninfor);
            }else{throw new IllegalArgumentException("invaild destination Section");}
                // code block
                break;
            case 4:
            case 11:
                if (destinationTrackSection==3){
                    ArrayList<String> traninfor= new ArrayList<String>();
                    traninfor.add(trainName);
                    traninfor.add(String.valueOf(entryTrackSection));
                    traninfor.add(String.valueOf(destinationTrackSection));
                    traninfor.add(String.valueOf(entryTrackSection));
                    traninfor.add("North");
                    Trainlist.add(traninfor);

                }else{throw new IllegalArgumentException("invaild destination Section");}
                // code block
                break;
            case 9:
            case 10:
                if (destinationTrackSection==2){
                    ArrayList<String> traninfor= new ArrayList<String>();
                    traninfor.add(trainName);
                    traninfor.add(String.valueOf(entryTrackSection));
                    traninfor.add(String.valueOf(destinationTrackSection));
                    traninfor.add(String.valueOf(entryTrackSection));
                    traninfor.add("North");
                    Trainlist.add(traninfor);
                }else{throw new IllegalArgumentException("invaild destination Section");}
                // code block
                break;
            // code block
            default: throw new IllegalArgumentException("invaild entry Section");
        }
        UpdateToken(entryTrackSection);
    }
    // Update the token at each section a


    public int moveTrains(String[] trainNames){
        //move the train that reached out the corridor destination
        ArrayList<Integer>tokenToZero=new ArrayList<Integer>();// store the index of place that token moves out
        ArrayList<Integer>tokenToOne=new ArrayList<Integer>();//store the index of place that token moves in
        for (int i = 0; i < Trainlist.size(); i++) {
            ArrayList<String> trancheck= new ArrayList<String>();
            trancheck = Trainlist.get(i) ;
            String checkcurrentsection=trancheck.get(3);
            String checkdestination=trancheck.get(4);
            int currentsection=Integer.valueOf(checkcurrentsection);

            if(checkcurrentsection.equals(checkdestination)){
                Trainlist.remove(i);
                tokenToZero.add( currentsection-1);


            }

        }

        return 0;
    }
    public String getSection(int trackSection){
        String Trainname="NoneTrainonThatSection";
        if(trackSection<1||trackSection>11){ throw new IllegalArgumentException("train section invalid");}
        else {
            for (int i = 0; i < Trainlist.size(); i++) {
                ArrayList<String> trancheck= new ArrayList<String>();
                trancheck = Trainlist.get(i) ;
                String checkcurrentsection=trancheck.get(3);
                String name=trancheck.get(0);

                int currentsection=Integer.valueOf(checkcurrentsection);
                if (currentsection==trackSection){
                    Trainname=name;
                }
            }}
        return Trainname;
    }
    public int getTrain(String trainName){
        return 1;
    }
    public void UpdateToken(int tokenlocation) {

        if(tokenList.get(tokenlocation-1)==1){
            throw new IllegalArgumentException("add train would cause crash");
        }else{tokenList.set(tokenlocation-1,1);}


    }
    public void initializeToken() {
        for (int i = 0; i < 11; i++) {
            tokenList.add(0);
        }
    }

    public static void main(String[] args) {
        HashMap TrainInf = new HashMap();
        Interlocking il = new InterlockingImpl();
        il.initializeToken();
        System.out.println(tokenList);
        il.addTrain("t1",1,8);
        il.addTrain("t2",9,2);
        il.addTrain("t3",3,4);



        System.out.println(Trainlist);
        System.out.println(tokenList);




    }
}
