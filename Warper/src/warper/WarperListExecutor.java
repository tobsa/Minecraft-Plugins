package warper;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarperListExecutor implements CommandExecutor {
    private Warper plugin;
    
    public WarperListExecutor(Warper plugin) {
        this.plugin = plugin;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        player.sendMessage(PlayerMessage.listHeader());

        int number = 1;
        if (plugin.getConfig().contains(player.getName())) {
            for (String position : plugin.getConfig().getConfigurationSection(player.getName()).getKeys(false)) {
                player.sendMessage(number++ + ". " + position);
            }
        }
        
        return true;
    }
    
}
