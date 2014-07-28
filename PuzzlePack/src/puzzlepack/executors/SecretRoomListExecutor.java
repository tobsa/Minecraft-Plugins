package puzzlepack.executors;

import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.AreaSecretRoom;
import puzzlepack.PuzzlePack;

public class SecretRoomListExecutor implements CommandExecutor {
    private List<AreaSecretRoom> areas;

    public SecretRoomListExecutor(PuzzlePack plugin) {
        this.areas = plugin.getAreaManager().getSecretRooms();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
                        
        player.sendMessage(ChatColor.LIGHT_PURPLE + "======= Secret Rooms =======");
        
        for(AreaSecretRoom area : areas)
            if(area.getPlayerName().equalsIgnoreCase(player.getPlayerListName()))
                player.sendMessage(area.getName());

        return true;
    }

}
