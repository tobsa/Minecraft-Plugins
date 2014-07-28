package area.clearinventoryarea;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.PuzzlePack;

public class ClearInventoryAreaListExecutor implements CommandExecutor {
    private List<ClearInventoryArea> clearInventoryRooms;

    public ClearInventoryAreaListExecutor(PuzzlePack plugin) {
        this.clearInventoryRooms = plugin.getAreaManager().getClearInventoryAreas();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
                        
        player.sendMessage(ChatColor.LIGHT_PURPLE + "======= Clear inventory rooms =======");
        
        for(ClearInventoryArea area : clearInventoryRooms)
            if(area.getPlayerName().equalsIgnoreCase(player.getPlayerListName()))
                player.sendMessage(area.getName());

        return true;
    }

}
