package puzzlepack.executors;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.AreaTeleportRoom;
import puzzlepack.PuzzlePack;

public class TeleportRoomSelectionExecutor implements CommandExecutor {
    private PuzzlePack plugin;
    private WorldEditPlugin we;

    public TeleportRoomSelectionExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
        this.we = plugin.getWorldEdit();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
                
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /teleportroomselection <AreaName>");
            return true;
        }
        
        AreaTeleportRoom area = plugin.getAreaManager().getTeleportRoom(player.getPlayerListName(), args[0]);
        if(area == null) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' doesn't exist!");
            return true;
        }
        
        we.setSelection(player, new CuboidSelection(plugin.getServer().getWorld("world"), area.getBlock1().getLocation(), area.getBlock2().getLocation()));
        
        return true;
    }

}
