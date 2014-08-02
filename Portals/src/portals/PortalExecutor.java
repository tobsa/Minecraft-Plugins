package portals;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalExecutor implements CommandExecutor {
    private PortalManager portalManager;
    private WorldEditPlugin worldEdit;
    
    public PortalExecutor(PortalManager portalManager, WorldEditPlugin worldEdit) {
        this.portalManager = portalManager;
        this.worldEdit = worldEdit;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        Selection selection = worldEdit.getSelection(player);
        if(selection == null) {
            player.sendMessage(PlayerMessage.invalidSelection());
            return true;
        }
        
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        if(portalManager.getPortal(player.getName(), args[0]) != null) {
            player.sendMessage(PlayerMessage.portalExist(args[0]));
            return true;
        }
        
        Block minimumBlock = selection.getMinimumPoint().getBlock();
        Block maximumBlock = selection.getMaximumPoint().getBlock();
        Location teleportLocation = player.getLocation();
        
        
        Portal portal = new Portal(args[0], player.getName(), minimumBlock, maximumBlock, teleportLocation);
        portalManager.addPortal(portal);
        FileManager.savePortal(portal);        
        
        player.sendMessage(PlayerMessage.portalCreated(args[0]));
        
        return true;
    }
    
}
