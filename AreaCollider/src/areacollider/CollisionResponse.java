package areacollider;

import org.bukkit.entity.Player;

public interface CollisionResponse {
    public void onCollision(Player player);
    public SerializedCollisionResponse getSerializedResponse();
}
