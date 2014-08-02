package bounceblocks;

import org.bukkit.ChatColor;

public class PlayerMessage {
    private static final ChatColor NORMAL = ChatColor.WHITE;
    private static final ChatColor ERROR = ChatColor.RED;
    private static final ChatColor HIGHLIGHT = ChatColor.GREEN;
        
    public static String getInvalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }

    public static String getWoolCheck() {
        return ERROR + "You must look at a white wool block!";
    }
    
    public static String getBounceBlockCreated(double jumpStrength) {
        return NORMAL + "A bounce block with jump strength '" + HIGHLIGHT + jumpStrength + NORMAL + "' was created";
    }
    
    public static String getBounceBlockUpdated(double jumpStrength) {
        return NORMAL + "A bounce block was updated with jump strength '" + HIGHLIGHT + jumpStrength + NORMAL + "'";
    }
    
    public static String getNumberCheck(String arg) {
        return ERROR + "Argument '" + HIGHLIGHT + arg + ERROR + "' must be a number!";
    }
    
    public static String getBounceBlockDestroyed() {
        return NORMAL + "A bounce block was destroyed!";
    }
    
    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }
}
