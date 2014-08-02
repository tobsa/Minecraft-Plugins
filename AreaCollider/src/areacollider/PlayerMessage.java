package areacollider;

import org.bukkit.ChatColor;

public class PlayerMessage {
    private static final ChatColor HEADER = ChatColor.GREEN;
    private static final ChatColor NORMAL = ChatColor.WHITE;
    private static final ChatColor ERROR = ChatColor.RED;
    private static final ChatColor HIGHLIGHT = ChatColor.GREEN;
    
    public static String getMissingRegionSelection() {
        return ERROR + "Make a region selection first!";
    }
    
    public static String getInvalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }
    
    public static String getAreaExists(String name) {
        return ERROR + "Area '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }
    
    public static String getMissingArea(String name) {
        return ERROR + "Area '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }
    
    public static String getAreaCreated(String name) {
        return NORMAL + "Area '" + HIGHLIGHT + name + NORMAL + "' was created!";
    }
    
    public static String getInvalidSound(String name) {
        return ERROR + "Sound '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }
    
    public static String getSecretAreaMessage() {
        return "Secret area found!";
    }

    public static String getListHeader() {
        return HEADER + "========== Area List ==========";
    }
    
    public static String getAreaDeleted(String name) {
        return NORMAL + "Area '" + HIGHLIGHT + name + NORMAL + "' was deleted!";
    }
    
    public static String getInventoryCleared() {
        return NORMAL + "Your inventory has been cleared!";
    }

    public static String getAreaRenamed(String originalName, String newName) {
        return NORMAL + "Area '" + HIGHLIGHT + originalName + NORMAL + "' has been renamed to '" + HIGHLIGHT + newName + NORMAL + "' ";
    }

    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }
}
