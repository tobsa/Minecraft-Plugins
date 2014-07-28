package bounceblock;

import bounceblock.BounceBlock;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;
import puzzlepack.PuzzlePack;

public class OnPlayerMoveBounceBlock implements Listener {
    private PuzzlePack plugin;
    
    public OnPlayerMoveBounceBlock(PuzzlePack plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
                        
        for(BounceBlock block : plugin.getBounceBlockManager().getBounceBlocks()) {
            if(player.getLocation().getBlock().getRelative(BlockFace.DOWN).equals(block.getBlock()))
                player.setVelocity(new Vector(0, block.getJumpStrength(), 0));
        }
    }
}
