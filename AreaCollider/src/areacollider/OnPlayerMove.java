package areacollider;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMove implements Listener {
    private AreaManager areaManager;
    
    public OnPlayerMove(AreaManager areaManager) {
        this.areaManager = areaManager;
    }
        
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {               
        for(Area area : areaManager.getAreas()) {
            if(area.hasPlayerEntered(event.getPlayer().getName(), event.getTo().getBlock()))
                area.onCollision(event.getPlayer());
        }
    }
}
