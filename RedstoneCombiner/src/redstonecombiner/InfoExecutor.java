package redstonecombiner;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InfoExecutor implements CommandExecutor {
    private CombinerManager combinerManager;
    
    public InfoExecutor(CombinerManager combinerManager) {
        this.combinerManager = combinerManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        if(args.length != 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
         
        Block block = player.getTargetBlock(null, 6);
        Combiner combiner1 = combinerManager.getCombinerByToggleBlock(player.getName(), block);
        Combiner combiner2 = combinerManager.getCombinerByLink(player.getName(), block);
        
        if(combiner1 == null && combiner2 == null) {
            player.sendMessage(PlayerMessage.missingInfo(block));
            return true;
        }
        
        player.sendMessage(PlayerMessage.infoHeader());
        
        if(combiner1 == null)
            player.sendMessage(PlayerMessage.info(combiner2.getName()));
        else
            player.sendMessage(PlayerMessage.info(combiner1.getName()));
        
        player.sendMessage(PlayerMessage.blockInfo(block));
                
        return true;
    }
    
}
