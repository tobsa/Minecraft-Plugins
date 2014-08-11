package puzzlepack;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractEvent implements Listener {
    
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
               
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        
         Material type = event.getItem().getType();
        
        if(!(type == Material.MINECART || 
             type == Material.STORAGE_MINECART ||
             type == Material.POWERED_MINECART ||
             type == Material.COMMAND_MINECART || 
             type == Material.HOPPER_MINECART || 
             type == Material.EXPLOSIVE_MINECART ||
             type == Material.HOPPER_MINECART))
            return;
                
        if(event.getClickedBlock().getRelative(BlockFace.DOWN).getType() != Material.GOLD_BLOCK)
            event.setCancelled(true);
        
        event.getPlayer().updateInventory();
    }
}
