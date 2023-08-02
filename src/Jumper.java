import java.util.ArrayList;

public class Jumper {

    public static final String inputFile = "src/buildings.txt";
    public static final String outputFile = "src/outcome.txt";
    public static final String FORWARD = "forward";
    public static final String BACKWARD = "backward";
    public static final String RECHARGE = "recharge";
    public static final String DEPLETE = "deplete";
    public static final String STAY = "stay";
    public static final String WEB = "web";
    private Player player;
    private Dimension dimension;
    private boolean gameOver;

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
        this.gameOver = false;
    }

    public void startProgram()
    {
        promptUserName();
        while (!this.gameOver)
        {
            displayWorld();
            promptUserInput();
        }

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
                    playerJump(Jumper.FORWARD);
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
                    playerJump(Jumper.BACKWARD);
                    validNumber = true;
                }
                else {
                    System.out.println("The Player cannot jump as it will break game rule, please choose another option");
                }
            }
            else if (userInput == 3)
            {
                //Stay still
                playerStayed();
                validNumber = true;
            }
            else
            {
                System.out.println("An option for " + userInput + " does not exist");
            }
        }

    }

    public void playerJump(String lateralMovement)
    {
        int newPlayerPosition = this.dimension.getPlayerNewBuildingPosition(this.player.getCurrentBuilding(), lateralMovement);
        int currentPlayerPosition = this.player.getCurrentBuilding();

        if (this.dimension.isFuelCell(newPlayerPosition))
        {
            int cellAmountFound = this.player.getFuelCellFound();
            this.player.setFuelCellFound(cellAmountFound + 1);
            calculateBattery(Jumper.RECHARGE, currentPlayerPosition, newPlayerPosition);
        }

        if (this.dimension.isWebTrapped(newPlayerPosition))
        {
            calculateBattery(Jumper.WEB, currentPlayerPosition, newPlayerPosition);
        }

        //This function always runs because it always cost fuel to jump
        calculateBattery(Jumper.DEPLETE, currentPlayerPosition, newPlayerPosition);

        if (newPlayerPosition != 0)
        {
            this.player.setCurrentBuilding(newPlayerPosition);
        }
        Player.turn++;
    }

    public void playerStayed()
    {
        //We placed some random arbitrary number inside the second and third parameter as we do not need it for stay
        calculateBattery(Jumper.STAY, 0, 0);
        Player.turn++;
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

    public void calculateBattery(String jumperStatus, int currentPlayerPosition, int newPlayerPosition)
    {
        int currentCharge = this.player.getDeviceBattery();

        if (jumperStatus.equals(Jumper.RECHARGE))
        {
            this.player.addDeviceBattery(5);
        }
        if (jumperStatus.equals(Jumper.WEB))
        {
            if (currentCharge - 5 <= 0)
            {
                this.gameOver = true;
            }
            this.player.removeDeviceBattery(5);
        }
        if (jumperStatus.equals(Jumper.DEPLETE))
        {
            int buildingOneHeight = this.dimension.getPlayerJumpHeight(currentPlayerPosition);
            int buildingTwoHeight = this.dimension.getPlayerJumpHeight(newPlayerPosition);
            int jumpCost = Math.abs(buildingOneHeight - buildingTwoHeight) + 1;

            //Checks to see if there is anymore battery left
            if (currentCharge - jumpCost <= 0)
            {
                this.gameOver = true;
            }
            this.player.removeDeviceBattery(jumpCost);
        }
        if (jumperStatus.equals(Jumper.STAY))
        {
            if (currentCharge - 1 <= 0)
            {
                this.gameOver = true;
            }
            this.player.removeDeviceBattery(1);
        }
    }
}
