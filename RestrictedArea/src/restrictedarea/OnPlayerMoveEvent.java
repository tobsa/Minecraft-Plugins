package restrictedarea;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMoveEvent implements Listener {
    private AreaManager areaManager;
    
    public OnPlayerMoveEvent(AreaManager areaManager) {
        this.areaManager = areaManager;
    }
    
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        
        for(Area area : areaManager.get())
            if(area.contains(player.getLocation())) {
                player.teleport(area.getLocation());
                player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                if(area.getMessage() != null && !area.getMessage().isEmpty())
                    player.sendMessage(area.getMessage());
            }
                    
        
    }
}
 