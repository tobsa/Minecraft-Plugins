package interactblocks;

import java.util.List;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteExecutor implements CommandExecutor {
    private InteractBlockManager blockManager;
    
    public DeleteExecutor(InteractBlockManager blockManager) {
        this.blockManager = blockManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        if(args.length != 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        List<InteractBlock> blocks = blockManager.getInteractBlocks(player.getTargetBlock(null, 6));
        if(!blocks.isEmpty()) {
            for(InteractBlock block : blocks) {
                blockManager.removeInteractBlock(block);
                player.sendMessage(PlayerMessage.interactBlockRemoved(block));
            }
            
            FileManager.save(blockManager);
        }
        else
            player.sendMessage(PlayerMessage.missingInteractBlock());
        
        return true;
    }
}
