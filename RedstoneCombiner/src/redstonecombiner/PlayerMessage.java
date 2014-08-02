package redstonecombiner;

import org.bukkit.ChatColor;

public class PlayerMessage {
    private static final ChatColor HEADER = ChatColor.GREEN;
    private static final ChatColor NORMAL = ChatColor.WHITE;
    private static final ChatColor ERROR = ChatColor.RED;
    private static final ChatColor HIGHLIGHT = ChatColor.GREEN;
        
    public static String getInvalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }

    public static String getCombinerExists(String name) {
        return ERROR + "Redstone combiner '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }

    public static String getCombinerCreated(String name) {
        return NORMAL + "Redstone combiner '" + HIGHLIGHT + name + NORMAL + "' was created!";
    }

    public static String getMissingCombiner(String name) {
        return ERROR + "Redstone combiner '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }

    public static String getCombinerAddLink(String name) {
        return NORMAL + "A link was added to combiner '" + HIGHLIGHT + name + NORMAL + "'!";
    }

    public static String getCombinerRemoved(String name) {
        return NORMAL + "Combiner '" + HIGHLIGHT + name + NORMAL + "' and all its links was removed!";
    }

    public static String getLinkRemoved(String name) {
        return NORMAL + "A link has been removed from combiner '" + HIGHLIGHT + name + NORMAL + "'!";
    }

    public static String getMissingLink(String name) {
        return ERROR + "A link for combiner '" + HIGHLIGHT + name + ERROR + "' doesn't exist at this block!";
    }

    public static String getHeaderList() {
        return HIGHLIGHT + "========== Redstone Combiners ==========";
    }

    public static String getInvalidBlock() {
        return ERROR + "You must look at a sign when creating a combiner or link!";
    }

    public static String getCombinerRenamed(String originalName, String newName) {
        return NORMAL + "Redstone combiner '" + HIGHLIGHT + originalName + NORMAL + "' was renamed to '" + HIGHLIGHT + newName + NORMAL + "'!"; 
    }
    
    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }
}
