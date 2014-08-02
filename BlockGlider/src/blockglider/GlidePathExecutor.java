package blockglider;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlidePathExecutor implements CommandExecutor {
    private BlockGlider plugin;
    private WorldEditPlugin we;
    
    public GlidePathExecutor(BlockGlider plugin) {
        this.plugin = plugin;
        we = plugin.getWorldEdit();
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return true;
        
        Player player = (Player)sender;
        
        Selection selection = we.getSelection(player);
        if (selection == null) {
            player.sendMessage(BlockGlider.CHAT_ERROR + "Make a region selection first.");
            return true;
        }
        
        int numBigSelections = 0;
        if(selection.getWidth() > 1)
            numBigSelections++;
        if(selection.getHeight() > 1)
            numBigSelections++;
        if(selection.getLength() > 1)
            numBigSelections++;
        
        if(numBigSelections != 1) {
            player.sendMessage(BlockGlider.CHAT_ERROR + "Invalid selection! Only one side can be longer than 1");
            return true;
        }
        
        if(args.length != 2) {
            player.sendMessage(BlockGlider.CHAT_ERROR + "Invalid arguments! Usage: /glidepath <velocity> <north|east|south|west|up|down>");
            return true;
        }
        
        GlidePath path = new GlidePath();
        path.setMaxBlock(selection.getMaximumPoint().getBlock());
        path.setMinBlock(selection.getMinimumPoint().getBlock());
        
        try {
            path.setVelocity(Double.valueOf(args[0]));
            
            boolean found = false;
            for(Direction direction : Direction.values()) {
                if(direction.toString().equalsIgnoreCase(args[1])) {
                    found = true;
                    path.setDirection(direction);
                }
            }
            
            if(!found) {
                player.sendMessage(BlockGlider.CHAT_HIGHLIGHT + args[1] + BlockGlider.CHAT_ERROR + " is not a valid direction!");
                return true;
            }
            
            plugin.path1 = path;
            player.sendMessage("A glidepath has been created!");
            
        } catch(NumberFormatException ex) {
            player.sendMessage(BlockGlider.CHAT_HIGHLIGHT + args[0] + BlockGlider.CHAT_ERROR + " must be a number!");
            return true;
        }

        return true;
    }
    
}
