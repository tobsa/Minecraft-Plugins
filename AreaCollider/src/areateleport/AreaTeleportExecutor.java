package areateleport;

import area.Area;
import area.AreaManager;
import areacollider.AreaCollider;
import areacollider.PlayerMessage;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AreaTeleportExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private WorldEditPlugin worldEdit;
    
    public AreaTeleportExecutor(AreaManager areaManager, WorldEditPlugin worldEdit) {
        this.areaManager = areaManager;
        this.worldEdit = worldEdit;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        Selection selection = worldEdit.getSelection(player);

        if (selection == null) {
            player.sendMessage(PlayerMessage.getMissingRegionSelection());
            return true;
        }
        
        if(args.length < 2) {
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        if(areaManager.getArea(player.getName(), args[0]) != null) {
            player.sendMessage(PlayerMessage.getAreaExists(args[0]));
            return true;
        }

        Block block1 = selection.getMinimumPoint().getBlock();
        Block block2 = selection.getMaximumPoint().getBlock();
                
        if(args.length == 1)
            areaManager.addArea(new Area(player.getName(), args[0], block1, block2, new AreaTeleportResponse(player.getLocation(), "")));
        else
            areaManager.addArea(new Area(player.getName(), args[0], block1, block2, new AreaTeleportResponse(player.getLocation(), AreaCollider.combineArguments(args, 1))));
        
        player.sendMessage(PlayerMessage.getAreaCreated(args[0]));  
        return true;
    }
}
