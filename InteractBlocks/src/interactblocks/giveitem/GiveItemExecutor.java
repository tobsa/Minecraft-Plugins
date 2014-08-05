package interactblocks.giveitem;

import interactblocks.FileManager;
import interactblocks.InteractBlock;
import interactblocks.InteractBlockManager;
import interactblocks.PlayerMessage;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveItemExecutor implements CommandExecutor {
    private InteractBlockManager blockManager;
    
    public GiveItemExecutor(InteractBlockManager blockManager) {
        this.blockManager = blockManager;
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
        
        Material material = getMaterial(args[0]);
        if(material == null) {
            player.sendMessage(PlayerMessage.invalidMaterial(args[0]));
            return true;
        }
        
        Integer amount = getInteger(args[1]);
        if(amount == null) {
            player.sendMessage(PlayerMessage.invalidNumber1(args[1]));
            return true;
        }
        
        GiveItemResponse response = new GiveItemResponse(material, amount);
        InteractBlock interactBlock = new InteractBlock(player.getTargetBlock(null, 6), response);

        blockManager.addInteractBlock(interactBlock);
        FileManager.save(blockManager);
        player.sendMessage(PlayerMessage.giveItemCreated(interactBlock, response));
        
        return true;
    }
    
    private Material getMaterial(String material) {
        try {
            return Material.valueOf(material);
        } catch(IllegalArgumentException ex) {
            return null;
        }
    }
    
    public Integer getInteger(String number) {
        try {
            return Integer.valueOf(number);
        } catch(NumberFormatException ex) {
            return null;
        }
    }
}
