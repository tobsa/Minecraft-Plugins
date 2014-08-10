package puzzlepack;

import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {
    
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        
        if(event.getPlayer().getGameMode() == GameMode.SURVIVAL)
            event.setCancelled(true);
    }
    
}
