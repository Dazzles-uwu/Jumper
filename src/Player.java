public class Player {

    public static int turn = 0;
    private String name;
    private boolean winGame;
    private int fuelCellFound;
    private Device device;
    private int currentBuilding;

    public Player()
    {
        this.name = "John Doe";
        this.winGame = false;
        this.fuelCellFound = 0;
        this.device = new Device();
        this.currentBuilding  = 1;
    }

    public static int getTurn() {
        return turn;
    }

    public static void setTurn(int turn) {
        Player.turn = turn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFuelCellFound() {
        return fuelCellFound;
    }

    public void setFuelCellFound(int fuelCellFound) {
        this.fuelCellFound = fuelCellFound;
    }

    public boolean isWinGame() {
        return winGame;
    }

    public void setWinGame(boolean winGame) {
        this.winGame = winGame;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public int getCurrentBuilding() {
        return currentBuilding;
    }

    public void setCurrentBuilding(int currentBuilding) {
        this.currentBuilding = currentBuilding;
    }
}
