package puzzlepack.executors;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.AreaSecretRoom;
import puzzlepack.PuzzlePack;

public class SecretRoomSelectionExecutor implements CommandExecutor {
    private PuzzlePack plugin;
    private WorldEditPlugin we;

    public SecretRoomSelectionExecutor(PuzzlePack plugin) {
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
            player.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /secretroomselection <AreaName>");
            return true;
        }
        
        AreaSecretRoom area = plugin.getAreaManager().getSecretRoom(player.getPlayerListName(), args[0]);
        if(area == null) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' doesn't exist!");
            return true;
        }
        
        we.setSelection(player, new CuboidSelection(plugin.getServer().getWorld("world"), area.getBlock1().getLocation(), area.getBlock2().getLocation()));
        
        return true;
    }

}
