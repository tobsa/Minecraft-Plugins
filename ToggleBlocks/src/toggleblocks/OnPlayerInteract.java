package toggleblocks;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteract implements Listener {
    private ToggleBlocks plugin;
    
    public OnPlayerInteract(ToggleBlocks plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block block = event.getClickedBlock();
            
            RegionManager regionManager = plugin.getRegionManager();
            List<Region> regions = regionManager.getRegions(event.getPlayer().getPlayerListName());
            
            for(Region region : regions) {
                if(region.isLinkBlock(block))
                    region.toggle();
            }            
        }
    }
    
}
