package toggleblocks;

import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class OnBlockRedstoneEvent implements Listener {
    private RegionManager regionManager;
    
    public OnBlockRedstoneEvent(RegionManager regionManager) {
        this.regionManager = regionManager;
    }

    @EventHandler
    public void onBlockRedstoneEvent(BlockRedstoneEvent event) {

        for(Region region : regionManager.getRegions()) {
            LinkBlock linkBlock = region.getLinkBlock();
            
            if(linkBlock != null && linkBlock.getLinkType() == LinkType.Redstone) {
                Block block = linkBlock.getBlock();
                if(block.isBlockPowered() || block.isBlockIndirectlyPowered())
                    region.toggleOff();
                else
                    region.toggleOn();
            }
        }
    }
}
