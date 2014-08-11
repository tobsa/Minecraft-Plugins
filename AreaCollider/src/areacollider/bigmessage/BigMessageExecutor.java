package areacollider.bigmessage;

import areacollider.Area;
import areacollider.AreaCollider;
import areacollider.AreaManager;
import areacollider.FileManager;
import areacollider.Message;
import areacollider.message.MessageResponse;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BigMessageExecutor implements CommandExecutor {
    private AreaManager areaManager;
    private WorldEditPlugin worldEdit;
    
    public BigMessageExecutor(AreaManager areaManager, WorldEditPlugin worldEdit) {
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
        
        if(args.length < 2) {
            player.sendMessage(Message.invalidArguments(command.getUsage()));
            return true;
        }
        
        if(areaManager.getArea(player.getName(), args[0]) != null) {
            player.sendMessage(Message.areaExists(args[0]));
            return true;
        }
        
        Block block1 = selection.getMinimumPoint().getBlock();
        Block block2 = selection.getMaximumPoint().getBlock();
        
        areaManager.addArea(new Area(player.getName(), args[0], block1, block2, new BigMessageResponse()));
        FileManager.save(areaManager);
        player.sendMessage(Message.areaCreated(args[0]));
        return true;
    }
}
