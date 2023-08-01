public class Building {

    private int buildingNumber;
    private int height;
    private boolean exitPortal, fuelCell, web, freeze;

    public Building()
    {
        this.buildingNumber = 0;
        this.height = 0;
        this.exitPortal = this.fuelCell = this.web = this.freeze = false;
    }

    public Building(int number, int height,
                    boolean exitPortal, boolean fuelCell, boolean web, boolean freeze)
    {
        this.buildingNumber = number;
        this.height = height;
        this.exitPortal = exitPortal;
        this.fuelCell = fuelCell;
        this.web = web;
        this.freeze = freeze;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(int buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isExitPortal() {
        return exitPortal;
    }

    public void setExitPortal(boolean exitPortal) {
        this.exitPortal = exitPortal;
    }

    public boolean isFuelCell() {
        return fuelCell;
    }

    public void setFuelCell(boolean fuelCell) {
        this.fuelCell = fuelCell;
    }

    public boolean isWeb() {
        return web;
    }

    public void setWeb(boolean web) {
        this.web = web;
    }

    public boolean isFreeze() {
        return freeze;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }
}
