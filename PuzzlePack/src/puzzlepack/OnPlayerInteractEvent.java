package puzzlepack;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnPlayerInteractEvent implements Listener {
    private PuzzlePack plugin;
    
    public OnPlayerInteractEvent(PuzzlePack plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
    }

}
