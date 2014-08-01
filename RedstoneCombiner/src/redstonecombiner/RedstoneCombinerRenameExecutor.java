package redstonecombiner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RedstoneCombinerRenameExecutor implements CommandExecutor {
    private CombinerManager combinerManager;
    
    public RedstoneCombinerRenameExecutor(CombinerManager combinerManager) {
        this.combinerManager = combinerManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        if(args.length != 2) {
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        Combiner combiner = combinerManager.getCombiner(player.getName(), args[0]);
        if(combiner == null) {
            player.sendMessage(PlayerMessage.getMissingCombiner(args[0]));
            return true;
        }
        
        if(combinerManager.getCombiner(player.getName(), args[1]) != null) {
            player.sendMessage(PlayerMessage.getCombinerExists(args[1]));
            return true;
        }
        
        combinerManager.renameCombiner(combiner, args[1]);
        player.sendMessage(PlayerMessage.getCombinerRenamed(args[0], args[1]));
        
        return true;
    }
    
}
