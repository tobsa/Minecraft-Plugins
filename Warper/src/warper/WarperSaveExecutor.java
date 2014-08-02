package warper;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarperSaveExecutor implements CommandExecutor {
    private Warper plugin;
    
    public WarperSaveExecutor(Warper plugin) {
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

        plugin.getConfig().set(player.getName() + "." + args[0] + ".x", player.getLocation().getX());
        plugin.getConfig().set(player.getName() + "." + args[0] + ".y", player.getLocation().getY());
        plugin.getConfig().set(player.getName() + "." + args[0] + ".z", player.getLocation().getZ());
        plugin.getConfig().set(player.getName() + "." + args[0] + ".yaw", player.getLocation().getYaw());
        plugin.getConfig().set(player.getName() + "." + args[0] + ".pitch", player.getLocation().getPitch());
        plugin.saveConfig();

        player.sendMessage(PlayerMessage.positionSaved(args[0]));
        
        return true;
    }
    
}
