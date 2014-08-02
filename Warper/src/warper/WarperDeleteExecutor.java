package warper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarperDeleteExecutor implements CommandExecutor {
    private Warper plugin;
    
    public WarperDeleteExecutor(Warper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        if (args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }

        if (!plugin.getConfig().contains(player.getName() + "." + args[0])) {
            player.sendMessage(PlayerMessage.invalidPosition(args[0]));
            return true;
        }

        plugin.getConfig().set(player.getName() + "." + args[0], null);
        plugin.saveConfig();

        player.sendMessage(PlayerMessage.positionDeleted(args[0]));
        return true;
    }
    
}
