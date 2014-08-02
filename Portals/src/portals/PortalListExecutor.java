package portals;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalListExecutor implements CommandExecutor {
    private PortalManager portalManager;
    
    public PortalListExecutor(PortalManager portalManager) {
        this.portalManager = portalManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        if(args.length != 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }

        player.sendMessage(PlayerMessage.listHeader());
        
        int number = 1;
        for(Portal portal : portalManager.getPortals(player.getName())) {
            player.sendMessage(number++ + ". " + portal.getName());
        }
        
        return true;
    }
    
}
