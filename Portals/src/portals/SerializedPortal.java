package portals;

import java.io.Serializable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class SerializedPortal implements Serializable {
    private String name;
    private String playerName;
    private int minimumX;
    private int minimumY;
    private int minimumZ;
    private int maximumX;
    private int maximumY;
    private int maximumZ;
    private double locationX;
    private double locationY;
    private double locationZ;
    private float yaw;
    private float pitch;
    
    public SerializedPortal(Portal portal) {
        this.name = portal.getName();
        this.playerName = portal.getPlayerName();
        this.minimumX = portal.getMinimumBlock().getX();
        this.minimumY = portal.getMinimumBlock().getY();
        this.minimumZ = portal.getMinimumBlock().getZ();
        
        this.maximumX = portal.getMaximumBlock().getX();
        this.maximumY = portal.getMaximumBlock().getY();
        this.maximumZ = portal.getMaximumBlock().getZ();
        
        this.locationX = portal.getTeleportLocation().getX();
        this.locationY = portal.getTeleportLocation().getY();
        this.locationZ = portal.getTeleportLocation().getZ();
        this.yaw = portal.getTeleportLocation().getYaw();
        this.pitch = portal.getTeleportLocation().getPitch();
    }
    
    public Portal getPortal() {
        Block minimumBlock = Bukkit.getWorld("world").getBlockAt(minimumX, minimumY, minimumZ);
        Block maximumBlock = Bukkit.getWorld("world").getBlockAt(maximumX, maximumY, maximumZ);
        Location location = new Location(Bukkit.getWorld("world"), locationX, locationY, locationZ, yaw, pitch);
        
        return new Portal(name, playerName, minimumBlock, maximumBlock, location);
    }
}
