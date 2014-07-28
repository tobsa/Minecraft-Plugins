package area.clearinventoryarea;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.block.Block;
import puzzlepack.PuzzlePack;

public class ClearInventoryAreaExecutor implements CommandExecutor {
    private PuzzlePack plugin;
    private WorldEditPlugin we;

    public ClearInventoryAreaExecutor(PuzzlePack plugin) {
        this.plugin = plugin;
        we = plugin.getWorldEdit();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player))
            return true;

        Player player = (Player) sender;

        Selection selection = we.getSelection(player);

        // Make sure we have a valid selection
        if (selection == null) {
            player.sendMessage(ChatColor.RED + "Make a region selection first.");
            return true;
        }
        
        if(args.length == 0) {
            player.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /clearinvroom <AreaName> [Message]");
            return true;
        }
                
        if(plugin.getAreaManager().getClearInventoryArea(player.getPlayerListName(), args[0]) != null) {
            player.sendMessage(ChatColor.RED + "'" + args[0] + "' already exists!");
            return true;
        }
        
        Block block1 = selection.getMinimumPoint().getBlock();
        Block block2 = selection.getMaximumPoint().getBlock();
                
        if(args.length == 1)        
            plugin.getAreaManager().addClearInventoryArea(player.getPlayerListName(), args[0], "", block1, block2);
        else {
            String message = "";
            for(String arg : args)
                message += arg + " ";
            message = message.substring(message.indexOf(' ') + 1);
            plugin.getAreaManager().addClearInventoryArea(player.getPlayerListName(), args[0], message, block1, block2);
        }
        
        player.sendMessage(ChatColor.LIGHT_PURPLE + "Clear inventory room '" + args[0] + "' was created.");

        return true;
    }

}
