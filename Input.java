import java.util.*;
public class Input {
    boolean playing = true; //Has the user typed "Quit"?
    public void cmd(Room a, Player b){
        Scanner p2 = new Scanner(System.in);
        System.out.print("> "); //Cursor
        String decision = p2.nextLine(); //Whatever user types
        if(decision.equalsIgnoreCase("look")){ //Gives room description
            System.out.println(a.getDesc());
            if(!a.mobs.isEmpty()){ //If there ARE mobs in the room (They are stored in an Array)
                System.out.println("Mobs: ");
                a.printMobs();
            }
            //System.out.println("Here are your available exits: ");
            //if(a.canN()){
            //    System.out.println("North: " + a.getN().getName());
            //}
            //if(a.canE()){
            //    System.out.println("East: " + a.getE().getName());
            //}
            //if(a.canS()){
            //   System.out.println("South: " + a.getS().getName());
            //}
            //if(a.canW()){
            //    System.out.println("West: " + a.getW().getName());
            //}
        }
        else if(decision.equalsIgnoreCase("N") || decision.equalsIgnoreCase("North")){
            if(a.canN()){ //If there is a North Exit for this room
                b.setLoc(a.nR); //Sets new Location (nR = North Room)
                a = b.getLoc(); //Sets current Room to Player location
                System.out.println(a.getDesc()); //Prints new Location
            }
            else{ //If there is no accessible North Exit
                System.out.println("Can't go this way!");
            }
        }
        else if(decision.equalsIgnoreCase("E") || decision.equalsIgnoreCase("East")){
            if(a.canE()){ //If there is a East Exit for this room
                b.setLoc(a.eR); //Sets new Location (eR = East Room)
                a = b.getLoc(); //Sets current Room to Player location
                System.out.println(a.getDesc()); //Prints new Location
            }
            else{ //If there is no accessible East Exit
                System.out.println("Can't go this way!");
            }
        }
        else if(decision.equalsIgnoreCase("S") || decision.equalsIgnoreCase("South")){
            if(a.canS()){ //If there is a South Exit for this room
                b.setLoc(a.sR); //Sets new Location (sR = South Room)
                a = b.getLoc(); //Sets current Room to Player location
                System.out.println(a.getDesc()); //Prints new Location
            }
            else{ //If there is no accessible South Exit
                System.out.println("Can't go this way!");
            }
        }
        else if(decision.equalsIgnoreCase("W") || decision.equalsIgnoreCase("West")){
            if(a.canW()){ //If there is a West Exit for this room
                b.setLoc(a.wR); //Sets new Location (wR = West Room)
                a = b.getLoc(); //Sets current Room to Player location
                System.out.println(a.getDesc()); //Prints new Location
            }
            else{ //If there is no accessible West Exit
                System.out.println("Can't go this way!");
            }
        }
        else if(decision.equalsIgnoreCase("Help")){
            System.out.println("Here are the commands available for you in NeuWald: ");
            System.out.println("N, E, S or W -- Go N, E, S or W\nLook -- Get the room Description!"
            + "\nExits -- Get a list of exits for this room" + "\nInv -- Get a printout of your Inventory!" +
            "\nEat <Item> -- Eat an item in your Inventory (Make sure you enter the item name!)" + 
            "\nStats -- Lists your current player stats" + "\nFight <monster> -- Starts fight with specified monster" + 
            "\nQuit -- Quit the game\n(More commands in \"Help 2\")");
        }
        else if(decision.equalsIgnoreCase("Help 2")){
            System.out.println("Help Page 2: ");
            System.out.println("Room items -- Prints out all the Items in your current room");
            System.out.println("Pickup <Item> -- Picks up specified Item");
            System.out.println("Equip <Weapon in Inv> -- Equips weapon, places it in Equipped slot, out of Inv");
            System.out.println("Unequip <Weapon that IS equipped> -- Unqeuips weapon, places it Inv");
        }
        else if(decision.equalsIgnoreCase("Quit")){
            System.out.print("Really quit? (Y/n): ");
            decision = p2.nextLine();
            if(decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("Yes")){
                playing = false;
            }
            else{
                System.out.println("And now back to our regularly scheduled programming: ");
                System.out.println(a.getDesc());
            }
        }
        else if(decision.equalsIgnoreCase("Exits")){
            System.out.println("Here are your available exits: ");
            if(a.canN()){
            System.out.println("North: " + a.getN().getName());
            }
            if(a.canE()){
                System.out.println("East: " + a.getE().getName());
            }
            if(a.canS()){
               System.out.println("South: " + a.getS().getName());
            }
            if(a.canW()){
                System.out.println("West: " + a.getW().getName());
            }
        }
        else if(decision.equalsIgnoreCase("Inv")){
            ArrayList<Item> inva = new ArrayList<>(); //Temporary inventory object
            inva = b.getInv(); //Copies player inv
            int sn = 1; //Current slot
            for(Item aa : inva){ //For each item in the inventory
                System.out.println("Slot " + sn + ": " + aa.getName());
                sn++; //Go up to slot 2
            }
            if(inva.isEmpty()){
                System.out.println("You don't have anything in here!");
            }
        }
        else if(decision.length()> 3 && decision.substring(0,3).equalsIgnoreCase("Eat")){
            String foode = decision.substring(4);
            int s = b.whereis(foode); //Finds the inv slot where the food is
            if(s == -1){ //-1 means there is not food there
                System.out.println("You don't have that!");
            }
            if(s != -1){
                Item tempI = b.inv.get(s); //Find the slot it's in, and copy the item
                if(tempI.amiFood(tempI)){
                    Food tempF = (Food) tempI; //Cast the Item to type Food
                    b.Eat(tempF, s); //Eat the food, remove the food in slot "s"
                    System.out.println("Om nom nom! HP: " + b.getHp()); 
                }
            }
        }
        else if(decision.equalsIgnoreCase("Stats")){
            b.printStats();
        }
        else if(decision.equalsIgnoreCase("Room Items")){
            a.printI();
        }
        else if(decision.length() > 6 && decision.substring(0,5).equalsIgnoreCase("Equip")){
            String wea = decision.substring(6);
            int sl = b.whereis(wea); //Where s the item
            if(sl == -1){
                System.out.println("You don't have that!");
            }
            else if(sl != -1){ //If the item exists in inv
                b.equip(sl); //Equip the item to the player
            }
        }
        else if(decision.length() > 8 && decision.substring(0,7).equalsIgnoreCase("Unequip")){
            String wuq = decision.substring(8);
            //wAct = Weapon active. Below: if there is an equipped weapon and it's what the user specified
            if(b.wAct != null && b.wAct.getName().equalsIgnoreCase(wuq)){
                if(b.inv.size() < 6){ //If there is room in the inv to put the active weapon in
                    Item putback = b.wAct;
                    b.wAct = null;
                    b.addInv(putback);
                    System.out.println("Unequipped " + putback.getName());
                }
                else{
                    System.out.println("Can't unequip! You're inv is full!");
                }
            }
            else if(b.wAct != null && !b.wAct.getName().equalsIgnoreCase(wuq)){
                System.out.println("That weapon isn't equipped!");
            }
            else{
                System.out.println("You don't have anything equipped!");
            }
        }
        else if(decision.length() > 7 && decision.substring(0,6).equalsIgnoreCase("Pickup")){
            String pik = decision.substring(7);
            int pikl = a.whereis(pik);
            if(pikl == -1){
                System.out.println("That doesn't exist here");
            }
            else if(pikl != -1){
                b.addInv(a.getI(pikl));
                a.remI(pikl);
            }
        }
        else if(decision.length() > 6 && decision.substring(0,5).equalsIgnoreCase("Fight")){
            String mobb = decision.substring(6);
            int mobpos = a.whereisMob(mobb);
            if(mobpos == -1){
                System.out.println("Either that monster isn't in this room, or you misspelled something");
            }
            else if(mobpos != -1){
                boolean x = b.fight(a.mobs.get(mobpos));
                if(x){
                    b.won++;
                    a.mobs.remove(mobpos);
                }
                else{
                    b.lost++;
                }
                System.out.println(a.getDesc());
            }
        }
        else{
            System.out.println("Incorrect input, type \"Help\" for commands");
        }
    }
}