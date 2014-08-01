package telepads;

import org.bukkit.Location;

public class Telepad {
    private String name;
    private String playerName;
    private Location from;
    private Location to;
    private float yaw;
    private float pitch;
    
    public Telepad(String name, String playerName) {
        this.name = name;
        this.playerName = playerName;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public Location getFrom() {
        return from;
    }
    
    public Location getTo() {
        return to;
    }
    
    public void setFrom(Location from) {
        this.from = from;
    }
    
    public void setTo(Location to, float yaw, float pitch) {
        this.to = to;
        this.yaw = yaw;
        this.pitch = pitch;
    }
    
    public boolean isActive() {
        return from != null && to != null;
    }
    
    public float getYaw() {
        return yaw;
    }
    
    public float getPitch() {
        return pitch;
    }
}
