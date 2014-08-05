package interactblocks;

import java.util.List;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractEvent implements Listener {
    private InteractBlockManager blockManager;
    
    public OnPlayerInteractEvent(InteractBlockManager blockManager) {
        this.blockManager = blockManager;
    }
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        
        List<InteractBlock> blocks = blockManager.getInteractBlocks(event.getClickedBlock());
        for(InteractBlock block : blocks)
            block.getResponse().onInteract(event.getPlayer());        
    }
    
}
