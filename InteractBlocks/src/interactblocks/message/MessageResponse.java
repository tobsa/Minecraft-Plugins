package interactblocks.message;

import interactblocks.InteractResponse;
import interactblocks.SerializedInteractResponse;
import org.bukkit.entity.Player;

public class MessageResponse implements InteractResponse {
    private String message;
    
    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    @Override
    public void onInteract(Player player) {
        player.sendMessage(message);
    }

    @Override
    public SerializedInteractResponse getSerializedResponse() {
        return new SerializedMessageResponse(this);
    }

    @Override
    public String getName() {
        return "Message";
    }
}
