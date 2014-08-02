package portals;

import org.bukkit.ChatColor;

public class PlayerMessage {
    private static final ChatColor HEADER = ChatColor.GREEN;
    private static final ChatColor NORMAL = ChatColor.WHITE;
    private static final ChatColor ERROR = ChatColor.RED;
    private static final ChatColor HIGHLIGHT = ChatColor.GREEN;
    
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
}
