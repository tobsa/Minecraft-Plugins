package puzzlepack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnBlockPlaceEvent implements Listener {
    
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        
        if(player.getItemInHand().getType() != Material.HAY_BLOCK)
            return;
        
        if(event.getAction() != Action.RIGHT_CLICK_AIR )
            return;
        
        int size = PuzzlePack.size;
        if(size == 0)
            return;
        
        List<Block> blocks = event.getPlayer().getLastTwoTargetBlocks(null, 50);
        Block startBlock = blocks.get(1);
        Random random = new Random();
        
        Block minimumBlock = startBlock.getRelative(-size, -size, -size);
        Block maximumBlock = startBlock.getRelative(size, size, size);

        minimumBlock.setType(Material.STONE);
        maximumBlock.setType(Material.STONE);

        int minimumX = minimumBlock.getX();
        int minimumY = minimumBlock.getY();
        int minimumZ = minimumBlock.getZ();

        int maximumX = maximumBlock.getX();
        int maximumY = maximumBlock.getY();
        int maximumZ = maximumBlock.getZ();

        for(int x = minimumX; x <= maximumX; x++) {
            for(int y = minimumY; y <= maximumY; y++) {
                for(int z = minimumZ; z <= maximumZ; z++) {                    
                    Block block = event.getPlayer().getWorld().getBlockAt(x, y, z);
                    
                    if(random.nextInt(100) < PuzzlePack.air) {
                        block.setType(Material.AIR);
                        continue;
                    }
                    
                    if(random.nextInt(100) < PuzzlePack.percent) {
                        block.setType(Material.LEAVES);
                        continue;
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
        }
        
    }
    
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        
        if(player.getItemInHand().getType() != Material.HAY_BLOCK)
            return;
        
        Block startBlock = event.getBlock();
            
        Random random = new Random();


         if(random.nextInt(100) < PuzzlePack.percent) {
             startBlock.setType(Material.LEAVES);
             return;
         }

         startBlock.setType(Material.LOG);
         switch(random.nextInt(4)) {
             case 0: startBlock.setData((byte)0); break;
             case 1: startBlock.setData((byte)4); break;
             case 2: startBlock.setData((byte)8); break;
             case 3: startBlock.setData((byte)12); break;
         }
    }
    
 }
