package puzzlepack.executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.Area;
import puzzlepack.PuzzlePack;

public class TeleportRoomListExecutor implements CommandExecutor {
    private PuzzlePack plugin;

    public TeleportRoomListExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        player.sendMessage(ChatColor.LIGHT_PURPLE + "======= Teleport Rooms =======");
        for(Area area : plugin.getAreaManager().getTeleportRooms())
            if(area.getPlayerName().equalsIgnoreCase(player.getPlayerListName()))
                player.sendMessage(area.getName());

        return true;
    }

}
