package worldeditplus;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.UnknownItemException;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.bukkit.selections.Selection;

public class CommandCornerExecutor implements CommandExecutor {

    private WorldEditPlugin we;

    public CommandCornerExecutor(WorldEditPlus plugin) {
        this.we = plugin.getWorldEdit();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // Make sure it's a player who issued a command
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        // Make sure we have the correct amount of arguments
        if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("-c"))) {
            player.sendMessage(ChatColor.RED + "To few arguments. Use: //corners [-c] <material>");
            return true;
        }

        // Create an edit session (used for WorldEdit undo/redo)
        EditSession editSession = we.createEditSession(player);
        Selection selection = we.getSelection(player);

        // Make sure we have a valid selection
        if (selection == null) {
            player.sendMessage(ChatColor.RED + "Make a region selection first.");
            return true;
        }

        int minX = selection.getMinimumPoint().getBlockX();
        int maxX = selection.getMaximumPoint().getBlockX();
        int minY = selection.getMinimumPoint().getBlockY();
        int maxY = selection.getMaximumPoint().getBlockY();
        int minZ = selection.getMinimumPoint().getBlockZ();
        int maxZ = selection.getMaximumPoint().getBlockZ();

        try {
            // Create only the corners
            if (args[0].equalsIgnoreCase("-c")) {
                editSession.setBlock(new Vector(minX, minY, minZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                editSession.setBlock(new Vector(maxX, minY, minZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                editSession.setBlock(new Vector(minX, minY, maxZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                editSession.setBlock(new Vector(maxX, minY, maxZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));

                editSession.setBlock(new Vector(minX, maxY, minZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                editSession.setBlock(new Vector(maxX, maxY, minZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                editSession.setBlock(new Vector(minX, maxY, maxZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                editSession.setBlock(new Vector(maxX, maxY, maxZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
            } // Create the corner pillars
            else {
                for (int y = minY; y <= maxY; y++) {
                    editSession.setBlock(new Vector(minX, y, minZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[0]));
                    editSession.setBlock(new Vector(maxX, y, minZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[0]));
                    editSession.setBlock(new Vector(minX, y, maxZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[0]));
                    editSession.setBlock(new Vector(maxX, y, maxZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[0]));
                }
            }
        } catch (UnknownItemException e) {
            if (args[0].equalsIgnoreCase("-c")) {
                player.sendMessage(ChatColor.RED + "Block name '" + args[1] + "' was not recognized.");
            } else {
                player.sendMessage(ChatColor.RED + "Block name '" + args[0] + "' was not recognized.");
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            we.remember(player, editSession);
        }

        player.sendMessage(ChatColor.LIGHT_PURPLE + "Corners created.");

        return true;
    }

}
