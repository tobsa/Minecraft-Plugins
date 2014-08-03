package pushblocks;

import java.util.ArrayList;
import org.bukkit.Material;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractEvent implements Listener {
    private PathManager pathManager;
    
    public OnPlayerInteractEvent(PathManager pathManager) {
        this.pathManager = pathManager;
    }
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        if(event.getPlayer().getItemInHand() == null || player.getItemInHand().getType() != Material.BONE)
            return;                
        
        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {            
            ArrayList<Block> blocks = (ArrayList<Block>) player.getLastTwoTargetBlocks(null, 50);
            
            for(Path path : pathManager.getPaths()) {
                if(path.isBlockActive(blocks.get(1)))
                    path.move();
            }
        }
    }
}
