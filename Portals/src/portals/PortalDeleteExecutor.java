package portals;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalDeleteExecutor implements CommandExecutor {
    private PortalManager portalManager;
    
    public PortalDeleteExecutor(PortalManager portalManager) {
        this.portalManager = portalManager;
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
        
        Portal portal = portalManager.getPortal(player.getName(), args[0]);
        if(portal == null) {
            player.sendMessage(PlayerMessage.missingPortal(args[0]));
            return true;
        }
               
        portalManager.removePortal(portal);
        FileManager.removePortal(portal);
        player.sendMessage(PlayerMessage.portalRemoved(args[0]));
        
        return true;
    }
    
}
