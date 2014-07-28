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

public class OnBlockPushForward implements Listener {
    private PuzzlePack plugin;

    public OnBlockPushForward(PuzzlePack plugin) {
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

                // Make sure the black wool can only be moved sideways
                BlockFace face = blocks.get(1).getFace(blocks.get(0)).getOppositeFace();
                if (face.equals(BlockFace.DOWN) || face.equals(BlockFace.UP)) {
                    return;
                }

                // Get the block where a block should move
                Block moveBlock = blocks.get(1).getRelative(face);

                // Move the black wool block away from the player
                if (color.equals(DyeColor.BLACK) && moveBlock.getType() == Material.AIR) {
                    clickBlock.setType(Material.AIR);
                    moveBlock.setType(Material.WOOL);
                    moveBlock.setData(DyeColor.BLACK.getData());
                    player.playSound(player.getLocation(), Sound.STEP_WOOL, 1, 1);
                }
            }
        }
    }
}
