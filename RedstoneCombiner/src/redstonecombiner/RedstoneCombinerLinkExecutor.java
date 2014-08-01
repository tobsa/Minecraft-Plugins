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
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        Combiner combiner = combinerManager.getCombiner(player.getName(), args[0]);
        if(combiner == null) {
            player.sendMessage(PlayerMessage.getMissingCombiner(args[0]));
            return true;
        }
        
        Block block = player.getTargetBlock(null, 6);
        if(!(block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
            player.sendMessage(PlayerMessage.getInvalidBlock());
            return true;
        }
                
        combiner.addLink(block);
        player.sendMessage(PlayerMessage.getCombinerAddLink(args[0]));
        
        return true;
    }
    
}
