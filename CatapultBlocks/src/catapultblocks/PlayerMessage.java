package catapultblocks;

import org.bukkit.util.Vector;
import puzzlepack.BaseMessage;

public class PlayerMessage extends BaseMessage {
    
    public static String invalidDirection(String direction) {
        return ERROR + "Direction '" + HIGHLIGHT + direction + ERROR + "' is invalid! <north|east|south|west>";
    }

    public static String invalidNumber(String number) {
        return ERROR + "'" + HIGHLIGHT + number + NORMAL + "' must be a number!";
    }

    public static String catapultBlockCreated(Direction direction, Vector velocity) {
        return NORMAL + "A catapult block (" + HIGHLIGHT + direction.toString() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getBlockX() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getBlockY() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getBlockZ() + NORMAL + ") has been created!";
    }
    
    public static String catapultBlockUpdated(Direction direction, Vector velocity) {
        return NORMAL + "A catapult block (" + HIGHLIGHT + direction.toString() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getBlockX() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getBlockY() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getBlockZ() + NORMAL + ") has been updated!";
    }

    public static String blockRemoved(Direction direction, Vector velocity) {
        return NORMAL + "A catapult block (" + HIGHLIGHT + direction.toString() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getBlockX() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getBlockY() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getBlockZ() + NORMAL + ") has been removed!";
    }
}
