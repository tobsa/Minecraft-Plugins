package area;

import areacollider.CollisionResponse;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class AreaResponse implements CollisionResponse {
    private String message;
    private Sound sound;
    
    public AreaResponse(String message, Sound sound) {
        this.message = message;
        this.sound = sound;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getSound() {
        return sound.toString();
    }
    
    @Override
    public void onCollision(Player player) {
        if(!message.isEmpty())
            player.sendMessage(message);
        
        player.playSound(player.getLocation(), sound, 1, 1);
    }
}
