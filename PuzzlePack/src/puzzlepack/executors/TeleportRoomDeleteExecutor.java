package puzzlepack.executors;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.AreaTeleportRoom;
import puzzlepack.PuzzlePack;

public class TeleportRoomDeleteExecutor implements CommandExecutor {
    private List<AreaTeleportRoom> teleportRooms;
    private PuzzlePack plugin;

    public TeleportRoomDeleteExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
        this.teleportRooms = plugin.getAreaManager().getTeleportRooms();
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
        
        AreaTeleportRoom area = plugin.getAreaManager().getTeleportRoom(player.getPlayerListName(), args[0]);
        if(area == null) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' doesn't exist!");
            return true;
        }     
        
        teleportRooms.remove(area);
        player.sendMessage("'" + args[0] + "' has been removed!");
        plugin.getConfig().set("teleportrooms." + area.getName(), null);
        plugin.saveConfig();
        
        return true;
    }

}
