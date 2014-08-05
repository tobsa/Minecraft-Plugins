package interactblocks.giveitem;

import interactblocks.InteractResponse;
import interactblocks.SerializedInteractResponse;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveItemResponse implements InteractResponse {
    private Material material;
    private int amount;
    
    public GiveItemResponse(Material material, int amount) {
        this.material = material;
        this.amount = amount;
    }
    
    public Material getMaterial() {
        return material;
    }
    
    public int getAmount() {
        return amount;
    }
    
    @Override
    public void onInteract(Player player) {
        player.getInventory().addItem(new ItemStack(material, amount));
        player.updateInventory();
    }

    @Override
    public SerializedInteractResponse getSerializedResponse() {
        return new SerializedGiveItemResponse(this);
    }

    @Override
    public String getName() {
        return "Give item";
    }
    
}

