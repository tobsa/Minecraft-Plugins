package pushblocks;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PathExecutor implements CommandExecutor {
    private PathManager pathManager;
    private WorldEditPlugin worldEdit;
    
    public PathExecutor(PathManager pathManager, WorldEditPlugin worldEdit) {
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
       
        Selection selection = worldEdit.getSelection(player);
        if(selection == null) {
            player.sendMessage(PlayerMessage.missingSelection());
            return true;
        }
        
        if(!isSelectionValid(selection)) {
            player.sendMessage(PlayerMessage.invalidSelection());
            return true;
        }
        
        Block minimumBlock = selection.getMinimumPoint().getBlock();
        Block maximumBlock = selection.getMaximumPoint().getBlock();
        
        if(!pathManager.isValid(minimumBlock, maximumBlock)) {
            player.sendMessage(PlayerMessage.invalidPath());
            return true;
        }
        
        Path path = new Path(minimumBlock, maximumBlock);
        pathManager.addPath(path);
        FileManager.save(pathManager);
        player.sendMessage(PlayerMessage.pathCreated(path));
        
        return true;
    }
    
    private Integer getInteger(String argument) {
        try {
            return Integer.valueOf(argument);
        } catch(NumberFormatException ex) {
            return null;
        }
    }
    
    private boolean isSelectionValid(Selection selection) {
        int numBigSelections = 0;
        if(selection.getWidth() > 1)
            numBigSelections++;
        if(selection.getHeight() > 1)
            numBigSelections++;
        if(selection.getLength() > 1)
            numBigSelections++;
        
        return numBigSelections == 1;
    }
}
