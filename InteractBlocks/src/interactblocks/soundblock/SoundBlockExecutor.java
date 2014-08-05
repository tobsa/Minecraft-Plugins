package interactblocks.soundblock;

import interactblocks.FileManager;
import interactblocks.InteractBlock;
import interactblocks.InteractBlockManager;
import interactblocks.PlayerMessage;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SoundBlockExecutor implements CommandExecutor {
    private InteractBlockManager blockManager;
    
    public SoundBlockExecutor(InteractBlockManager blockManager) {
        this.blockManager = blockManager;
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
        
        Sound sound = getSound(args[0]);
        if(sound == null) {
            player.sendMessage(PlayerMessage.invalidSound(args[0]));
            return true;
        }
        
        SoundResponse soundResponse = new SoundResponse(sound);
        InteractBlock interactBlock = new InteractBlock(player.getTargetBlock(null, 6), soundResponse);

        blockManager.addInteractBlock(interactBlock);
        FileManager.save(blockManager);
        player.sendMessage(PlayerMessage.soundBlockCreated(interactBlock, soundResponse));     
        
        return true;
    }
    
    private Sound getSound(String argument) {
        try {
            return Sound.valueOf(argument);
        } catch(IllegalArgumentException ex) {
            return null;
        }
    }
}
