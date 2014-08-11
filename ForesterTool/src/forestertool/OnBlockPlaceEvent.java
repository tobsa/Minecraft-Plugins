package forestertool;

import java.util.Random;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class OnBlockPlaceEvent implements Listener {
    private Tool tool;
    
    public OnBlockPlaceEvent(Tool tool) {
        this.tool = tool;
    }
    
    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        
        if(player.getItemInHand().getType() != Material.STONE_AXE)
            return;
        
        Block block = event.getBlock();
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
