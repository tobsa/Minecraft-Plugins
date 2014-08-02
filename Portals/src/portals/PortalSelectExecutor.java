package portals;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PortalSelectExecutor implements CommandExecutor {
    private PortalManager portalManager;
    private WorldEditPlugin worldEdit;
    
    public PortalSelectExecutor(PortalManager portalManager, WorldEditPlugin worldEdit) {
        this.portalManager = portalManager;
        this.worldEdit = worldEdit;
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
        
        Block minimumBlock = portal.getMinimumBlock();
        Block maximumBlock = portal.getMaximumBlock();
        
        worldEdit.setSelection(player, new CuboidSelection(player.getWorld(), minimumBlock.getLocation(), maximumBlock.getLocation()));
        return true;
    }
    
}
