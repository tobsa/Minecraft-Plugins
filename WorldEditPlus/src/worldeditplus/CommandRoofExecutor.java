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

public class CommandRoofExecutor implements CommandExecutor {

    private WorldEditPlugin we;

    public CommandRoofExecutor(WorldEditPlus plugin) {
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
        if (args.length == 0 || (args.length == 1 && args[0].equalsIgnoreCase("-w"))) {
            player.sendMessage(ChatColor.RED + "To few arguments. Use: //roof [-w] <material>");
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
        int minZ = selection.getMinimumPoint().getBlockZ();
        int maxZ = selection.getMaximumPoint().getBlockZ();

        try {
            int y = selection.getMaximumPoint().getBlockY();

            // Create a wall at the top
            if (args[0].equalsIgnoreCase("-w")) {
                for (int x = minX; x <= maxX; x++) {
                    editSession.setBlock(new Vector(x, y, minZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                    editSession.setBlock(new Vector(x, y, maxZ), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                }

                for (int z = minZ; z <= maxZ; z++) {
                    editSession.setBlock(new Vector(minX, y, z), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                    editSession.setBlock(new Vector(maxX, y, z), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[1]));
                }
            } // Create the roof
            else {
                for (int x = minX; x <= maxX; x++) {
                    for (int z = minZ; z <= maxZ; z++) {
                        editSession.setBlock(new Vector(x, y, z), we.getWorldEdit().getBlock(we.wrapPlayer(player), args[0]));
                    }
                }
            }
        } catch (UnknownItemException e) {
            if (args[0].equalsIgnoreCase("-w")) {
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

        player.sendMessage(ChatColor.LIGHT_PURPLE + "Roof created.");

        return true;
    }

}
