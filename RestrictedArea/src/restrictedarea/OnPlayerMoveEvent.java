package restrictedarea;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMoveEvent implements Listener {
    private static int count = 0;    
    
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        
        SubArea area = RestrictedArea.area;
        if(area == null)
            return;
        
        
        if(area.contains(event.getTo()))
            event.getPlayer().sendMessage("Collision: " + count++);
        
    }
}
 