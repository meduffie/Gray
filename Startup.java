import java.util.*;
public class Startup{
    public Player getDetails(){
        Scanner scan = new Scanner(System.in);
        World aa = new World();
        aa.setWorld(); //This basically sets up everything in the world, relationships, items, etc
        Player temp = new Player(null,1,100,aa.Spawn,null,true,0,1);
        temp.addInv(0,aa.chocBar);
        boolean running = true;
        whole: while(running){
            System.out.print("Name: ");
            String tname = scan.nextLine();
            if(tname.equalsIgnoreCase("Quit")){
                System.out.print("Really quit? (Y/n): ");
                if(scan.nextLine().equalsIgnoreCase("Y") || scan.nextLine().equalsIgnoreCase("Yes")){
                    temp.ali = false;
                    break whole;
                }
                else{
                    continue whole;
                }
            }
            if(!running){
                break;
            }
            temp.setName(tname);
            System.out.println("Welcome " + temp.getName() + '!');
            boolean trying = true;
            String xpa ="";
            int xpaa = 0;
            while(trying){
                try{
                    System.out.print("Age: ");
                    xpa = scan.nextLine();
                    if(xpa.equalsIgnoreCase("Quit")){
                        System.out.print("Really quit? (Y/n): ");
                        boolean rq;
                        if(scan.nextLine().equalsIgnoreCase("Y") || scan.nextLine().equalsIgnoreCase("Yes")){
                            temp.ali = false;
                            break;
                        }
                        else{
                            break;
                        }
                    }
                    xpaa = Integer.parseInt(xpa);
                    if(xpaa >= 0){
                        trying = false;
                    }
                }
                catch(NumberFormatException e){
                    System.err.println("Not a number, try again!");
                }
            }
            temp.setAge(xpaa);
            System.out.println("Hello human, welcome to Gray");
            System.out.println("Type \"Help\" for available commands! (New players should do this!)");
            System.out.println("\n" + aa.Spawn.getDesc());
            running = false;
        }
        return temp;
    }
}