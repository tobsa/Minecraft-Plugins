package portals;

import org.bukkit.ChatColor;
import puzzlepack.CommandColor;

public class PlayerMessage {
    private static final ChatColor HEADER    = CommandColor.HEADER;
    private static final ChatColor NORMAL    = CommandColor.NORMAL;
    private static final ChatColor ERROR     = CommandColor.ERROR;
    private static final ChatColor HIGHLIGHT = CommandColor.HIGHLIGHT;
    
    public static String invalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }

    public static String invalidSelection() {
        return ERROR + "Make a region selection first!";
    }

    public static String portalExist(String name) {
        return ERROR + "Portal '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }

    public static String portalCreated(String name) {
        return NORMAL + "Portal '" + HIGHLIGHT + name + NORMAL + "' was created!";
    }

    public static String listHeader() {
        return HIGHLIGHT + "========== Portals ==========";
    }

    public static String missingPortal(String name) {
        return ERROR + "Portal '" + HIGHLIGHT + name + ERROR + "' doesn't exist!"; 
    }

    public static String teleportLocationUpdated(String name) {
        return NORMAL + "Portal '" + HIGHLIGHT + name + NORMAL + "' was updated with a new teleportation location!";
    }

    public static String portalRemoved(String name) {
        return NORMAL + "Portal '" + HIGHLIGHT + name + NORMAL + "' was removed!";
    }

    public static String portalRenamed(String originalName, String newName) {
        return NORMAL + "Portal '" + HIGHLIGHT + originalName + NORMAL + "' was renamed to '" + HIGHLIGHT + newName + NORMAL + "'!";
    }
    
    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }
}
