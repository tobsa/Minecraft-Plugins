package portals;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class OnPlayerPortalEvent implements Listener {
    private PortalManager portalManager;
    
    public OnPlayerPortalEvent(PortalManager portalManager) {
        this.portalManager = portalManager;
    }
        
    @EventHandler
    public void onPlayerPortalEvent(PlayerPortalEvent event) {
        Player player = event.getPlayer();
        
        for(Portal portal : portalManager.getPortals()) {
            if(portal.intersects(player.getLocation().getBlock())) {
                player.teleport(portal.getTeleportBlock());
            }
        }
    }
}
