package puzzlepack;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;

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
    
    public static String missingSelection() {
        return ERROR + "Make a region selection first!";
    }
    
    public static String blockInfo(Block block) {
        return NORMAL + "(" + HIGHLIGHT + block.getX() + NORMAL + ", " 
                            + HIGHLIGHT + block.getY() + NORMAL + ", " 
                            + HIGHLIGHT + block.getZ() + NORMAL + ")";
    }
}
