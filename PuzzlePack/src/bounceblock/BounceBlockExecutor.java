package bounceblock;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;

import puzzlepack.PuzzlePack;

public class BounceBlockExecutor implements CommandExecutor {
    private PuzzlePack plugin;

    public BounceBlockExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /bounceblock <JumpStrength>");
            return true;
        }
        
        try {   
            Block block = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
            double jumpStrength = Double.valueOf(args[0]);
            
            // Check if a bounce block is below the player
            BounceBlock bounceBlock = plugin.getBounceBlockManager().getBounceBlock(block);
            if(bounceBlock == null) {
                // Make sure the block needs to be a white wool block
                if(block.getType() != Material.WOOL || new Wool(block.getType(), block.getData()).getColor() != DyeColor.WHITE) {
                    player.sendMessage(ChatColor.RED + "You must stand above a white wool block!");
                    return true;
                }

                plugin.getBounceBlockManager().addBounceBlock(block, jumpStrength);
                player.sendMessage(ChatColor.LIGHT_PURPLE + "A bounce block was created!");
            }
        } catch(NumberFormatException ex) {
            player.sendMessage(ChatColor.RED + args[0] + " must be a number!");
            return true;
        }
        
        return true;
    }

}
