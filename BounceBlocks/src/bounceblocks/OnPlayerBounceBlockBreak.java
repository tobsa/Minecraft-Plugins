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
        BounceBlock bounceBlock = bounceBlockManager.getBounceBlock(event.getBlock());
        if(bounceBlock != null)
            if(bounceBlockManager.removeBounceBlock(bounceBlock)) {
                event.getPlayer().sendMessage(PlayerMessage.bounceBlockDestroyed(bounceBlock));
                FileManager.save(bounceBlockManager);
            }
    }
}
