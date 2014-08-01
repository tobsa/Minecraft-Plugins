package redstonecombiner;

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
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        Combiner combiner = combinerManager.getCombiner(player.getName(), args[0]);
        if(combiner == null) {
            player.sendMessage(PlayerMessage.getMissingCombiner(args[0]));
            return true;
        }
                
        if(combiner.removeLink(player.getTargetBlock(null, 6)))
            player.sendMessage(PlayerMessage.getLinkRemoved(args[0]));
        else
            player.sendMessage(PlayerMessage.getMissingLink(args[0]));
        
        return true;
    }
    
}
