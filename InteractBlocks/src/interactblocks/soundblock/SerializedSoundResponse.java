package interactblocks.soundblock;

import interactblocks.InteractResponse;
import interactblocks.SerializedInteractResponse;
import org.bukkit.Sound;

public class SerializedSoundResponse implements SerializedInteractResponse{
    private String sound;
    
    public SerializedSoundResponse(SoundResponse response) {
        this.sound = response.getSound().toString();
    }
    
    @Override
    public InteractResponse getResponse() {
        return new SoundResponse(Sound.valueOf(sound));
    }
    
}
