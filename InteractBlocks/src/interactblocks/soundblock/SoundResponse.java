package interactblocks.soundblock;

import interactblocks.InteractResponse;
import interactblocks.SerializedInteractResponse;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundResponse implements InteractResponse {
    private Sound sound;
    
    public SoundResponse(Sound sound) {
        this.sound = sound;
    }
        
    public Sound getSound() {
        return sound;
    }

    @Override
    public void onInteract(Player player) {
        player.playSound(player.getLocation(), sound, 1, 1);
    }

    @Override
    public SerializedInteractResponse getSerializedResponse() {
        return new SerializedSoundResponse(this);
    }

    @Override
    public String getName() {
        return "Sound";
    }
}
