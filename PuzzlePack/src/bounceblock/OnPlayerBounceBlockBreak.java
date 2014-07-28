package bounceblock;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import puzzlepack.PuzzlePack;

public class OnPlayerBounceBlockBreak implements Listener {
    private PuzzlePack plugin;
    
    public OnPlayerBounceBlockBreak(PuzzlePack plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if(plugin.getBounceBlockManager().removeBlock(event.getBlock()))
            event.getPlayer().sendMessage(ChatColor.LIGHT_PURPLE + "A bounce block was destroyed!");
    }
}
