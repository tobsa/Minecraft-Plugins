package telepads;

import java.io.Serializable;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SerializedTelepad implements Serializable {    
    private String name;
    private String playerName;
    private double fromX;
    private double fromY;
    private double fromZ;
    private double toX;
    private double toY;
    private double toZ;
    private float yaw;
    private float pitch;
    
    public SerializedTelepad(Telepad telepad) {
        this.name = telepad.getName();
        this.playerName = telepad.getPlayerName();
        
        this.fromX = telepad.getFrom().getX();
        this.fromY = telepad.getFrom().getY();
        this.fromZ = telepad.getFrom().getZ();
        
        this.toX = telepad.getTo().getX();
        this.toY = telepad.getTo().getY();
        this.toZ = telepad.getTo().getZ();
        
        this.yaw = telepad.getYaw();
        this.pitch = telepad.getPitch();
    }
    
    public Telepad getTelepad() {
        Location from = new Location(Bukkit.getWorld("world"), fromX, fromY, fromZ);
        Location to   = new Location(Bukkit.getWorld("world"), toX, toY, toZ);
        
        Telepad telepad = new Telepad(name, playerName);
        telepad.setFrom(from);
        telepad.setTo(to, yaw, pitch);
        
        return telepad;
    }
}
