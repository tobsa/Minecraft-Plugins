package catapultblocks;

import org.bukkit.Sound;
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
                player.setVelocity(catapultBlock.getVelocity());
                player.playSound(player.getLocation(), Sound.ARROW_HIT, 1, 1);
            }
        }
    }
}
