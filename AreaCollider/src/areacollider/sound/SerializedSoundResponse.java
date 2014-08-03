package areacollider.sound;

import areacollider.CollisionResponse;
import areacollider.SerializedCollisionResponse;
import org.bukkit.Sound;

public class SerializedSoundResponse implements SerializedCollisionResponse{
    private String sound;
    
    public SerializedSoundResponse(String sound) {
        this.sound = sound;
    }
        
    @Override
    public CollisionResponse getCollisionResponse() {
        return new SoundResponse(Sound.valueOf(sound));
    }
    
}
