package areacollider;

import puzzlepack.BaseMessage;

public class PlayerMessage extends BaseMessage {    
    
    public static String missingRegionSelection() {
        return ERROR + "Make a region selection first!";
    }
        
    public static String areaExists(String name) {
        return ERROR + "Area '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }
    
    public static String missingArea(String name) {
        return ERROR + "Area '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }
    
    public static String areaCreated(String name) {
        return NORMAL + "Area '" + HIGHLIGHT + name + NORMAL + "' was created!";
    }
    
    public static String invalidSound(String name) {
        return ERROR + "Sound '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }
    
    public static String secretAreaMessage() {
        return "Secret area found!";
    }

    public static String listHeader() {
        return HEADER + "========== Area List ==========";
    }
    
    public static String areaDeleted(String name) {
        return NORMAL + "Area '" + HIGHLIGHT + name + NORMAL + "' was deleted!";
    }
    
    public static String inventoryCleared() {
        return NORMAL + "Your inventory has been cleared!";
    }

    public static String areaRenamed(String originalName, String newName) {
        return NORMAL + "Area '" + HIGHLIGHT + originalName + NORMAL + "' has been renamed to '" + HIGHLIGHT + newName + NORMAL + "' ";
    }
}
