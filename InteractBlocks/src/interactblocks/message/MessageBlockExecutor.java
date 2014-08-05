package interactblocks.message;

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

public class MessageBlockExecutor implements CommandExecutor {
    private InteractBlockManager blockManager;
    
    public MessageBlockExecutor(InteractBlockManager blockManager) {
        this.blockManager = blockManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
                
        if(args.length == 0) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        
        MessageResponse response = new MessageResponse(combineArguments(args, 0));
        InteractBlock interactBlock = new InteractBlock(player.getTargetBlock(null, 6), response);

        blockManager.addInteractBlock(interactBlock);
        FileManager.save(blockManager);
        player.sendMessage(PlayerMessage.messageBlockCreated(interactBlock, response));
        
        return true;
    }
    
    private String combineArguments(String[] args, int arg) {
        String argument = "";
        for(int i = arg; i < args.length; i++)
            argument += args[i] + " ";
        
        return argument;        
    }
}
