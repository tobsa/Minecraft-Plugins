package catapultblocks;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class OnPlayerMove implements Listener {
    private CatapultManager catapultManager;
    
    public OnPlayerMove(CatapultManager catapultManager) {
        this.catapultManager = catapultManager;
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Block block = event.getTo().getBlock();
        Player player = event.getPlayer();
        
        for(CatapultBlock catapultBlock : catapultManager.getCapultBlocks()) {
            if(catapultBlock.getActiveBlock().equals(block)) {
                double forward = catapultBlock.getForwardVelocity();
                double up = catapultBlock.getUpwardVelocity();
                
                switch(catapultBlock.getDirection()) {
                    case NORTH: player.setVelocity(new Vector(0, up, -forward)); break;
                    case EAST:  player.setVelocity(new Vector(forward, up, 0));  break;
                    case SOUTH: player.setVelocity(new Vector(0, up, forward));  break;
                    case WEST:  player.setVelocity(new Vector(-forward, up, 0)); break;
                } 
            }
        }
    }
}
