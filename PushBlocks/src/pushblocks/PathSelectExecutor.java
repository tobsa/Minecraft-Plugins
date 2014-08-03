package pushblocks;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PathSelectExecutor implements CommandExecutor {
    private PathManager pathManager;
    private WorldEditPlugin worldEdit;
    
    public PathSelectExecutor(PathManager pathManager, WorldEditPlugin worldEdit) {
        this.pathManager = pathManager;
        this.worldEdit = worldEdit;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {        
        if (!(sender instanceof Player))
            return true;

        Player player = (Player)sender;
        
        if(args.length != 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
               
        Path path = pathManager.getPath(player.getTargetBlock(null, 20));
        if(path == null) {
            player.sendMessage(PlayerMessage.missingPath());
            return true;
        }
        
        Block minimumBlock = path.getMinimumBlock();
        Block maximumBlock = path.getMaximumBlock();
        
        worldEdit.setSelection(player, new CuboidSelection(player.getWorld(), minimumBlock.getLocation(), maximumBlock.getLocation()));        
        return true;
    }
}
