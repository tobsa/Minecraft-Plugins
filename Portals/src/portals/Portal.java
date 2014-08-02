package portals;

import org.bukkit.Location;
import org.bukkit.block.Block;


public class Portal {
    private String name;
    private String playerName;
    private Block minimumBlock;
    private Block maximumBlock;
    private Location teleportLocation;
    
    public Portal(String name, String playerName, Block minimumBlock, Block maximumBlock, Location teleportLocation) {
        this.name = name;
        this.playerName = playerName;
        this.minimumBlock = minimumBlock;
        this.maximumBlock = maximumBlock;
        this.teleportLocation = teleportLocation;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public Block getMinimumBlock() {
        return minimumBlock;
    }
    
    public Block getMaximumBlock() {
        return maximumBlock;
    }
    
    public Location getTeleportLocation() {
        return teleportLocation;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setTeleportLocation(Location teleportLocation) {
        this.teleportLocation = teleportLocation;
    }
    
    public boolean intersects(Block block) {
        return  block.getX() >= minimumBlock.getX() && block.getX() <= maximumBlock.getX() &&
                block.getY() >= minimumBlock.getY() && block.getY() <= maximumBlock.getY() &&
                block.getZ() >= minimumBlock.getZ() && block.getZ() <= maximumBlock.getZ();
    }
}


