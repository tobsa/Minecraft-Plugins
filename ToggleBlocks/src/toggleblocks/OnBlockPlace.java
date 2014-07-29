package toggleblocks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlace implements Listener {
    private ToggleBlocks plugin;
    
    public OnBlockPlace(ToggleBlocks plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        
        RegionManager regionManager = plugin.getRegionManager();
        Region region = regionManager.getEditRegion(player);
        if(region == null)
            return;
        
        region.addToggleBlock(new ToggleBlock(event.getBlock()));
        player.sendMessage(ToggleBlocks.CHAT_NORMAL + "A block was added to region '" + ToggleBlocks.CHAT_HIGHLIGHT + region.getName() + ToggleBlocks.CHAT_NORMAL + "'!");
    }
    
}
