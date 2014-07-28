package pushblocks;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import puzzlepack.PuzzlePack;

public class OnBlockPushUp implements Listener {
    private PuzzlePack plugin;

    public OnBlockPushUp(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (event.getPlayer().getItemInHand() == null)
                return;

            ArrayList<Block> blocks = (ArrayList<Block>) player.getLastTwoTargetBlocks(null, 50);

            // Get the block a player clicked on
            Block clickBlock = blocks.get(1);
            if (clickBlock.getType() != Material.WOOL)
                return;

            // Get the color of the clicked block
            DyeColor color = DyeColor.getByData(clickBlock.getData());

            if (event.getPlayer().getItemInHand().getType() == Material.FEATHER) {
                if (plugin.isToClose(player, clickBlock, 1.75)) {
                    player.sendMessage(ChatColor.RED + "You're standing to close to move the block!");
                    return;
                }

                // Move a purple block up (if possible)
                if (color.equals(DyeColor.PURPLE) && clickBlock.getRelative(BlockFace.UP).getType() == Material.AIR) {
                    clickBlock.setType(Material.AIR);
                    clickBlock.getRelative(BlockFace.UP).setType(Material.WOOL);
                    clickBlock.getRelative(BlockFace.UP).setData(DyeColor.PURPLE.getData());
                    player.playSound(player.getLocation(), Sound.STEP_WOOL, 1, 1);
                }
            }
        }
    }
}
