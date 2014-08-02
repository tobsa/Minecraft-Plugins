package toggleblocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlacedEvent implements Listener {
    private RegionManager regionManager;
    
    public OnBlockPlacedEvent(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
    
    @EventHandler
    public void onBlockPlacedEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        
        Region region = regionManager.getEditRegion(player.getName());
        if(region == null)
            return;
        
        Block block = event.getBlock();
        
        region.addBlock(new ToggleBlock(block));        
        player.sendMessage(PlayerMessage.blockPlaced(region.getName()));
    }
    
}
