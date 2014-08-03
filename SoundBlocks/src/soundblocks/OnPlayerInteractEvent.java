package soundblocks;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractEvent implements Listener {
    private SoundBlockManager soundBlockManager;
    
    public OnPlayerInteractEvent(SoundBlockManager soundBlockManager) {
        this.soundBlockManager = soundBlockManager;
    }
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK)
            return;
        
        SoundBlock soundBlock = soundBlockManager.getSoundBlock(event.getClickedBlock());
        if(soundBlock != null)
            event.getPlayer().playSound(soundBlock.getBlock().getLocation(), soundBlock.getSound(), 1, 1);
        
    }
}
