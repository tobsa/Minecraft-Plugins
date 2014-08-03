package areacollider.message;

import areacollider.CollisionResponse;
import areacollider.SerializedCollisionResponse;
import org.bukkit.entity.Player;

public class MessageResponse implements CollisionResponse {
    private String message;
    
    public MessageResponse(String message) {
        this.message = message;
    }
    
    @Override
    public void onCollision(Player player) {
        player.sendMessage(message);
    }

    @Override
    public SerializedCollisionResponse getSerializedResponse() {
        return new SerializedMessageResponse(message);
    }
    
}
