public class Player {

    public static int turn = 0;
    private int currentBuilding;
    private int fuelCellFound;
    private Device device;
    private String name;
    private boolean winGame;


    public Player()
    {
        this.name = "John Doe";
        this.winGame = false;
        this.fuelCellFound = 0;
        this.device = new Device();
        this.currentBuilding = 1;
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

    public void setName(String name)
    {
        this.name = name.substring(0, 1).toUpperCase() + name.substring(1);
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

    public int getDeviceBattery()
    {
        return this.device.getDeviceBattery();
    }

    public void addDeviceBattery(int charge)
    {
        int currentCharge = this.device.getDeviceBattery();
        if (currentCharge + charge > 20)
        {
            this.device.setDeviceBattery(20);
            System.out.println("You have reached a Maximum amount of 20 points on your jumper device");
        }
        else {
            this.device.setDeviceBattery(currentCharge + charge);
        }
    }

    public void removeDeviceBattery(int charge)
    {
        int currentCharge = this.device.getDeviceBattery();
        this.device.setDeviceBattery(currentCharge - charge);
    }

    public int getCurrentBuilding() {
        return currentBuilding;
    }

    public void setCurrentBuilding(int currentBuilding) {
        this.currentBuilding = currentBuilding;
    }

    public String displayPlayerInfo()
    {
        return "Player Name: " + this.name + "\n" +
                "Player Turn: " + turn + "\n" +
                "Device Fuel: " + this.device.getDeviceBattery() + "\n" +
                "Fuel Cell Found: " + this.fuelCellFound;
    }

    public String playerTestingInformation()
    {
        return "Player Name: " + this.name + "\n" +
                "Current Building: " + this.currentBuilding + "\n" +
                "Fuel Cell Found: " + this.fuelCellFound + "\n" +
                "DeviceBattery: " + this.device.getDeviceBattery() + "\n" +
                "is Win: " + this.winGame + "\n" +
                "Player Turn: " + turn + "\n";
    }

}
