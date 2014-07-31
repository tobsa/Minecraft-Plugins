package areateleport;

import areacollider.CollisionResponse;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AreaTeleportResponse implements CollisionResponse {
    private Location location;
    private float yaw;
    private float pitch;
    private String message;
    
    public AreaTeleportResponse(Location location, String message) {
        this.location = location;
        this.yaw = location.getYaw();
        this.pitch = location.getPitch();
        this.message = message;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public String getMessage() {
        return message;
    }
    
    public float getYaw() {
        return yaw;
    }
    
    public float getPitch() {
        return pitch;
    }
    
    @Override
    public void onCollision(Player player) {
        if(!message.isEmpty())
            player.sendMessage(message);
        
        player.teleport(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), yaw, pitch));
        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
    }
}
