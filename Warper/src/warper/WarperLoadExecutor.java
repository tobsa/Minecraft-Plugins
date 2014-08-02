package warper;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarperLoadExecutor implements CommandExecutor {
    private Warper plugin;
    
    public WarperLoadExecutor(Warper plugin) {
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

        double x = plugin.getConfig().getDouble(player.getName() + "." + args[0] + ".x");
        double y = plugin.getConfig().getDouble(player.getName() + "." + args[0] + ".y");
        double z = plugin.getConfig().getDouble(player.getName() + "." + args[0] + ".z");
        float yaw = (float)plugin.getConfig().getDouble(player.getName() + "." + args[0] + ".yaw");
        float pitch = (float)plugin.getConfig().getDouble(player.getName() + "." + args[0] + ".pitch");

        player.teleport(new Location(player.getWorld(), x, y, z, yaw, pitch));
        return true;
    }
    
}
