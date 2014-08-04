package bounceblocks;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;

public class BounceBlockExecutor implements CommandExecutor {
    private BounceBlockManager bounceBlockManager;

    public BounceBlockExecutor(BounceBlockManager bounceBlockManager) {
        this.bounceBlockManager = bounceBlockManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;
        
        if(args.length != 1) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        try {   
            Block block = player.getTargetBlock(null, 6);
            double jumpStrength = Double.valueOf(args[0]);
            
            BounceBlock bounceBlock = bounceBlockManager.getBounceBlock(block);
            if(bounceBlock == null) {

                if(block.getType() != Material.WOOL || new Wool(block.getType(), block.getData()).getColor() != DyeColor.WHITE) {
                    player.sendMessage(PlayerMessage.woolCheck());
                    return true;
                }

                bounceBlockManager.addBounceBlock(new BounceBlock(block, jumpStrength));
                player.sendMessage(PlayerMessage.bounceBlockCreated(jumpStrength));
                FileManager.save(bounceBlockManager);
            } else {
                bounceBlockManager.setJumpStrength(bounceBlock, jumpStrength);
                player.sendMessage(PlayerMessage.bounceBlockUpdated(jumpStrength));
                FileManager.save(bounceBlockManager);
            }
        } catch(NumberFormatException ex) {
            player.sendMessage(PlayerMessage.numberCheck(args[0]));
            return true;
        }
        
        return true;
    }

}
