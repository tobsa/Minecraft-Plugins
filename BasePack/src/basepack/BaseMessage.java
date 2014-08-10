package basepack;

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
    
    public static String groupIndexUpdated(String name, Integer index) {
        return NORMAL + "Group '" + HIGHLIGHT + name + NORMAL + "' has been updated with a new index " + HIGHLIGHT + index + NORMAL + "!"; 
    }

    public static String missingGroup(String name) {
        return ERROR + "Group '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }

    public static String groupCreated(String name) {
        return NORMAL + "Group '" + HIGHLIGHT + name + NORMAL + "' has been created!";
    }

    public static String grouplistHeader() {
        return HIGHLIGHT + "========== Groups ==========";
    }

    public static String groupExists(String name) {
        return ERROR + "Group '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }
    
     public static String groupnameHeader(String name) {
        return HIGHLIGHT + "========== " + NORMAL + name + HIGHLIGHT + " ==========";
    }

    public static String groupitemList(int count, String name, int size) {
        return NORMAL + "" + count + ". " + name + " (" + HIGHLIGHT + size + NORMAL + (size == 1 ? " area)" : " areas)");
    }

    public static String groupRemoved(String name) {
        return NORMAL + "Group '" + HIGHLIGHT + name + NORMAL + "' was removed!";
    }

    public static String groupRenamed(String group, String newName) {
        return NORMAL + "Group '" + HIGHLIGHT + group + NORMAL + "' has been renamed to '" + HIGHLIGHT + newName + NORMAL + "'!";
    }
    
    public static String invalidIndex(String number, int size) {
        return ERROR + "Index " + HIGHLIGHT + number + ERROR + " is invalid! Must be between " + HIGHLIGHT + "1" + ERROR + " and " + HIGHLIGHT + size + ERROR + "!"; 
    }
    
    public static String invalidNumber(String number) {
        return ERROR + "" + HIGHLIGHT + number + ERROR + " must be a number!";
    }
}
