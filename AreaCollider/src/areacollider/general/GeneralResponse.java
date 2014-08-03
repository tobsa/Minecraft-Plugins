package areacollider.general;

import areacollider.CollisionResponse;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import areacollider.SerializedCollisionResponse;

public class GeneralResponse implements CollisionResponse {
    private String message;
    private Sound sound;
    
    public GeneralResponse(String message, Sound sound) {
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

    @Override
    public SerializedCollisionResponse getSerializedResponse() {
        return new SerializedGeneralResponse(this);
    }
}
