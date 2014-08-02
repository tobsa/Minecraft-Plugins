package areacollider;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

public class HelpExecutor implements CommandExecutor {
    private List<PluginCommand> commands;
    
    public HelpExecutor(List<PluginCommand> commands) {
        this.commands = commands;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {        
        if (!(sender instanceof Player))
            return true;

        Player player = (Player)sender;
                
        player.sendMessage(PlayerMessage.help());
        
        for(PluginCommand pluginCommand : commands)
            player.sendMessage(" " + pluginCommand.getUsage());
        
        return true;
    }
}
