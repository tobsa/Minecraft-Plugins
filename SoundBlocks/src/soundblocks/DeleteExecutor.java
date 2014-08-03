package soundblocks;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteExecutor implements CommandExecutor {
    private SoundBlockManager soundBlockManager;
    
    public DeleteExecutor(SoundBlockManager soundBlockManager) {
        this.soundBlockManager = soundBlockManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        if(args.length != 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        SoundBlock soundBlock = soundBlockManager.getSoundBlock(player.getTargetBlock(null, 6));
        if(soundBlockManager.removeSoundBlock(soundBlock)) {
            player.sendMessage(PlayerMessage.soundBlockRemoved(soundBlock));
            FileManager.save(soundBlockManager);
        }
        else
            player.sendMessage(PlayerMessage.missingSoundBlock());
        
        return true;
    }
}
