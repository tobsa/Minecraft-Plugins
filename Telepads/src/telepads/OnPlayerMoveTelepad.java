package telepads;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMoveTelepad implements Listener {
    private TelepadManager telepadManager;
    
    public OnPlayerMoveTelepad(TelepadManager telepadManager) {
        this.telepadManager = telepadManager;
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {        
        Player player = event.getPlayer();
        
        for(Telepad telepad : telepadManager.getTelepads()) {
            if(telepad.isActive()) {                
                if(telepad.getFrom().getBlock().equals(player.getLocation().getBlock())) {
                    double x = telepad.getTo().getX();
                    double y = telepad.getTo().getY();
                    double z = telepad.getTo().getZ();
                    float yaw = telepad.getYaw();
                    float pitch = telepad.getPitch();
                    
                    player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
                    player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                }
            }
        }
    }
}
