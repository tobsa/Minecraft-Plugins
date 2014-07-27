package puzzlepack;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class OnBlazeRodInteractEvent implements Listener {

    private PuzzlePack plugin;

    public OnBlazeRodInteractEvent(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        // If a player right clicked with an item in hand
        if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getItemInHand() != null) {
            // If the player holds a blaze rod
            if (player.getItemInHand().getType() == Material.BLAZE_ROD) {
                Block block = player.getTargetBlock(null, 50);

                if (block.getType() == Material.WOOL) {
                    if (plugin.isToClose(player, block, 1.75)) {
                        player.sendMessage(ChatColor.RED + "You're standing to close to teleport!");
                        return;
                    }

                    // Make sure to preserve the players pitch and yaw when teleporting
                    float pitch = player.getLocation().getPitch();
                    float yaw = player.getLocation().getYaw();

                    // Get the color of the clicked block
                    DyeColor color = DyeColor.getByData(block.getData());

                    // Handle brown wool block
                    if (color == DyeColor.BROWN) {
                        int x = block.getRelative(BlockFace.UP).getX();
                        int y = block.getRelative(BlockFace.UP).getY();
                        int z = block.getRelative(BlockFace.UP).getZ();

                        player.teleport(new Location(player.getWorld(), x + 0.5, y, z + 0.5, yaw, pitch));
                        player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);
                    }

                    // Handle green wool block
                    if (color == DyeColor.GREEN) {
                        int x = block.getRelative(BlockFace.DOWN).getX();
                        int y = block.getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getY();
                        int z = block.getRelative(BlockFace.DOWN).getZ();

                        player.teleport(new Location(player.getWorld(), x + 0.5, y, z + 0.5, yaw, pitch));
                        player.playSound(player.getLocation(), Sound.ENDERDRAGON_WINGS, 1, 1);
                    }
                }
            }
        }
    }
}
