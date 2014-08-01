package telepads;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class OnBlockBreakTelepad implements Listener {
    private TelepadManager telepadManager;
    
    public OnBlockBreakTelepad(TelepadManager telepadManager) {
        this.telepadManager = telepadManager;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block   = event.getBlock();
        
        for(Telepad telepad : telepadManager.getTelepads()) {
            if(!telepad.isActive())
                continue;
            
            if(block.equals(telepad.getFrom().getBlock().getRelative(BlockFace.DOWN))) {
                telepadManager.removeFrom(telepad);
                player.sendMessage(PlayerMessage.getFromBlockDestroyed(telepad.getName()));
            }
                
            if(block.equals(telepad.getTo().getBlock().getRelative(BlockFace.DOWN))) {
                telepadManager.removeTo(telepad);
                player.sendMessage(PlayerMessage.getToBlockDestroyed(telepad.getName()));
            }
        }
    }
}
