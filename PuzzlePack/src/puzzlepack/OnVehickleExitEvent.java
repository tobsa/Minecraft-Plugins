package puzzlepack;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class OnVehickleExitEvent implements Listener {
    
    @EventHandler
    public void onExitWithMinecraft(VehicleExitEvent event) {
        Block block = event.getExited().getLocation().getBlock();
                
        if(block.getType() != Material.GOLD_BLOCK)
            event.setCancelled(true);
    }
}
