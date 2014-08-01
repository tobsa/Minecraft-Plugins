
package redstonecombiner;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakEvent implements Listener {
    private CombinerManager combinerManager;
    
    public OnBlockBreakEvent(CombinerManager combinerManager) {
        this.combinerManager = combinerManager;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = event.getPlayer();      
        
        for(Combiner combiner : combinerManager.getCombiners()) {
            Block toggleBlock = combiner.getToggleBlock();
            
            if(!player.getName().equalsIgnoreCase(combiner.getPlayerName()))
                continue;
            
            if(toggleBlock.equals(block) || toggleBlock.getRelative(BlockFace.DOWN).equals(block)) {
                combinerManager.removeCombiner(combiner);
                player.sendMessage(PlayerMessage.getCombinerRemoved(combiner.getName()));
            }
            
            for(Block link : combiner.getLinks()) {
                if(block.equals(link)) {
                    combiner.removeLink(link);
                    player.sendMessage(PlayerMessage.getLinkRemoved(combiner.getName()));
                    break;
                }
            }
        }
    }
}
