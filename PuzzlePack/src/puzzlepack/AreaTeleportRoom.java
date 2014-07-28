package puzzlepack;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class AreaTeleportRoom extends Area {
    private Location teleport;
    private float pitch;
    private float yaw;
    
    public AreaTeleportRoom(String playerName, String name, Block block1, Block block2, Location teleport, float pitch, float yaw) {
        super(playerName, name, block1, block2);
        this.teleport = teleport;
        this.pitch = pitch;
        this.yaw = yaw;
    }
    
    @Override
    public boolean isInside(Player player, Block block) {  
        return intersects(block);
    }
    
    public Location getTeleport() {
        return teleport;
    }
    
    public float getPitch() {
        return pitch;
    }
    
    public float getYaw() {
        return yaw;
    }
}
