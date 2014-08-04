package telepads;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TelepadDeleteExecutor implements CommandExecutor {
    private TelepadManager telepadManager;

    public TelepadDeleteExecutor(TelepadManager telepadManager) {
        this.telepadManager = telepadManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Telepad telepad = telepadManager.getTelepad(player.getPlayerListName(), args[0]);
        if(telepad == null) {
            player.sendMessage(PlayerMessage.getMissingTelepad(args[0]));
            return true;
        }     
        
        telepadManager.removeTelepad(telepad);
        FileManager.save(telepadManager);
        player.sendMessage(PlayerMessage.telepadDestroyed(args[0]));
        
        return true;
    }

}
