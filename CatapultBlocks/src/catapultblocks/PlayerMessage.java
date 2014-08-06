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
                                             + HIGHLIGHT + velocity.getX() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getY() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getZ() + NORMAL + ") has been created!";
    }
    
    public static String catapultBlockUpdated(Direction direction, Vector velocity) {
        return NORMAL + "A catapult block (" + HIGHLIGHT + direction.toString() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getX() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getY() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getZ() + NORMAL + ") has been updated!";
    }

    public static String blockRemoved(Direction direction, Vector velocity) {
        return NORMAL + "A catapult block (" + HIGHLIGHT + direction.toString() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getX() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getY() + NORMAL + ", " 
                                             + HIGHLIGHT + velocity.getZ() + NORMAL + ") has been removed!";
    }
}
