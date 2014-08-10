package bounceblocks;

import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class OnPlayerMoveBounceBlock implements Listener {
    private BounceBlockManager bounceBlockManager;
    
    public OnPlayerMoveBounceBlock(BounceBlockManager bounceBlockManager) {
        this.bounceBlockManager = bounceBlockManager;
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if(event.getFrom().equals(event.getTo()))
            return;
        
        Player player = event.getPlayer();
                        
        for(BounceBlock block : bounceBlockManager.getBounceBlocks()) {
            if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).equals(block.getBlock())) {
                player.setVelocity(new Vector(0, block.getJumpStrength(), 0));
                player.playSound(player.getLocation(), Sound.ARROW_HIT, 1, 1);
            }
        }
    }
}
