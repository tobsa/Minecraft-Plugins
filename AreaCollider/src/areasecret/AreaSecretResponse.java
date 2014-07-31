package areasecret;

import areacollider.CollisionResponse;
import areacollider.PlayerMessage;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AreaSecretResponse implements CollisionResponse {
    
    @Override
    public void onCollision(Player player) {
        player.sendMessage(PlayerMessage.getSecretAreaMessage());
        player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
    }
}
