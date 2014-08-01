package redstonecombiner;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class OnBlockRedstoneEvent implements Listener {
    private CombinerManager combinerManager;
    
    public OnBlockRedstoneEvent(CombinerManager channelManager) {
        this.combinerManager = channelManager;
    }
    
    @EventHandler
    public void onBlockRedstone(BlockRedstoneEvent event) {
        
        for(Combiner combiner : combinerManager.getCombiners()) {
            combiner.update();
        }
    }
}
