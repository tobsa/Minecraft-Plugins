package redstonecombiner;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RedstoneCombinerDeleteExecutor implements CommandExecutor {
    private CombinerManager combinerManager;
    
    public RedstoneCombinerDeleteExecutor(CombinerManager combinerManager) {
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
                
        combinerManager.removeCombiner(combiner);
        FileManager.save(combinerManager);
        player.sendMessage(PlayerMessage.combinerRemoved(args[0]));
        
        return true;
    }
    
}
