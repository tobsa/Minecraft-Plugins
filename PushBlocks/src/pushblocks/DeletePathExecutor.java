package pushblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeletePathExecutor implements CommandExecutor {
    private PathManager pathManager;
    
    public DeletePathExecutor(PathManager pathManager) {
        this.pathManager = pathManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {        
        if (!(sender instanceof Player))
            return true;

        Player player = (Player)sender;
        
        if(args.length != 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
                
        Path path = pathManager.getPath(player.getTargetBlock(null, 20));
        if(path == null) {
            player.sendMessage(PlayerMessage.missingPath());
            return true;
        }
        
        pathManager.removePath(path);
        FileManager.save(pathManager);
        player.sendMessage(PlayerMessage.pathRemoved(path));
        
        return true;
    }
}
