import java.util.ArrayList;

public class Jumper {

    public static final String inputFile = "src/buildings.txt";
    public static final String outputFile = "src/outcome.txt";
    public static final String FORWARD = "forward";
    public static final String BACKWARD = "backward";
    private Player player;
    private Dimension dimension;

    public static void main(String[] args)  //static method
    {
        Jumper jumperObj = new Jumper();
        jumperObj.readFile();
        jumperObj.startProgram();
    }

    public Jumper()
    {
        this.player = new Player();
        this.dimension = new Dimension();
    }

    public void startProgram()
    {
        promptUserName();
        displayWorld();
        promptUserInput();
    }

    public void promptUserName()
    {
        this.player.setName(Input.playerNameInput("Please enter your name (3 - 12 characters only):"));
    }

    public String displayCurrentPlayerPosition()
    {
        String dimensionContent = "";

        ArrayList<Building> buildings = this.dimension.getBuildings();
        for (int i = 0; i < buildings.size(); i++)
        {
            if (this.player.getCurrentBuilding() == buildings.get(i).getBuildingNumber())
            {
                dimensionContent += "   P   ";
            }
            else {
                dimensionContent += "       ";
            }
        }
        return dimensionContent;
    }

    public String displayGameLegends()
    {
        return "\n| | = Storey, P = Player, F = Fuel Cell, X = Exit Portal, W = Web Police, ! = Frozen Building\n";
    }

    public void displayWorld()
    {
        System.out.println(this.player.displayPlayerInfo());
        System.out.println(displayGameLegends());
        System.out.println(displayCurrentPlayerPosition());
        System.out.println(this.dimension.displayDimension());
    }

    public void promptUserInput()
    {
        String optionContent = "";
        String[] optionArray = {"Press 1 to jump forward", "Press 2 to jump backward", "Press 3 to skip a turn"};
        for (String s : optionArray) {
            optionContent += s + "\n";
        }

        boolean validNumber = false;

        while (!validNumber)
        {
            int userInput = Input.acceptIntegerInput(optionContent);

            if (userInput == 1)
            {
                //Jump forward
                if (this.dimension.canPlayerJump(this.player.getCurrentBuilding(), Jumper.FORWARD)) {
                    playerJump();
                    validNumber = true;
                }
                else {
                    System.out.println("The Player cannot jump as it will break game rule, please choose another option");
                }

            }
            else if (userInput == 2)
            {
                //Jump backward
                if (this.dimension.canPlayerJump(this.player.getCurrentBuilding(), Jumper.BACKWARD)) {
                    playerJump();
                    validNumber = true;
                }
                else {
                    System.out.println("The Player cannot jump as it will break game rule, please choose another option");
                }
            }
            else if (userInput == 3)
            {
                //Stay still
                validNumber = true;
            }
            else
            {
                System.out.println("An option for " + userInput + " does not exist");
            }
        }

    }

    public void playerJump()
    {

    }

    public void playerStayed()
    {

    }

    public void saveStatistics()
    {

    }

    public void readFile()
    {
        FileIO ioObj = new FileIO(inputFile);

        String fileContent = ioObj.readFile();

        String[] individualBuilding = fileContent.split("\n");

        for (int i = 0; i < individualBuilding.length; i++)
        {
            String[] buildingInfo = individualBuilding[i].split(",");
            int buildingNumber = i + 1;
            int height = Integer.parseInt(buildingInfo[0]);
            boolean exitPortal = Boolean.parseBoolean(buildingInfo[1].toLowerCase());
            boolean fuelCell = Boolean.parseBoolean(buildingInfo[2].toLowerCase());
            boolean web = Boolean.parseBoolean(buildingInfo[3].toLowerCase());
            boolean freeze = Boolean.parseBoolean(buildingInfo[4].toLowerCase());

            this.dimension.addBuilding(buildingNumber, height, exitPortal, fuelCell, web, freeze);
        }

        //System.out.println(this.dimension.printBuilding());
    }

    public void writeFile()
    {

    }
}
