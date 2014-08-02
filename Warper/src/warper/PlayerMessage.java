package warper;

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

    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }

    public static String positionSaved(String name) {
        return NORMAL + "Position '" + HIGHLIGHT + name + NORMAL + "' is saved!";
    }

    public static String invalidPosition(String name) {
        return ERROR + "Position '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }

    public static String positionDeleted(String name) {
        return NORMAL + "Position '" + HIGHLIGHT + name + NORMAL + "' has been removed!";
    }

    public static String listHeader() {
        return HIGHLIGHT + "========== Positions ==========";
    }
}
