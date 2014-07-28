package area.teleportarea;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import puzzlepack.PuzzlePack;

public class OnPlayerMoveTeleportArea implements Listener {
    private PuzzlePack plugin;
    
    public OnPlayerMoveTeleportArea(PuzzlePack plugin) {
        this.plugin = plugin;
    }
        
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Block block = event.getTo().getBlock();
        Player player = event.getPlayer();
        
        for(TeleportArea area : plugin.getAreaManager().getTeleportAreas()) {
            if(area.isInside(player, block)) {
                double x = area.getTeleport().getX();
                double y = area.getTeleport().getY();
                double z = area.getTeleport().getZ();
                
                Location location = new Location(player.getWorld(), x, y, z, area.getYaw(), area.getPitch());
                player.teleport(location);
                player.playSound(location, Sound.ENDERMAN_TELEPORT, 1, 1);
                
                if(area.hasMessage())
                    player.sendMessage(area.getMessage());
            }
        }
    }
}
