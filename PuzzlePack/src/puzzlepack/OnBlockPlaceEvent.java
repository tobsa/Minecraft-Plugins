package puzzlepack;

import org.bukkit.DyeColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlaceEvent implements Listener {
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        // For easier access
        Player player = event.getPlayer();
        Block block1 = event.getBlock();
        Block block2 = event.getBlockAgainst();

        // Ignore function if in creative mode
        if (player.getGameMode() == GameMode.CREATIVE)
            return;

        // Make sure ladders can only be placed on iron blocks
        if (block1.getType() == Material.LADDER) {
            if (block2.getType() != Material.IRON_BLOCK)
                event.setCancelled(true);
        } // Make sure redstone wire can only be placed on red wool
        else if (block1.getType() == Material.REDSTONE_WIRE) {
            if (block2.getType() != Material.WOOL || (block2.getType() == Material.WOOL && DyeColor.getByData(block2.getData()) != DyeColor.RED))
                event.setCancelled(true);
        } // Make sure levers can only be placed on gold blocks
        else if (block1.getType() == Material.LEVER) {
            if (block2.getType() != Material.GOLD_BLOCK)
                event.setCancelled(true);
        } // Make sure stone plates and wood plates can only be placed on gold blocks
        else if (block1.getType() == Material.STONE_PLATE || block1.getType() == Material.WOOD_PLATE) {
            if (block2.getType() != Material.GOLD_BLOCK)
                event.setCancelled(true);
        } // Make sure stone buttons and wood buttons can only be placed on gold blocks
        else if (block1.getType() == Material.STONE_BUTTON || block1.getType() == Material.WOOD_BUTTON) {
            if (block2.getType() != Material.GOLD_BLOCK)
                event.setCancelled(true);
        }
    }
}
