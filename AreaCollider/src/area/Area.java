package area;

import areacollider.CollisionResponse;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class Area {
    private String playerName;
    private String name;
    private Block minimumBlock;
    private Block maximumBlock;
    private CollisionResponse response;
    private Map<String, Boolean> players = new HashMap();
    
    public Area(String playerName, String name, Block minimumBlock, Block maximumBlock, CollisionResponse response) {
        this.playerName = playerName;
        this.name = name;
        this.minimumBlock = minimumBlock;
        this.maximumBlock = maximumBlock;
        this.response = response;
    }
          
    public String getPlayerName() {
        return playerName;
    }
    
    public String getName() {
        return name;
    }
    
    public Block getMinimumBlock() {
        return minimumBlock;
    }
    
    public Block getMaximumBlock() {
        return maximumBlock;
    }
            
    public void onCollision(Player player) {
        response.onCollision(player);
    }
    
    public CollisionResponse getResponse() {
        return response;
    }
    
    public void setMinimumBlock(Block minimumBlock) {
        this.minimumBlock = minimumBlock;
    }
    
    public void setMaximumBlock(Block maximumBlock) {
        this.maximumBlock = maximumBlock;
    }
        
    public boolean intersects(Block block) {
        return  block.getX() >= minimumBlock.getX() && block.getX() <= maximumBlock.getX() &&
                block.getY() >= minimumBlock.getY() && block.getY() <= maximumBlock.getY() &&
                block.getZ() >= minimumBlock.getZ() && block.getZ() <= maximumBlock.getZ();
    }
    
    public boolean hasPlayerEntered(String playerName, Block block) {
        if(intersects(block)) {
            Boolean value = players.get(playerName);
            if(value == null) {
                players.put(playerName, true);
                return true;
            }
            else {
                if(!value.booleanValue()) {
                    players.put(playerName, true);
                    return true;
                }
            }
                
            return false;
        }
        else {
            players.put(playerName, false);
            return false;
        }
    }
}
