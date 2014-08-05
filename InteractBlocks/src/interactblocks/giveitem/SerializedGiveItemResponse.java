package interactblocks.giveitem;

import interactblocks.InteractResponse;
import interactblocks.SerializedInteractResponse;
import org.bukkit.Material;

public class SerializedGiveItemResponse implements SerializedInteractResponse {
    private String material;
    private int amount;
    
    public SerializedGiveItemResponse(GiveItemResponse response) {
        this.material = response.getMaterial().toString();
        this.amount = response.getAmount();
    }
    
    @Override
    public InteractResponse getResponse() {
        return new GiveItemResponse(Material.valueOf(material), amount);
    }
    
}
