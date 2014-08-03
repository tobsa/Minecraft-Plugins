package areacollider.sound;

import areacollider.CollisionResponse;
import areacollider.SerializedCollisionResponse;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundResponse implements CollisionResponse {
    private Sound sound;
    
    public SoundResponse(Sound sound) {
        this.sound = sound;
    }
    
    @Override
    public void onCollision(Player player) {
        player.playSound(player.getLocation(), sound, 1, 1);
    }

    @Override
    public SerializedCollisionResponse getSerializedResponse() {
        return new SerializedSoundResponse(sound.toString());
    }
    
}
