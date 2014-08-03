package pushblocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakEvent implements Listener {
    private PathManager pathManager;
    
    public OnBlockBreakEvent(PathManager pathManager) {
        this.pathManager = pathManager;
    }
    
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        for(Path path : pathManager.getPaths())
            if(path.getActiveBlock().equals(event.getBlock()))
                event.setCancelled(true);
    }
}
