package area.clearinventoryarea;

import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import puzzlepack.PuzzlePack;

public class OnPlayerMoveClearInventoryArea implements Listener {
    private PuzzlePack plugin;
    
    public OnPlayerMoveClearInventoryArea(PuzzlePack plugin) {
        this.plugin = plugin;
    }    
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Block block = event.getTo().getBlock();
        Player player = event.getPlayer();
        
        if(player.getGameMode() == GameMode.CREATIVE)
            return;
        
        for(ClearInventoryArea area : plugin.getAreaManager().getClearInventoryAreas()) {
            if(area.isInside(player, block)) {
                if(area.hasMessage())
                    player.sendMessage(area.getMessage());       
                
                PlayerInventory inv = player.getInventory();

                // Loop trough all inventory slots
                for (int i = 0; i < inv.getSize(); i++) {
                    // If there's no item in a slot then continue to the next one
                    if (inv.getItem(i) == null) {
                        continue;
                    }

                    // Clear all items except a few (down below)
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
                
                player.playSound(player.getLocation(), Sound.BURP, 1, 1);
            }
        }
    }
}
