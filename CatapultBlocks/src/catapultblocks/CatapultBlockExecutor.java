package catapultblocks;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CatapultBlockExecutor implements CommandExecutor {
    private CatapultManager catapultManager;
    
    public CatapultBlockExecutor(CatapultManager catapultManager) {
        this.catapultManager = catapultManager;
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        if(args.length != 3) {
            player.sendMessage(PlayerMessage.getInvalidArguments(command.getUsage()));
            return true;
        }
        
        Direction direction = getDirection(args[0]);
        if(direction == null) {
            player.sendMessage(PlayerMessage.getInvalidDirection(args[0]));
            return true;
        }
        
        Double forwardVelocity = getDouble(args[1]);
        if(forwardVelocity == null) {
            player.sendMessage(PlayerMessage.getInvalidNumber(args[1]));
            return true;
        }
        
        Double upwardVelocity = getDouble(args[2]);
        if(upwardVelocity == null) {
            player.sendMessage(PlayerMessage.getInvalidNumber(args[1]));
            return true;
        }
        
        Block block = player.getTargetBlock(null, 6);
        CatapultBlock catapultBlock = catapultManager.getCatapultBlock(block);
        
        if(catapultBlock == null) {
            catapultManager.addCatapultBlock(new CatapultBlock(block, direction, forwardVelocity, upwardVelocity));
            player.sendMessage(PlayerMessage.getCatapultBlockCreated(direction, forwardVelocity, upwardVelocity));
        } else {
            catapultBlock.setDirection(direction);
            catapultBlock.setForwardVelocity(forwardVelocity);
            catapultBlock.setUpwardVelocity(upwardVelocity);
            player.sendMessage(PlayerMessage.getCatapultBlockUpdated(direction, forwardVelocity, upwardVelocity));
        }
                
        return true;
    }
    
    private Direction getDirection(String direction) {
        try {        
            return Direction.valueOf(direction.toUpperCase());
        } catch(IllegalArgumentException ex) {
            return null;
        }
    }    
    
    private Double getDouble(String number) {
        try {
            return Double.valueOf(number);
        } catch(NumberFormatException ex) {
            return null;
        }
    }
}
