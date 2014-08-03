package areacollider;

import java.io.Serializable;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;

public class SerializedArea implements Serializable {
    private String playerName;
    private String name;
    private int minimumX;
    private int minimumY;
    private int minimumZ;
    private int maximumX;
    private int maximumY;
    private int maximumZ;
    private SerializedCollisionResponse response;
    
    public SerializedArea(Area area) {
        this.playerName = area.getPlayerName();
        this.name = area.getName();
        
        this.minimumX = area.getMinimumBlock().getX();
        this.minimumY = area.getMinimumBlock().getY();
        this.minimumZ = area.getMinimumBlock().getZ();
        
        this.maximumX = area.getMaximumBlock().getX();
        this.maximumY = area.getMaximumBlock().getY();
        this.maximumZ = area.getMaximumBlock().getZ();
        
        response = area.getSerializedResponse();
    }
    
    public Area getArea() {
        Block minimumBlock = Bukkit.getWorld("world").getBlockAt(minimumX, minimumY, minimumZ);
        Block maximumBlock = Bukkit.getWorld("world").getBlockAt(maximumX, maximumY, maximumZ);
        
        return new Area(playerName, name, minimumBlock, maximumBlock, response.getCollisionResponse());
    }
}
