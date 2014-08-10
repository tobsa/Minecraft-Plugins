package toggleblocks;

import java.util.List;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractEvent implements Listener {
    private RegionManager regionManager;
    
    public OnPlayerInteractEvent(RegionManager regionManager) {
        this.regionManager = regionManager;
    }
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            
            List<Region> regions = regionManager.get();
            for(Region region : regions) {
                if(region.isLinkBlock(block) && region.getLinkBlock().getLinkType() == LinkType.Interact ) {
                    region.toggle();
                    block.getWorld().playSound(block.getLocation(), Sound.PISTON_EXTEND, 1, 1);
                }
            }            
        }
    }
    
}
