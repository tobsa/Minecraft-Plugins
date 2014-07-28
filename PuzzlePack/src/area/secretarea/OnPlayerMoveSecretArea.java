package area.secretarea;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import puzzlepack.PuzzlePack;

public class OnPlayerMoveSecretArea implements Listener {
    private PuzzlePack plugin;
    
    public OnPlayerMoveSecretArea(PuzzlePack plugin) {
        this.plugin = plugin;
    }
        
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Block block = event.getTo().getBlock();
        Player player = event.getPlayer();
        
        for(SecretArea area : plugin.getAreaManager().getSecretAreas()) {
            if(area.isInside(player, block)) {
                player.sendMessage("Secret area found!");       
                player.playSound(player.getLocation(), Sound.ORB_PICKUP, 1, 1);
            }
        }
    }
}
