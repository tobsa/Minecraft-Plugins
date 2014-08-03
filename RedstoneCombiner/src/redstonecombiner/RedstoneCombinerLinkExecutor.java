package redstonecombiner;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RedstoneCombinerLinkExecutor implements CommandExecutor {
    private CombinerManager combinerManager;
    
    public RedstoneCombinerLinkExecutor(CombinerManager combinerManager) {
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
        
        Block block = player.getTargetBlock(null, 6);
        if(!(block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
            player.sendMessage(PlayerMessage.invalidBlock());
            return true;
        }
                
        combiner.addLink(block);
        FileManager.saveLink(combiner.getName(), block);
        player.sendMessage(PlayerMessage.combinerAddLink(args[0]));
        
        return true;
    }
    
}
