package warper;

import puzzlepack.BaseMessage;

public class PlayerMessage extends BaseMessage {
    
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
