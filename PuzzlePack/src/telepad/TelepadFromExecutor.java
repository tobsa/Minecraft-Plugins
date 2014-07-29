package telepad;

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

public class TelepadFromExecutor implements CommandExecutor {
    private PuzzlePack plugin;

    public TelepadFromExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /telepadfrom <name>");
            return true;
        }
        
        Telepad telepad = plugin.getTelepadManager().getTelepad(player.getPlayerListName(), args[0]);
        if(telepad == null) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' doesn't exist!");
            return true;
        }
        
        Block below = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        Wool wool   = new Wool(below.getType(), below.getData());
        
        if(below.getType() != Material.WOOL || wool.getColor() != DyeColor.BLUE) {
            player.sendMessage(ChatColor.RED + "You must stand above a blue wool block!");
            return true;
        }
        
        telepad.setFrom(player.getLocation());
        plugin.getTelepadManager().saveFrom(telepad);
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Telepad '" + args[0] + "' from-block was updated!");

        return true;
    }

}
