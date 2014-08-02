package toggleblocks;

import puzzlepack.CommandColor;

public class PlayerMessage extends CommandColor {
        
    public static String invalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }
        
    public static String blockPlaced(String name) {
        return NORMAL + "A block has been added to region '" + HIGHLIGHT + name + NORMAL + "'!";
    }
    
    public static String regionCreated(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' has been created!";
    }
    
    public static String blockRemoved(String name) {
        return NORMAL + "A block has been removed from region '" + HIGHLIGHT + name + NORMAL + "'!";
    }
    
    public static String missingRegion(String name) {
        return ERROR + "Region '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }
    
    public static String linkSet(String name) {
        return NORMAL + "A link for region '" + HIGHLIGHT + name + NORMAL + "' has been set!";
    }
    
    public static String redstoneLinkSet(String name) {
        return NORMAL + "A redstone link for region '" + HIGHLIGHT + name + NORMAL + "' has been set!";
    }
    
    public static String editmodeYes(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' is now in edit mode!";  
    }
    
    public static String editmodeNo(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' is no longer in edit mode!";  
    }
    
    public static String listHeader() {
        return HEADER + "========== Regions ==========";
    }
    
    public static String regionRemoved(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' was removed!";
    }
    
    public static String getLinkDeleted(String name) {
        return NORMAL + "A link for region '" + HIGHLIGHT + name + NORMAL + "' was removed!";
    }

    public static String toggledOff(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' has been toggled off!";
    }

    public static String toggledOn(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' has been toggled on!";
    }
    
    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }
}
