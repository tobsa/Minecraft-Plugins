package redstonecombiner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RenameExecutor implements CommandExecutor {
    private CombinerManager combinerManager;
    
    public RenameExecutor(CombinerManager combinerManager) {
        this.combinerManager = combinerManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        if(args.length != 2) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Combiner combiner = combinerManager.getCombiner(player.getName(), args[0]);
        if(combiner == null) {
            player.sendMessage(PlayerMessage.missingCombiner(args[0]));
            return true;
        }
        
        if(combinerManager.getCombiner(player.getName(), args[1]) != null) {
            player.sendMessage(PlayerMessage.combinerExists(args[1]));
            return true;
        }
        
        combinerManager.renameCombiner(combiner, args[1]);
        FileManager.save(combinerManager);
        player.sendMessage(PlayerMessage.combinerRenamed(args[0], args[1]));
        
        return true;
    }
    
}
