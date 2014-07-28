package area;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public abstract class Area {
    private String playerName;
    private String name;
    private Block block1;
    private Block block2;
    private String message = "";
    
    public Area(String playerName, String name, Block block1, Block block2) {
        this.playerName = playerName;
        this.name = name;
        this.block1 = block1;
        this.block2 = block2;
    }
    
    public Area(String playerName, String name, String message, Block block1, Block block2) {
        this.playerName = playerName;
        this.name = name;
        this.message = message;
        this.block1 = block1;
        this.block2 = block2;
    }
    
    public boolean intersects(Block block) {
        return  block.getX() >= block1.getX() && block.getX() <= block2.getX() &&
                block.getY() >= block1.getY() && block.getY() <= block2.getY() &&
                block.getZ() >= block1.getZ() && block.getZ() <= block2.getZ();
    }
    
    public abstract boolean isInside(Player player, Block block);
    
    public String getPlayerName() {
        return playerName;
    }
    
    public String getName() {
        return name;
    }
    
    public Block getBlock1() {
        return block1;
    }
    
    public Block getBlock2() {
        return block2;
    }
    
    public boolean hasMessage() {
        return !message.isEmpty();
    }
    
    public String getMessage() {
        return message;
    }
}
