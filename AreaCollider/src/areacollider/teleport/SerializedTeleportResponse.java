package areacollider.teleport;

import areacollider.SerializedCollisionResponse;
import areacollider.CollisionResponse;
import areacollider.teleport.TeleportResponse;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class SerializedTeleportResponse implements SerializedCollisionResponse {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private String message;
    
    public SerializedTeleportResponse(TeleportResponse response) {
        x = response.getLocation().getX();
        y = response.getLocation().getY();
        z = response.getLocation().getZ();
        yaw = response.getYaw();
        pitch = response.getPitch();
        message = response.getMessage();
    }
    
    @Override
    public CollisionResponse getCollisionResponse() {
        return new TeleportResponse(new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch), message);
    }
}
