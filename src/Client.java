public class Client {
    public static void main(String args[]){
        Input inp = new Input(); //Object that goes through input loop
        Startup start = new Startup(); //Gets player details, runs SetWorld
        String version = "0.0.2";
        String icon = "#############\n#           #\n#   Gray    #\n#           #\n#############";        
        System.out.println("\n" + icon + "\nVersion: " + version + "\n");
        Player play = start.getDetails(); //Gets player details
        while(inp.playing){ //This loop runs the whole game, see the cmd method
            if(play.getAlive() == false){ //If player dies, game ends
                break;
            }
            inp.cmd(play.location, play);
        }
        System.out.println("\nGoodbye, traitor");
    }
}