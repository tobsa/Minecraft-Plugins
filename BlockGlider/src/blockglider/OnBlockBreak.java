package blockglider;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerPortalEvent;

public class OnBlockBreak implements Listener {
    private BlockGlider plugin;
    
    public OnBlockBreak(BlockGlider plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        PlayerPortalEvent event1;
        
    }
    
}
