package redstonecombiner;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RedstoneCombinerDelinkExecutor implements CommandExecutor {
    private CombinerManager combinerManager;
    
    public RedstoneCombinerDelinkExecutor(CombinerManager combinerManager) {
        this.combinerManager = combinerManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Combiner combiner = combinerManager.getCombiner(player.getName(), args[0]);
        if(combiner == null) {
            player.sendMessage(PlayerMessage.missingCombiner(args[0]));
            return true;
        }
                
        Block link = player.getTargetBlock(null, 6);
        if(combiner.removeLink(link)) {
            FileManager.save(combinerManager);
            player.sendMessage(PlayerMessage.linkRemoved(args[0]));    
        }
        else
            player.sendMessage(PlayerMessage.missingLink(args[0]));
        
        return true;
    }
    
}
