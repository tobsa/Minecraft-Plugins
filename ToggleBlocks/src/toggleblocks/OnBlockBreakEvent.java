package toggleblocks;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakEvent implements Listener {
    private RegionManager regionManager;
    
    public OnBlockBreakEvent(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
    
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        Player player = event.getPlayer();
        
        Region region = regionManager.getEditRegion(player.getName());
        if(region == null)
            return;
        
        player.sendMessage("Passed region check: " + region.getName());
        
        if(region.removeBlock(event.getBlock()))
            player.sendMessage(Message.blockRemoved(region.getName()));
    }
    
}
