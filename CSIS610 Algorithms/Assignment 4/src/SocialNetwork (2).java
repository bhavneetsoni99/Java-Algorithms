import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by bsoni1 on 11/30/2016.
 */
public class SocialNetwork {

    public static ArrayList<String> Members = new ArrayList<String>();
    public static ArrayList<ArrayList<String>> Network =  new ArrayList<ArrayList<String>>();

    ArrayList<ArrayList<String>> Connections = new ArrayList<ArrayList<String>>();

    public static void main (String[] args) {
        Members.add("Madhu");
        Members.add("Giridhar");
        Members.add("Sachin");
        Members.add("Suman");
        Members.add("Bhavneet");
        Members.add("Preeti");
        Members.add("Maanya");
        Members.add("Sakshi");

        for (int i = 0; i< Members.size(); i++){
            ArrayList<String> branch = new ArrayList<String>();
            Network.add(branch);
        }
        Network.get(0).add("Giridhar");
        Network.get(1).add("Madhu");
        Network.get(0).add("Sachin");
        Network.get(0).add("Bhavneet");
        Network.get(2).add("Madhu");
        Network.get(2).add("Suman");
        Network.get(2).add("Sakshi");
        Network.get(4).add("Madhu");
        Network.get(4).add("Preeti");
        Network.get(4).add("Maanya");
        Network.get(6).add("Bhavneet");
        Network.get(6).add("Preeti");
        Network.get(7).add("Sachin");
        Network.get(7).add("Suman");
        Network.get(5).add("Bhavneet");
        Network.get(5).add("Maanya");
        Network.get(3).add("Sachin");
        Network.get(3).add("Sakshi");
        printNetwork();
        startInteraction();
    }
    //this function interacts with the user to get input for actions to be performed
    public static void startInteraction() {
        System.out.println("********************************");
        System.out.println("----- You can Choose from one of the Following options ----- ");
        System.out.println("You can either ----- exit, addMembers, getMembers, setRelation, PrintNetwork, seePath(x,y) or hopTree(x,1) ---- ");
        System.out.print("------->");
        Scanner input = new Scanner(System.in);
        String userInput = input.nextLine();
        userInput = userInput.trim();
        userInput = userInput.toLowerCase();
        if (userInput.equals("exit")) {
            System.out.println("Have a nice day");
        } else if (userInput.equals("addmembers")) {
            addMembers();
        } else if (userInput.equals("printnetwork")) {
            printNetwork();
        }else if (userInput.equals("setrelation")){
            setRelation();
        }else if(userInput.equals("getmembers")) {
            printVertexes();
        }else if (userInput.substring(0, 7).equals("seepath")){
            String[] pathBetween = userInput.substring(8).split(",");
            String first = toProperCase(pathBetween[0]);
            String second = toProperCase(pathBetween[1].substring(0, pathBetween[1].length()-1));
            if (!Members.contains(first) || !Members.contains(second)) {
                System.out.println("Member(s) does not exist in the network, try again");
                startInteraction();
            }else if(first.equals(second)){
                System.out.println("**Its the same person**");
                startInteraction();
            } else{
                //steps.add(first);
                //ArrayList<String> path =
                seePath(first, second);
                if(found) {

                    //  System.out.println(path);
                }else {
                    System.out.println("!!!!! There is no relation between the two. !!!!!");
                }
                startInteraction();
            }
        } else if (userInput.substring(0, 7).equals("hoptree")) {
            String[] arguments = userInput.substring(8).split(",");
            String person = toProperCase(arguments[0]);
            try {
                int hops = Integer.valueOf(arguments[1].replace(")",""));
                System.out.print(hops);
                if(!Members.contains(person)){

                }else{
                    if(hops<0){
                        System.out.println(" Please enter a valid value");
                    }else {
                        hopTree(person, hops);
                    }
                }
            }catch (NumberFormatException e){
                System.out.print("incorrect format try again");
            }

        }else{
            System.out.println("Enter a valid input value");
            startInteraction();
        }
    }

    // Following function will add new members to the members list formated properly
    public static void addMembers () {
        System.out.println("Enter the name, if more than 1 enter seperated by (,) ");
        Scanner input = new Scanner(System.in);
        String enteredData = input.nextLine();
        String[] members = enteredData.split(",");
        for (int i = 0; i < members.length; i++) {
            checkAndAdd(members[i]);
        }
        System.out.println("Members added to the network");
        System.out.println("********************************");
        System.out.println("Updated network is as below");
        printVertexes();
        System.out.println();

        startInteraction();
    }
    //prints all the members in the graph
    public static void printVertexes() {
        int n = Members.size();
        for (int i = 0; i < n; ++i) {
            System.out.println(Members.get(i));
        }
        startInteraction();
    }
    //Following function prints the complete network
    //prints Node----->connections
    public static void printNetwork(){
        int len = Network.size();
        System.out.println(len);
        for(int i = 0; i <len; i++){
            System.out.print(Members.get(i)+ "  ----->  ");
            System.out.print(Network.get(i));
            System.out.println();

        }
        startInteraction();
    }
    //Following function strips the white spaces and return full name in proper case
    //Caps first character of first and last name
    public static String toProperCase(String tempName){
        tempName = tempName.trim();
        String [] fullname = tempName.split(" ");
        String FullName = new String();
        int l = fullname.length;
        if (l>1){
            for(int i = 0; i <l-1; i++ ) {
                if (fullname[i] == " ") {

                } else {
                    String fname = new String();
                    try {
                        fname = fullname[i].substring(0, 1).toUpperCase() + fullname[i].substring(1).toLowerCase() + " ";
                    }catch (StringIndexOutOfBoundsException e){

                    }

                    FullName += fname;
                }
            }
            FullName += fullname[l-1].substring(0, 1).toUpperCase() + fullname[l-1].substring(1).toLowerCase();
        }else {
            FullName = tempName.substring(0, 1).toUpperCase() + tempName.substring(1).toLowerCase();
        }
        return FullName;
    }
    public static void checkAndAdd(String name){
        String memberToBeAdded = toProperCase(name);
        if(Members.indexOf(memberToBeAdded) > 0){
            //dont do any thing if it already exist in the Members list
        }else {
            Members.add(memberToBeAdded);
            ArrayList<String> branch = new ArrayList<String>();
            Network.add(branch);
        }
    }
    public static void setRelation() {//, String relation){
        System.out.println("Set the relation ships, first enter the member u want to enter to");
        System.out.println("Member you want to add to ----> ");
        Scanner input = new Scanner(System.in);
        String memberEntered = input.next();
        memberEntered = toProperCase(memberEntered);
        int indexNumber = Members.indexOf(memberEntered);
        if (indexNumber < 0){
            System.out.println("*******Enter a valid member name*******");
            setRelation();
        }else {
            System.out.println("Members is at  -  " + indexNumber);
            System.out.println("Enter the members directly connected, if more than 1 enter seperated by (,) ");
            Scanner inputLinks = new Scanner(System.in);
            String membersToBeAdded = inputLinks.nextLine();
            String[] addMembersToBranch = membersToBeAdded.split(",");
            for (int i = 0; i < addMembersToBranch.length; i++) {
                String addMemberToBranch = toProperCase(addMembersToBranch[i]);
                checkAndAdd(addMembersToBranch[i]);
                if(Network.get(indexNumber).indexOf(addMemberToBranch)< 0) {
                    Network.get(indexNumber).add(addMemberToBranch);
                    Network.get(Members.indexOf(addMemberToBranch)).add(memberEntered);
                }
            }
            printNetwork();
            startInteraction();
        }



    }

    private static  ArrayList<String> checked = new ArrayList<String>(); //add already checked so no reps are done
    private static  /*ArrayList<String>*/ int  steps = 0;//new ArrayList<String>();//

    private static boolean found = false;
    private static int level = 0;

    private static  ArrayList<String>  temp = new ArrayList<String>();//
    private  static ArrayList<String> allreadyCheckedPath = new ArrayList<String>();
    public static class parentChild{
        String parent;
        ArrayList<String> child;
        public parentChild(String p, ArrayList<String> c) {
            parent = p;
            child = c;
        }
        public boolean isParent(String p) {
            return p.equals(parent);
        }
    }
    private static ArrayList<parentChild> path = new ArrayList<parentChild>();
    public static void /*ArrayList<String>*/ seePath(String first, String second) {

        ArrayList<String> level = Network.get(Members.indexOf(first));
        parentChild tempStep = new parentChild(first, level);
        System.out.println("checking in   ---" + level);
        path.add(tempStep);
        if(level.contains(second)){
            System.out.println("found in "+ level);
            return;// steps;
        }else{
            allreadyCheckedPath.addAll(level);
            System.out.println("allreadyChecked "+ allreadyCheckedPath);
            int levelSize = level.size();
            for(int i = 0; i<levelSize; i++) {
                getNextLevel(level.get(i),second);//prepare next set of temp array for us
            }
            steps = steps+1;
            ifDestisThere(second); //search in temp array
            // return steps;
        }

    }
    //prepare temp array
    private static void getNextLevel(String startPt, String second){
        ArrayList<String> nextMoves = Network.get(Members.indexOf(startPt));
        System.out.println("next set of movers are    "+nextMoves +" startpoint is  "+ startPt+ "   src is   "+ startPt );
        for (int nextM = 0; nextM < nextMoves.size(); nextM++) {
            if (temp.contains(nextMoves.get(nextM))) {

            } else {
                temp.add(nextMoves.get(nextM));

            }

        }
        System.out.println("temp is   ---" + temp);
        temp.removeAll(allreadyCheckedPath);
        System.out.println("removed checked   " + temp);


    }
    //check in each of temp array elements
    public static void ifDestisThere(String second){
        //check in each of moves in temp

        if (temp.contains(second)){
            System.out.println("found in "+ temp);
            return;// steps;
        }else {
            steps=steps+1;
            for (int t = 0; t < temp.size(); t++) {
                ArrayList<String> a = Network.get(Members.indexOf(temp.get(t)));
                if (a.contains(second)) {
                    System.out.println("found in " + temp.get(t));
                    return;// steps;
                }
            }
            // prepare next temp
            ArrayList<String> temp2 =  new ArrayList<String>(temp);

            System.out.println("*************Cleared old temp**********");
            temp.clear();
            System.out.println("****new temp*****" + temp2);

            System.out.println("***allreadychecked*****" + allreadyCheckedPath);
            for (int tempitemp = 0; tempitemp < temp2.size(); tempitemp++) {

                allreadyCheckedPath.add(temp2.get(tempitemp));
                getNextLevel(temp2.get(tempitemp),second);
            }

            ifDestisThere(second);
        }
        //return steps;
    }
//    /ArrayList<ArrayList<String>>  temp = new ArrayList<ArrayList<String>>();



    /*

                int thisMemberLocation = Members.indexOf(first);
                // int secondMemberLocation = Members.indexOf(second);
                ArrayList<String> thisLevel = Network.get(thisMemberLocation); //get all the direct members of this member
                thisLevel.removeAll(steps); //remove all the members we have allready checked
                //checked.addAll(thisLevel);
                if (thisLevel.contains(second)) {
                    //steps.add(first);
                    found = true;
                    steps.add(second);

                    //return steps;
                } else {

                    ArrayList<ArrayList<String>>  temp = new ArrayList<ArrayList<String>>();
                    for(int i = 0 ; i< thisLevel.size(); i++){
                        temp.add(getlevel(thisLevel.get(i)));
                    }
                 System.out.println(temp);
                }
            return steps;
        }
    */
    private static ArrayList<String> getlevel (String member){
        int memPos = Members.indexOf(member);
        ArrayList<String> level = Network.get(memPos);
        //temp.removeAll(allReadyAdded);
        //nextLevel.addAll(temp);
        //allReadyAdded.addAll(temp);
        return level;
    }
    private static ArrayList<ArrayList<String>> tree = new ArrayList<ArrayList<String>>();
    private static int count = 0;
    private static ArrayList<String> allReadyAdded = new ArrayList<String>();
    public static void hopTree(String person, int hops) {
        if (hops == 0) {
            System.out.println(person);
        } else {
            ArrayList<String> nextLevel = new ArrayList<String>();
            nextLevel.add(person);
            tree.add(nextLevel);

            allReadyAdded.add(person);
            while (count< hops) {
                //keep gettting next levels till the desired hops value
                getLevel(tree.get(count), hops);
                count++;
            }
            System.out.println(tree);
        }
    }
    public static void getLevel (ArrayList<String> level, int hops){
        int levelSize = level.size();
        if(levelSize>0) {
            ArrayList<String> nextLevel = new ArrayList<String>();

            for (int i = 0; i < levelSize; i++) {
                int memPos = Members.indexOf(level.get(i));
                ArrayList<String> temp = Network.get(memPos);
                temp.removeAll(allReadyAdded);
                nextLevel.addAll(temp);
                allReadyAdded.addAll(temp);
            }
            tree.add(nextLevel);
        }

    }
}