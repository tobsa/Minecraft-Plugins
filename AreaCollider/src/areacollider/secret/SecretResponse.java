package areacollider.secret;

import areacollider.CollisionResponse;
import areacollider.Message;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import areacollider.SerializedCollisionResponse;

public class SecretResponse implements CollisionResponse {
    
    @Override
    public void onCollision(Player player) {
        player.sendMessage(Message.secretAreaMessage());
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
    }

    @Override
    public SerializedCollisionResponse getSerializedResponse() {
        return new SerializedSecretResponse();
    }
}
