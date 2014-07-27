package puzzlepack;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakEvent implements Listener {

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (event.getPlayer().getGameMode() == GameMode.CREATIVE) {
            // Make sure a player can't destroy blocks with the feather in creative mode
            if (event.getPlayer().getItemInHand() != null && event.getPlayer().getItemInHand().getType() == Material.FEATHER) {
                event.setCancelled(true);
            }

            return;
        }

        event.setCancelled(true);
    }
}
