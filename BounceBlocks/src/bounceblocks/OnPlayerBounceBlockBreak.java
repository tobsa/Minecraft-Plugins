package bounceblocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnPlayerBounceBlockBreak implements Listener {
    private BounceBlockManager bounceBlockManager;
    
    public OnPlayerBounceBlockBreak(BounceBlockManager bounceBlockManager) {
        this.bounceBlockManager = bounceBlockManager;
    }
    
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if(bounceBlockManager.removeBlock(event.getBlock()))
            event.getPlayer().sendMessage(PlayerMessage.bounceBlockDestroyed());
    }
}
