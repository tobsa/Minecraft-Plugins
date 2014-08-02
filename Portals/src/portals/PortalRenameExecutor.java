package portals;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalRenameExecutor implements CommandExecutor {
    private PortalManager portalManager;
    
    public PortalRenameExecutor(PortalManager portalManager) {
        this.portalManager = portalManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        if(args.length != 2) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Portal portal = portalManager.getPortal(player.getName(), args[0]);
        if(portal == null) {
            player.sendMessage(PlayerMessage.missingPortal(args[0]));
            return true;
        }
        
        if(portalManager.getPortal(player.getName(), args[1]) != null) {
            player.sendMessage(PlayerMessage.portalExist(args[1]));
            return true;
        }
               
        portalManager.removePortal(portal);
        FileManager.removePortal(portal);
        
        portal.setName(args[1]);
        portalManager.addPortal(portal);
        FileManager.savePortal(portal);
        
        player.sendMessage(PlayerMessage.portalRenamed(args[0], args[1]));
        
        return true;
    }
    
}
