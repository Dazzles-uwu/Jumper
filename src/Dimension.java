import java.util.ArrayList;

public class Dimension {

    private ArrayList<Building> buildings;
    private final int MAX_BUILDING_HEIGHT = 5;

    public Dimension()
    {
        this.buildings = new ArrayList<Building>();
    }

    public String displayDimension()
    {
        String dimensionContent = "";

        for (int i = 0; i < this.buildings.size(); i++)
        {
            if (this.buildings.get(i).isExitPortal())
            {
                dimensionContent += "   X   ";
            }
            else {
                dimensionContent += "       ";
            }
        }
        dimensionContent += "\n";

        for (int i = 0; i < this.buildings.size(); i++)
        {
            if (this.buildings.get(i).isFuelCell())
            {
                dimensionContent += "   F   ";
            }
            else {
                dimensionContent += "       ";
            }
        }
        dimensionContent += "\n";

        int createBuildingLinePointer = MAX_BUILDING_HEIGHT + 1;
        while (createBuildingLinePointer > 0)
        {
            for (int i = 0; i < this.buildings.size(); i++)
            {
                if (this.buildings.get(i).getHeight() == createBuildingLinePointer - 1)
                {
                    dimensionContent += "  _ _  ";
                }
                else if (this.buildings.get(i).getHeight() > createBuildingLinePointer - 1)
                {
                    if (createBuildingLinePointer == 1)
                    {
                        if (this.buildings.get(i).isWeb())
                        {
                            dimensionContent += " | " + "W" +" | ";
                        }
                        else if (this.buildings.get(i).isFreeze())
                        {
                            dimensionContent += " | " + "!" + " | ";
                        } else {
                            dimensionContent += " | " + this.buildings.get(i).getHeight() + " | ";
                        }
                    }
                    else {
                        dimensionContent += " |   | ";
                    }
                }
                else {
                    dimensionContent += "       ";
                }
            }
            dimensionContent += "\n";
            createBuildingLinePointer--;
        }
        return dimensionContent;
    }

    public int getPlayerJumpHeight(int playerBuilding)
    {
        if (!this.buildings.isEmpty())
        {
            // - 1 because the .get starts from 0 and playerBuilding starts from 1
            return this.buildings.get(playerBuilding - 1).getHeight();
        }
        return 0;
    }

    public int getPlayerNewBuildingPosition(int playerCurrentBuilding, String lateralMovement)
    {
        if (lateralMovement.equals(Jumper.FORWARD))
        {
            return playerCurrentBuilding + getPlayerJumpHeight(playerCurrentBuilding);
        }
        else if (lateralMovement.equals(Jumper.BACKWARD))
        {
            return playerCurrentBuilding - getPlayerJumpHeight(playerCurrentBuilding);
        }
        return 0;
    }

    public boolean canPlayerJump(int playerCurrentBuilding, String lateralMovement)
    {
        if (!this.buildings.isEmpty())
        {
            if (!isBuildingFrozen(playerCurrentBuilding))
            {
                if (lateralMovement.equals(Jumper.FORWARD))
                {
                    return playerCurrentBuilding + getPlayerJumpHeight(playerCurrentBuilding) <= 15;
                }
                else if (lateralMovement.equals(Jumper.BACKWARD))
                {
                    return playerCurrentBuilding - getPlayerJumpHeight(playerCurrentBuilding) >= 1;
                }
            } else {
                System.out.println("You are on a frozen building. You must skip a turn");
            }
        }
        return false;
    }

    public ArrayList<Building> getBuildings() {
        return this.buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public void addBuilding(int number, int height,
                            boolean exitPortal, boolean fuelCell, boolean web, boolean freeze)
    {
        this.buildings.add(new Building(number, height, exitPortal, fuelCell, web, freeze));
    }

    public String printBuilding()
    {
        String buildings = "";
        for (Building building : this.buildings) {
            buildings += building.getBuildingNumber() + " , " +
                    building.getHeight() + " , " +
                    building.isExitPortal() + " , " +
                    building.isFuelCell() + " , " +
                    building.isWeb() + " , " +
                    building.isFreeze() + "\n";
        }
        return buildings;
    }

    public boolean isBuildingFrozen(int playerBuilding)
    {
        if (!this.buildings.isEmpty())
        {
            // - 1 because the .get starts from 0 and playerBuilding starts from 1
            return this.buildings.get(playerBuilding - 1).isFreeze();
        }
        return false;
    }

    public boolean isExitPortal(int playerBuilding)
    {
        if (!this.buildings.isEmpty())
        {
            // - 1 because the .get starts from 0 and playerBuilding starts from 1
            return this.buildings.get(playerBuilding - 1).isExitPortal();
        }
        return false;
    }

    public boolean isFuelCell(int playerBuilding)
    {
        if (!this.buildings.isEmpty())
        {
            // - 1 because the .get starts from 0 and playerBuilding starts from 1
            return this.buildings.get(playerBuilding - 1).isFuelCell();
        }
        return false;
    }

    public boolean isWebTrapped(int playerBuilding)
    {
        if (!this.buildings.isEmpty())
        {
            // - 1 because the .get starts from 0 and playerBuilding starts from 1
            return this.buildings.get(playerBuilding - 1).isWeb();
        }
        return false;
    }

}