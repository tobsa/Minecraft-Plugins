package catapultblocks;

import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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
        
        if(args.length != 4) {
            player.sendMessage(PlayerMessage.invalidArguments(command.getUsage()));
            return true;
        }
        
        Direction direction = getDirection(args[0]);
        if(direction == null) {
            player.sendMessage(PlayerMessage.invalidDirection(args[0]));
            return true;
        }
        
        Double velocityX = getDouble(args[1]);
        if(velocityX == null) {
            player.sendMessage(PlayerMessage.invalidNumber(args[1]));
            return true;
        }
        
        Double velocityY = getDouble(args[2]);
        if(velocityY == null) {
            player.sendMessage(PlayerMessage.invalidNumber(args[2]));
            return true;
        }
        
        Double velocityZ = getDouble(args[3]);
        if(velocityZ == null) {
            player.sendMessage(PlayerMessage.invalidNumber(args[3]));
            return true;
        }
        
        Block block = player.getTargetBlock(null, 6);
        CatapultBlock catapultBlock = catapultManager.getCatapultBlock(block);
        
        Vector velocity = new Vector(velocityX, velocityY, velocityZ);
        if(catapultBlock == null) {
            CatapultBlock newCatapultBlock = new CatapultBlock(block, direction, velocity);
            catapultManager.addCatapultBlock(newCatapultBlock);
            FileManager.saveCatapultBlock(newCatapultBlock);
            player.sendMessage(PlayerMessage.catapultBlockCreated(direction, velocity));
        } else {
            catapultBlock.setDirection(direction);
            catapultBlock.setVelocity(velocity);
            FileManager.saveCatapultBlock(catapultBlock);
            player.sendMessage(PlayerMessage.catapultBlockUpdated(direction, velocity));
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
