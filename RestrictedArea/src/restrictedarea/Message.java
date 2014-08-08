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

    public static String displayArea(int count, String name, int size) {
        return NORMAL + "" + count + ". " + name + " (" + HIGHLIGHT + size + NORMAL + (size == 1 ? " area)" : " areas)");
    }

    public static String invalidNumber(String number) {
        return ERROR + "" + HIGHLIGHT + number + ERROR + " must be a number!";
    }

    public static String invalidIndex(String number, int size) {
        return ERROR + "Index " + HIGHLIGHT + number + ERROR + " is invalid! Must be between " + HIGHLIGHT + "1" + ERROR + " and " + HIGHLIGHT + size + ERROR + "!"; 
    }

    public static String subareaRemoved(Integer index, String name) {
        return NORMAL + "Subarea " + index + NORMAL + " in area '" + HIGHLIGHT + name + NORMAL + "' has been removed!";
    }

    public static String indexUpdated(String name, Integer index) {
        return NORMAL + "Area '" + HIGHLIGHT + name + NORMAL + "' has been updated with a new index " + HIGHLIGHT + index + NORMAL + "!"; 
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

    public static String areaExistsInGroup(String area, String group) {
        return ERROR + "Area '" + HIGHLIGHT + area + ERROR + "' already exists in group '" + HIGHLIGHT + group + ERROR + "'!";
    }

    public static String areaPlacedInGroup(String area, String group) {
        return NORMAL + "Area '" + HIGHLIGHT + area + NORMAL + "' was added to group '" + HIGHLIGHT + group + NORMAL + "'!";
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

    public static String areaMissingInGroup(String area, String group) {
        return ERROR + "Area '" + HIGHLIGHT + area + ERROR + "' doesn't  exist in group '" + HIGHLIGHT + group + ERROR + "'!";
    }

    public static String areaRemovedFromGroup(String area, String group) {
        return NORMAL + "Area '" + HIGHLIGHT + area + NORMAL + "' was removed from group '" + HIGHLIGHT + group + NORMAL + "'!";
    }
    
}
