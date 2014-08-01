package catapultblocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreak implements Listener {
    private CatapultManager catapultManager;
    
    public OnBlockBreak(CatapultManager catapultManager) {
        this.catapultManager = catapultManager;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();
        
        for(CatapultBlock catapultBlock : catapultManager.getCapultBlocks()) {
            if(catapultBlock.getBlock().equals(block)) {
                catapultManager.removeCatapultBlock(catapultBlock);
                player.sendMessage(PlayerMessage.getBlockRemoved(catapultBlock.getDirection(), catapultBlock.getForwardVelocity(), catapultBlock.getUpwardVelocity()));
                break;
            }
        }
    }
}
