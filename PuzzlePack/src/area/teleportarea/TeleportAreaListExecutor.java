package area.teleportarea;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import area.Area;
import puzzlepack.PuzzlePack;

public class TeleportAreaListExecutor implements CommandExecutor {
    private PuzzlePack plugin;

    public TeleportAreaListExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        player.sendMessage(ChatColor.LIGHT_PURPLE + "======= Teleport Rooms =======");
        for(Area area : plugin.getAreaManager().getTeleportAreas())
            if(area.getPlayerName().equalsIgnoreCase(player.getPlayerListName()))
                player.sendMessage(area.getName());

        return true;
    }

}
