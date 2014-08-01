package telepads;

import org.bukkit.ChatColor;

public class PlayerMessage {
    private static final ChatColor HEADER = ChatColor.GREEN;
    private static final ChatColor NORMAL = ChatColor.WHITE;
    private static final ChatColor ERROR = ChatColor.RED;
    private static final ChatColor HIGHLIGHT = ChatColor.GREEN;
    
    public static String getFromBlockDestroyed(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' from-block was removed!";
    }

    public static String getToBlockDestroyed(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' to-block was removed!";
    }

    public static String getInvalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }

    public static String getMissingTelepad(String name) {
        return ERROR + "Telepad '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }

    public static String getTelepadDestroyed(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' was removed!";
    }

    public static String getTelepadExist(String name) {
        return ERROR + "Telepad '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }

    public static String getTelepadCreated(String name) {
        return NORMAL + "A telepad '" + HIGHLIGHT + name + NORMAL + "' was created!";
    }

    public static String getTelepadFromUpdated(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' from-block was updated!";
    }
    
    public static String getTelepadToUpdated(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' to-block was updated!";
    }

    public static String getListHeader() {
        return HIGHLIGHT + "========== Telepads ==========";
    }
    
    
    
}
