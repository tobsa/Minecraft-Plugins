package area.teleportarea;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.PuzzlePack;

public class TeleportAreaDeleteExecutor implements CommandExecutor {
    private PuzzlePack plugin;

    public TeleportAreaDeleteExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invald arguments. Usage: /teleportroomdelete <AreaName>");
            return true;
        }
        
        TeleportArea area = plugin.getAreaManager().getTeleportArea(player.getPlayerListName(), args[0]);
        if(area == null) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' doesn't exist!");
            return true;
        }     
        
        plugin.getAreaManager().removeTeleportArea(area);
        player.sendMessage("'" + args[0] + "' has been removed!");
        
        return true;
    }

}
