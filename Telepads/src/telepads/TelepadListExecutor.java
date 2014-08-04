package telepads;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TelepadListExecutor implements CommandExecutor {
    private TelepadManager telepadManager;

    public TelepadListExecutor(TelepadManager telepadManager) {
        this.telepadManager = telepadManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
                        
        player.sendMessage(PlayerMessage.listHeader());
        
        int number = 1;
        for(Telepad telepad : telepadManager.getTelepads())
            if(telepad.getPlayerName().equalsIgnoreCase(player.getPlayerListName()))
                player.sendMessage(number++ + ". " + telepad.getName());

        return true;
    }

}
