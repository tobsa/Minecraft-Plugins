package toggleblocks;

import org.bukkit.ChatColor;

public class PlayerMessage {
    private static final ChatColor HEADER = ChatColor.GREEN;
    private static final ChatColor NORMAL = ChatColor.WHITE;
    private static final ChatColor ERROR = ChatColor.RED;
    private static final ChatColor HIGHLIGHT = ChatColor.GREEN;
        
    public static String getInvalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }
    
    public static String getRegionExists(String name) {
        return ERROR + "Region '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }
    
    public static String getBlockPlaced(String name) {
        return NORMAL + "A block has been added to region '" + HIGHLIGHT + name + NORMAL + "'!";
    }
    
    public static String getRegionCreated(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' has been created!";
    }
    
    public static String getBlockRemoved(String name) {
        return NORMAL + "A block has been removed from region '" + HIGHLIGHT + name + NORMAL + "'!";
    }
    
    public static String getMissingRegion(String name) {
        return ERROR + "Region '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }
    
    public static String getLinkSet(String name) {
        return NORMAL + "A link for region '" + HIGHLIGHT + name + NORMAL + "' has been set!";
    }
    
    public static String getRLinkSet(String name) {
        return NORMAL + "A redstone link for region '" + HIGHLIGHT + name + NORMAL + "' has been set!";
    }
    
    public static String getYesEditMode(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' is now in edit mode!";  
    }
    
    public static String getNoEditMode(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' is no longer in edit mode!";  
    }
    
    public static String getListHeader() {
        return HEADER + "========== Regions ==========";
    }
    
    public static String getRegionDeleted(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' was deleted!";
    }
    
    public static String getLinkDeleted(String name) {
        return NORMAL + "A link for region '" + HIGHLIGHT + name + NORMAL + "' was deleted!";
    }
}
