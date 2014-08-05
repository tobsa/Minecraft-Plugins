package interactblocks;

import org.bukkit.entity.Player;

public interface InteractResponse {
    public void onInteract(Player player);
    public SerializedInteractResponse getSerializedResponse();
    public String getName();
}
