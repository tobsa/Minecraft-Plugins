package forestertool;

import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractEvent implements Listener {
    private Tool tool;
    
    public OnPlayerInteractEvent(Tool tool) {
        this.tool = tool;      
    }
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
            return;
        
        if(player.getItemInHand().getType() != Material.STONE_AXE)
            return;
        
        if(tool.getDistanceMode() && event.getAction() == Action.RIGHT_CLICK_AIR)
            farInteract(player);
        else if(event.getAction() == Action.RIGHT_CLICK_BLOCK)
            closeInteract(player, tool.getReplace() ? event.getClickedBlock() : event.getClickedBlock().getRelative(event.getBlockFace()));
         
    }
    
    private Block getClickedBlock(Player player) {
        List<Block> blocks = player.getLastTwoTargetBlocks(null, 50);
        return blocks.get(1);
    }
    
    private boolean isInSphere(int radius, int dx, int dy, int dz) {
        if(dx + dy + dz <= radius)
            return true;
        
        if(dx > radius || dy > radius || dz > radius)
            return false;
        
        return dx*dx + dy*dy + dz*dz <= radius * radius;
    }
    
    private void setType(Random random, Block block) {
        if(random.nextInt(100) < tool.getAir()) {
            block.setType(Material.AIR);
            return;
        }

        if(random.nextInt(100) < tool.getLeaves()) {
            block.setType(Material.LEAVES);
            return;
        }

        block.setType(Material.LOG);
        switch(random.nextInt(4)) {
            case 0: block.setData((byte)0); break;
            case 1: block.setData((byte)4); break;
            case 2: block.setData((byte)8); break;
            case 3: block.setData((byte)12); break;
        }    
    }
    
    private void farInteract(Player player) {
        Block block   = getClickedBlock(player);
        Random random = new Random();
        
        int radius = tool.getRadius() - 1;
        int blockX = block.getX();
        int blockY = block.getY();
        int blockZ = block.getZ();

        for(int x = blockX - radius; x <= blockX + radius; x++) {
            for(int y = blockY - radius; y <= blockY + radius; y++) {
                for(int z = blockZ - radius; z <= blockZ + radius; z++) {
                    int dx = Math.abs(blockX - x);
                    int dy = Math.abs(blockY - y);
                    int dz = Math.abs(blockZ - z);
                    
                    if(isInSphere(radius, dx, dy, dz))
                        setType(random, Bukkit.getWorld("world").getBlockAt(x, y, z));
                }
            }
        }  
    }
    
    private void closeInteract(Player player, Block block) {            
         Random random = new Random();

         if(random.nextInt(100) < tool.getLeaves()) {
             block.setType(Material.LEAVES);
             return;
         }

         block.setType(Material.LOG);
         switch(random.nextInt(4)) {
             case 0: block.setData((byte)0); break;
             case 1: block.setData((byte)4); break;
             case 2: block.setData((byte)8); break;
             case 3: block.setData((byte)12); break;
         }
    }
}
