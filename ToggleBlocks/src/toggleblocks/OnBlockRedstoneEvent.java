package toggleblocks;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class OnBlockRedstoneEvent implements Listener {
    private ToggleBlocks plugin;

    public OnBlockRedstoneEvent(ToggleBlocks plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBlockRedstoneEvent(BlockRedstoneEvent event) {
        RegionManager regionManager = plugin.getRegionManager();
                
        for(Region region : regionManager.getAllRegions()) {
            LinkBlock linkBlock = region.getLinkBlock();
            if(linkBlock != null && linkBlock.getLinkType() == LinkType.Redstone) {
                Block block = linkBlock.getBlock();
                if(block.isBlockPowered() || block.isBlockIndirectlyPowered()) {
                    region.toggleOff();
                }
                else {
                    region.toggleOn();
                }
            }
        }
    }
}
