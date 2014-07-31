package toggleblocks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {
    private ToggleBlocks plugin;
    
    public OnBlockBreak(ToggleBlocks plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        
        RegionManager regionManager = plugin.getRegionManager();
        Region region = regionManager.getEditRegion(player);
        if(region == null)
            return;
        
        if(region.removeBlock(event.getBlock()))
            player.sendMessage(PlayerMessage.getBlockRemoved(region.getName()));
    }
    
}
