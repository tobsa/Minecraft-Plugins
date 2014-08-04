package redstonecombiner;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RedstoneCombinerExecutor implements CommandExecutor {
    private CombinerManager combinerManager;
    
    public RedstoneCombinerExecutor(CombinerManager combinerManager) {
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
        
        if(combinerManager.getCombiner(player.getName(), args[0]) != null) {
            player.sendMessage(PlayerMessage.combinerExists(args[0]));
            return true;
        }
                
        Block block = player.getTargetBlock(null, 6);
        if(!(block.getType() == Material.SIGN_POST || block.getType() == Material.WALL_SIGN)) {
            player.sendMessage(PlayerMessage.invalidBlock());
            return true;
        }
        
        Combiner combiner = new Combiner(player.getName(), args[0], block);
        combinerManager.addCombiner(combiner);
        FileManager.save(combinerManager);
        player.sendMessage(PlayerMessage.combinerCreated(args[0]));
        
        return true;
    }
    
}
