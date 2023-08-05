import java.util.ArrayList;

public class Jumper {

    public static final String INPUT_FILE = "src/buildings.txt";
    public static final String OUTPUT_FILE = "src/outcome.txt";
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
        if (!jumperObj.gameOver) {
            jumperObj.startProgram();
        } else {
            System.out.println("Cannot Read File or Internal Server Error");
        }
        jumperObj.writeFile();
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
            this.dimension.changeDimension();
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

        checkItemsPlayerLandsOn(currentPlayerPosition, newPlayerPosition);

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
        int currentPlayerPosition = this.player.getCurrentBuilding();

        checkItemsPlayerLandsOn(currentPlayerPosition, currentPlayerPosition);

        //We placed some random arbitrary number inside the second and third parameter as we do not need it for stay
        calculateBattery(Jumper.STAY, this.player.getCurrentBuilding(), this.player.getCurrentBuilding());

        Player.turn++;
    }

    public void checkItemsPlayerLandsOn(int currentPosition, int newPosition)
    {
        //Operations if there is a Fuel Cell in new Player Position
        if (this.dimension.isFuelCell(newPosition))
        {
            int cellAmountFound = this.player.getFuelCellFound();
            this.player.setFuelCellFound(cellAmountFound + 1);
            this.dimension.setFuelCellOff(newPosition);
            calculateBattery(Jumper.RECHARGE, currentPosition, newPosition);
        }

        //Operations if there is a Web in the player's current position
        if (this.dimension.isWebTrapped(newPosition))
        {
            calculateBattery(Jumper.WEB, currentPosition, newPosition);
        }

        //Checks to see if the game is over and the player has won
        if (this.player.getDeviceBattery() >= 0 && this.dimension.isExitPortal(newPosition))
        {
            if (this.dimension.isBuildingFrozen(newPosition)) {
                System.out.println("Cannot end the game you are Jumping on a frozen building. Please skip a turn");
            }
            else {
                this.player.setWinGame(true);
                this.gameOver = true;
                System.out.println("Congratulations " + this.player.getName() + ", you have won the game!");
            }
        }
    }

    public void readFile()
    {
        FileIO ioObj = new FileIO(INPUT_FILE);

        String fileContent = ioObj.readFile();

        if (Validation.isBlank(fileContent.trim()))
        {
            this.gameOver = true;
            System.out.println("Unable to read or empty text file");
        } else {
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
        }
        //System.out.println(this.dimension.printBuilding());
    }

    public void writeFile()
    {
        FileIO ioObj = new FileIO(OUTPUT_FILE);

        String gameContent = Player.turn + "," + this.player.getFuelCellFound() + ",";

        if (this.player.isWinGame())
        {
            gameContent += "win,";
        }
        else {
            gameContent += "lose,";
        }

        gameContent += this.player.getName();
        ioObj.writeFile(gameContent);
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
                System.out.println("Unfortunate " + this.player.getName() + ", you have lost the game as your device has no charge left");
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
                System.out.println("Unfortunate " + this.player.getName() + ", you have lost the game as your device has no charge left");
                this.gameOver = true;
            }
            this.player.removeDeviceBattery(jumpCost);
        }
        if (jumperStatus.equals(Jumper.STAY))
        {
            if (currentCharge - 1 <= 0)
            {
                System.out.println("Unfortunate " + this.player.getName() + ", you have lost the game as your device has no charge left");
                this.gameOver = true;
            }
            this.player.removeDeviceBattery(1);
        }
    }
}
