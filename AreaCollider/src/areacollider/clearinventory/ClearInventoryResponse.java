package areacollider.clearinventory;

import areacollider.CollisionResponse;
import areacollider.Message;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import areacollider.SerializedCollisionResponse;

public class ClearInventoryResponse implements CollisionResponse {

    @Override
    public void onCollision(Player player) {
        if(player.getGameMode() == GameMode.CREATIVE)
            return;
        
        PlayerInventory inv = player.getInventory();
        
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null)
                continue;

            switch (inv.getItem(i).getType()) {
                case GOLD_NUGGET:
                case REDSTONE_WIRE:
                case LEVER:
                case LADDER:
                case STONE_PLATE:
                case WOOD_PLATE:
                case STONE_BUTTON:
                case WOOD_BUTTON:
                case COOKED_BEEF:
                case BREAD:
                case COOKED_CHICKEN:
                case COOKED_FISH:
                case WOOD_AXE:
                    break;
                default:
                    inv.clear(i);
            }
        }
        
        player.sendMessage(Message.inventoryCleared());
    }

    @Override
    public SerializedCollisionResponse getSerializedResponse() {
        return new SerializedClearInventoryResponse();
    }
}
