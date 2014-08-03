package telepads;

import puzzlepack.BaseMessage;

public class PlayerMessage extends BaseMessage {
    
    public static String fromBlockDestroyed(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' from-block was removed!";
    }

    public static String toBlockDestroyed(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' to-block was removed!";
    }

    public static String getMissingTelepad(String name) {
        return ERROR + "Telepad '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }

    public static String telepadDestroyed(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' was removed!";
    }

    public static String telepadExist(String name) {
        return ERROR + "Telepad '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }

    public static String telepadCreated(String name) {
        return NORMAL + "A telepad '" + HIGHLIGHT + name + NORMAL + "' was created!";
    }

    public static String telepadFromUpdated(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' from-block was updated!";
    }
    
    public static String telepadToUpdated(String name) {
        return NORMAL + "Telepad '" + HIGHLIGHT + name + NORMAL + "' to-block was updated!";
    }

    public static String listHeader() {
        return HIGHLIGHT + "========== Telepads ==========";
    }
}
