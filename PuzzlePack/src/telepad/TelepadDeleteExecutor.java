package telepad;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.PuzzlePack;

public class TelepadDeleteExecutor implements CommandExecutor {
    private PuzzlePack plugin;

    public TelepadDeleteExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invald arguments. Usage: /telepaddelete <name>");
            return true;
        }
        
        Telepad telepad = plugin.getTelepadManager().getTelepad(player.getPlayerListName(), args[0]);
        if(telepad == null) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' doesn't exist!");
            return true;
        }     
        
        plugin.getTelepadManager().removeTelepad(telepad);
        player.sendMessage("'" + args[0] + "' has been removed!");
        
        return true;
    }

}
