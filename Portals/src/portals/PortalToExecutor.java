package portals;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalToExecutor implements CommandExecutor {
    private PortalManager portalManager;
    
    public PortalToExecutor(PortalManager portalManager) {
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
        
        portal.setTeleportLocation(player.getLocation());
        FileManager.saveTeleportLocation(args[0], player.getLocation());
        player.sendMessage(PlayerMessage.teleportLocationUpdated(args[0]));
        
        return true;
    }
    
}
