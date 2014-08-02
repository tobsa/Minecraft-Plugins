package blockglider;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class OnPlayerMove implements Listener {
    private BlockGlider plugin;
    
    public OnPlayerMove(BlockGlider plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {        
        Player player = event.getPlayer();
        Block block = event.getTo().getBlock().getRelative(BlockFace.UP).getRelative(BlockFace.UP);
        player.setAllowFlight(true);
        
//        if(player.getGameMode() == GameMode.CREATIVE)
//            return;

        if(plugin.path1 != null && plugin.path1.intersects(block)) {
            double y = plugin.path1.getMinBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getY();
            
            Location location = new Location(block.getWorld(), player.getLocation().getX(), y, player.getLocation().getZ());
            player.setFlying(true);
            
            switch(plugin.path1.getDirection()) {
                case North: player.setVelocity(new Vector(0, 0, -plugin.path1.getVelocity())); break;
                case East:  player.setVelocity(new Vector(plugin.path1.getVelocity(), 0, 0)); break;
                case South: player.setVelocity(new Vector(0, 0, plugin.path1.getVelocity())); break;
                case West:  player.setVelocity(new Vector(-plugin.path1.getVelocity(), 0, 0)); break;
                case Up:    player.setVelocity(new Vector(0, -plugin.path1.getVelocity(), 0)); break;
                case Down:  player.setVelocity(new Vector(0, plugin.path1.getVelocity(), 0)); break;
            }
        }
        else
            player.setFlying(false);        
    }
    
}
