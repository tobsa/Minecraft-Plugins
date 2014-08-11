package areacollider.teleport;

import areacollider.Area;
import areacollider.AreaManager;
import areacollider.AreaCollider;
import areacollider.FileManager;
import areacollider.Message;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private WorldEditPlugin worldEdit;
    
    public TeleportExecutor(AreaManager areaManager, WorldEditPlugin worldEdit) {
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
            player.sendMessage(Message.missingRegionSelection());
            return true;
        }
        
        if(args.length < 1) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        if(areaManager.getArea(player.getName(), args[0]) != null) {
            player.sendMessage(Message.areaExists(args[0]));
            return true;
        }

        Block block1 = selection.getMinimumPoint().getBlock();
        Block block2 = selection.getMaximumPoint().getBlock();
                
        if(args.length == 1)
            areaManager.addArea(new Area(player.getName(), args[0], block1, block2, new TeleportResponse(player.getLocation(), "")));
        else
            areaManager.addArea(new Area(player.getName(), args[0], block1, block2, new TeleportResponse(player.getLocation(), AreaCollider.combineArguments(args, 1))));
        FileManager.save(areaManager);
        player.sendMessage(Message.areaCreated(args[0]));  
        return true;
    }
}
