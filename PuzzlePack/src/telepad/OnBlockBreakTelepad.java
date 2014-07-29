package telepad;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import puzzlepack.PuzzlePack;

public class OnBlockBreakTelepad implements Listener {
    private PuzzlePack plugin;
    
    public OnBlockBreakTelepad(PuzzlePack plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block   = event.getBlock();
        
        for(Telepad telepad : plugin.getTelepadManager().getTelepads()) {
            if(!telepad.isActive())
                continue;
            
            if(block.equals(telepad.getFrom().getBlock().getRelative(BlockFace.DOWN))) {
                plugin.getTelepadManager().removeFrom(telepad);
                player.sendMessage("Telepad '" + telepad.getName() + "' from-block was removed!");
            }
                
            if(block.equals(telepad.getTo().getBlock().getRelative(BlockFace.DOWN))) {
                plugin.getTelepadManager().removeTo(telepad);
                player.sendMessage("Telepad '" + telepad.getName() + "' to-block was removed!");
            }
        }
    }
}
