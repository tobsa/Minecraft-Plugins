package soundblocks;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoundBlockExecutor implements CommandExecutor {
    private SoundBlockManager soundBlockManager;
    
    public SoundBlockExecutor(SoundBlockManager soundBlockManager) {
        this.soundBlockManager = soundBlockManager;
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
        
        Block block = player.getTargetBlock(null, 6);
        SoundBlock soundBlock = soundBlockManager.getSoundBlock(block);
        
        if(soundBlock == null) {
            try {
                soundBlock = new SoundBlock(block, Sound.valueOf(args[0]));
                soundBlockManager.addSoundBlock(soundBlock);
                player.sendMessage(PlayerMessage.soundBlockCreated(soundBlock));
            } catch(IllegalArgumentException ex) {
                player.sendMessage(PlayerMessage.invalidSound(args[0]));
            }
        }        
        else
            player.sendMessage(PlayerMessage.soundBlockExist(soundBlock));
        
        return true;
    }
}
