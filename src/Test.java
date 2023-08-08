public class Test {

    public Test()
    {

    }

    public void testDefaultConstructorPositive()
    {
        Player player = new Player();

        System.out.println(player.playerTestingInformation());
    }

    public void getCurrentBuildingMethodPositive()
    {
        Player player = new Player();

        player.setCurrentBuilding(3);
        System.out.println("Player Current Building: " + player.getCurrentBuilding());
    }

    public void getCurrentBuildingMethodNegative()
    {
        Player player = new Player();

        player.setCurrentBuilding(-5);
        System.out.println("Player Current Building: " + player.getCurrentBuilding());
    }

    public void getFuelCellMethodPositive()
    {
        Player player = new Player();
        player.setFuelCellFound(4);

        System.out.println("Fuel Cell: " + player.getFuelCellFound());
    }

    public void getFuelCellMethodNegative()
    {
        Player player = new Player();
        player.setFuelCellFound(-5);

        System.out.println("Fuel Cell: " + player.getFuelCellFound());
    }

    public void getDevice()
    {
        Player player = new Player();

        System.out.println("Device battery: " + player.getDevice().getDeviceBattery());
    }

    public void isWinGameMethod()
    {
        Player player = new Player();

        System.out.println("Win game: " + player.isWinGame());
    }

    public void setCurrentBuildingPositive()
    {
        Player player = new Player();
        player.setCurrentBuilding(13);

        System.out.println("Current building: " + player.getCurrentBuilding());
    }

    public void setCurrentBuildingNegative()
    {
        Player player = new Player();
        player.setCurrentBuilding(0);

        System.out.println("Current building: " + player.getCurrentBuilding());
    }

    public void setFuelCellPositive()
    {
        Player player = new Player();
        player.setFuelCellFound(4);

        System.out.println("Fuel Cell: " + player.getFuelCellFound());
    }

    public void setFuelCellNegative()
    {
        Player player = new Player();
        player.setFuelCellFound(-4);

        System.out.println("Fuel Cell: " + player.getFuelCellFound());
    }

    public void setDevice()
    {
        Player player = new Player();
        Device device = new Device();

        device.setDeviceBattery(10);
        player.setDevice(device);

        System.out.println("Device battery: " + player.getDevice().getDeviceBattery());
    }

    public void setNamePositive()
    {
        Player player = new Player();
        player.setName("daffa");

        System.out.println(player.playerTestingInformation());
    }

    public void setNameNegative()
    {
        Player player = new Player();
        player.setName("22");

        System.out.println(player.playerTestingInformation());
    }

    public void setWinGame()
    {
        Player player = new Player();
        player.setWinGame(true);

        System.out.println(player.playerTestingInformation());
    }

    public void addDeviceBatteryPositive()
    {
        Player player = new Player();
        Device device = new Device();
        device.setDeviceBattery(8);
        player.setDevice(device);
        player.addDeviceBattery(10);

        System.out.println(player.playerTestingInformation());
    }

    public void addDeviceBatteryNegative()
    {
        Player player = new Player();
        Device device = new Device();
        device.setDeviceBattery(3);
        player.setDevice(device);
        player.addDeviceBattery(41);

        System.out.println(player.playerTestingInformation());
    }

    public void removeDeviceBatteryPositive()
    {
        Player player = new Player();
        Device device = new Device();
        device.setDeviceBattery(5);

        player.setDevice(device);
        player.removeDeviceBattery(2);

        System.out.println(player.playerTestingInformation());
    }

    public void removeDeviceBatteryNegative()
    {
        Player player = new Player();
        Device device = new Device();
        device.setDeviceBattery(5);

        player.setDevice(device);
        player.removeDeviceBattery(12);

        System.out.println(player.playerTestingInformation());
    }
}
