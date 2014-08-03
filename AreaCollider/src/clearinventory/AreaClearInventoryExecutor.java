package clearinventory;

import area.Area;
import area.AreaManager;
import areacollider.PlayerMessage;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AreaClearInventoryExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private WorldEditPlugin worldEdit;
    
    public AreaClearInventoryExecutor(AreaManager areaManager, WorldEditPlugin worldEdit) {
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
            player.sendMessage(PlayerMessage.missingRegionSelection());
            return true;
        }
        
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        if(areaManager.getArea(player.getName(), args[0]) != null) {
            player.sendMessage(PlayerMessage.areaExists(args[0]));
            return true;
        }

        Block block1 = selection.getMinimumPoint().getBlock();
        Block block2 = selection.getMaximumPoint().getBlock();
                
        areaManager.addArea(new Area(player.getName(), args[0], block1, block2, new ClearInventoryResponse()));
        player.sendMessage(PlayerMessage.areaCreated(args[0]));
        
        return true;
    }
}
