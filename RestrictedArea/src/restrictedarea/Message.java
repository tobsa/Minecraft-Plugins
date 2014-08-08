package restrictedarea;

import puzzlepack.BaseMessage;

public class Message extends BaseMessage {

    public static String areaExists(String name) {
        return ERROR + "Area '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }

    public static String areaCreated(String name) {
        return NORMAL + "Area '" + HIGHLIGHT + name + NORMAL + "' has been created!";
    }

    public static String missingArea(String name) {
        return ERROR + "Area '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }

    public static String subareaCreated(String name, int blocks) {
        return NORMAL + "An amount of " + HIGHLIGHT + blocks + NORMAL + " blocks has been added to area '" + HIGHLIGHT + name + NORMAL + "'!"; 
    }

    public static String areaRemoved(String name) {
        return NORMAL + "Area '" + HIGHLIGHT + name + NORMAL + "' has been removed!";
    }

    public static String listHeader() {
        return HIGHLIGHT + "========== Restricted Areas ==========";
    }

    public static String areaLocationUpdated(String name) {
        return NORMAL + "Location for area '" + HIGHLIGHT + name + NORMAL + "' has been updated!";
    }

    public static String areaRenamed(String originalName, String newName) {
        return NORMAL + "Area '" + HIGHLIGHT + originalName + NORMAL + "' has been renamed to '" + HIGHLIGHT + newName + NORMAL + "'!"; 
    }

    public static String displayArea(String name, int size) {
        return NORMAL + name + " (" + HIGHLIGHT + size + NORMAL + (size == 1 ? " area)" : " areas)");
    }

    public static String invalidNumber(String number) {
        return ERROR + "" + HIGHLIGHT + number + ERROR + " must be a number!";
    }

    public static String invalidIndex(String number, int size) {
        return HIGHLIGHT + number + ERROR + " is an invalid index! Must be between " + HIGHLIGHT + "0" + ERROR + " and " + HIGHLIGHT + size + ERROR + "!"; 
    }

    public static String subareaRemoved(Integer index, String name) {
        return NORMAL + "Subarea " + index + NORMAL + " in area '" + HIGHLIGHT + name + NORMAL + "' has been removed!";
    }
    
}
