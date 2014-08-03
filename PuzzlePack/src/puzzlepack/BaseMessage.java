package puzzlepack;

import org.bukkit.ChatColor;

public class BaseMessage {
    public static final ChatColor HEADER    = ChatColor.GREEN;
    public static final ChatColor NORMAL    = ChatColor.WHITE;
    public static final ChatColor ERROR     = ChatColor.RED;
    public static final ChatColor HIGHLIGHT = ChatColor.GREEN;
    
    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }
    
    public static String invalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }
}
