package puzzlepack.executors;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;
import org.bukkit.block.Block;
import puzzlepack.PuzzlePack;

public class TeleportRoomExecutor implements CommandExecutor {
    private PuzzlePack plugin;
    private WorldEditPlugin we;

    public TeleportRoomExecutor(PuzzlePack plugin) {
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
        
        if(args.length != 1) {
            player.sendMessage(ChatColor.RED + "Invalid arguments. Usage: /teleportroom <AreaName>");
            return true;
        }
        
        Block block1 = selection.getMinimumPoint().getBlock();
        Block block2 = selection.getMaximumPoint().getBlock();
        
        plugin.getAreaManager().addTeleportRoom(player.getPlayerListName(), args[0], block1, block2, player.getLocation(), player.getLocation().getPitch(), player.getLocation().getYaw());

        player.sendMessage(ChatColor.LIGHT_PURPLE + "Teleport room '" + args[0] + "' was created.");

        return true;
    }

}
