package telepad;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import puzzlepack.PuzzlePack;

public class TelepadListExecutor implements CommandExecutor {
    private PuzzlePack plugin;

    public TelepadListExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
                        
        player.sendMessage(ChatColor.LIGHT_PURPLE + "======= Telepads =======");
        
        for(Telepad telepad : plugin.getTelepadManager().getTelepads())
            if(telepad.getPlayerName().equalsIgnoreCase(player.getPlayerListName())) {
                player.sendMessage(telepad.getName());
                if(telepad.getFrom() != null)
                    player.sendMessage(" from: x: " + telepad.getFrom().getBlockX() + ", y: " + telepad.getFrom().getBlockY() + ", z: " + telepad.getFrom().getBlockZ() + "");
                if(telepad.getTo() != null)
                    player.sendMessage(" to: x: " + telepad.getTo().getBlockX() + ", y: " + telepad.getTo().getBlockY() + ", z: " + telepad.getTo().getBlockZ() + "");
            }

        return true;
    }

}
