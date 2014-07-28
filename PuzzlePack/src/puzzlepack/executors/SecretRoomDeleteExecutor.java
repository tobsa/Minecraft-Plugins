package puzzlepack.executors;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.AreaSecretRoom;
import puzzlepack.PuzzlePack;

public class SecretRoomDeleteExecutor implements CommandExecutor {
    private List<AreaSecretRoom> areas;
    private PuzzlePack plugin;

    public SecretRoomDeleteExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
        this.areas = plugin.getAreaManager().getSecretRooms();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invald arguments. Usage: /secretroomdelete <AreaName>");
            return true;
        }
        
        AreaSecretRoom area = plugin.getAreaManager().getSecretRoom(player.getPlayerListName(), args[0]);
        if(area == null) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' doesn't exist!");
            return true;
        }     
        
        areas.remove(area);
        player.sendMessage("'" + args[0] + "' has been removed!");
        plugin.getConfig().set("secretrooms." + area.getName(), null);
        plugin.saveConfig();
        
        return true;
    }

}
