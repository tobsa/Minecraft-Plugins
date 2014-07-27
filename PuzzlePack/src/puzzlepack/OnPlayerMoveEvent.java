package puzzlepack;

import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.Wool;
import org.bukkit.util.Vector;

public class OnPlayerMoveEvent implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // For easier access
        Block block = event.getFrom().getBlock().getRelative(BlockFace.DOWN);
        Player player = event.getPlayer();

        // Is the block the player walked upon is a wool block?
        if (block.getType() == Material.WOOL) {
            // For easier acces
            Block below = block.getRelative(BlockFace.DOWN);
            Wool wool = new Wool(block.getType(), block.getData());

            // If the wool is white then bounce the player
            if (wool.getColor() == DyeColor.WHITE) {
                player.setVelocity(new Vector(0, below.getTypeId() * 0.1 + 0.4, 0));
            }

            // If the wool is pink then clear inventory
            if (wool.getColor() == DyeColor.PINK) {
                // Don't do this in creative mode...
                if (player.getGameMode() == GameMode.CREATIVE) {
                    return;
                }

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
                            break;
                        default:
                            inv.clear(i);
                    }

                }
            }
        }
    }

}
