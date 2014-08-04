package boomstick;

import java.util.List;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractEvent implements Listener {
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        if(!(event.getAction() == Action.RIGHT_CLICK_AIR  || event.getAction() == Action.RIGHT_CLICK_BLOCK))
            return;
        
        if(player.getItemInHand().getType() != Material.STICK)
            return;
        
        List<Block> blocks = player.getLastTwoTargetBlocks(null, 50);

        explode(blocks.get(1), 4);
    }
    
    private void explode(Block block, int depth) {
        if(depth == 0)
            return;
        
        if(block.getType() == Material.STAINED_CLAY && block.getData() == 10)
            block.setType(Material.AIR);
        
        explode(block.getRelative(BlockFace.UP), depth - 1);
        explode(block.getRelative(BlockFace.DOWN), depth - 1);
        explode(block.getRelative(BlockFace.NORTH), depth - 1);
        explode(block.getRelative(BlockFace.SOUTH), depth - 1);
        explode(block.getRelative(BlockFace.EAST), depth - 1);
        explode(block.getRelative(BlockFace.WEST), depth - 1);
    }
}
