package telepad;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import puzzlepack.PuzzlePack;

public class TelepadExecutor implements CommandExecutor {
    private PuzzlePack plugin;

    public TelepadExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /telepad <name>");
            return true;
        }
        
        if(!plugin.getTelepadManager().isAvailable(args[0])) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' already exists!");
            return true;
        }
        
        plugin.getTelepadManager().createTelepad(args[0], player.getPlayerListName());
        
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Telepad '" + args[0] + "' was created.");

        return true;
    }

}
