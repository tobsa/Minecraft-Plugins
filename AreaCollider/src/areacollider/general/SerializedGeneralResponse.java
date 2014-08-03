package areacollider.general;

import areacollider.SerializedCollisionResponse;
import areacollider.CollisionResponse;
import org.bukkit.Sound;

public class SerializedGeneralResponse implements SerializedCollisionResponse  {
    private String message;
    private String sound;
    
    public SerializedGeneralResponse(GeneralResponse areaResponse) {
        message = areaResponse.getMessage();
        sound = areaResponse.getSound();
    }
    
    @Override
    public CollisionResponse getCollisionResponse() {
        return new GeneralResponse(message, Sound.valueOf(sound));
    }
}
