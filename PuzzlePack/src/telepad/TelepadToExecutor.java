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

public class TelepadToExecutor implements CommandExecutor {
    private PuzzlePack plugin;

    public TelepadToExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /telepadto <name>");
            return true;
        }
        
        Telepad telepad = plugin.getTelepadManager().getTelepad(player.getPlayerListName(), args[0]);
        if(telepad == null) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' doesn't exist!");
            return true;
        }
        
        Block below = player.getLocation().getBlock().getRelative(BlockFace.DOWN);
        Wool wool   = new Wool(below.getType(), below.getData());
        
        if(below.getType() != Material.WOOL || wool.getColor() != DyeColor.ORANGE) {
            player.sendMessage(ChatColor.RED + "You must stand above a orange wool block!");
            return true;
        }
        
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();
        
        telepad.setTo(player.getLocation(), yaw, pitch);
        plugin.getTelepadManager().saveTo(telepad);
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Telepad '" + args[0] + "' to-block was updated!");

        return true;
    }

}
