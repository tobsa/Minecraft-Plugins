package catapultblocks;

import org.bukkit.ChatColor;

public class PlayerMessage {
    private static final ChatColor NORMAL = ChatColor.WHITE;
    private static final ChatColor ERROR = ChatColor.RED;
    private static final ChatColor HIGHLIGHT = ChatColor.GREEN;    
    
    public static String getInvalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }

    public static String getInvalidDirection(String direction) {
        return ERROR + "Direction '" + HIGHLIGHT + direction + ERROR + "' is invalid! <north|east|south|west>";
    }

    public static String getInvalidNumber(String number) {
        return ERROR + "'" + HIGHLIGHT + number + NORMAL + "' must be a number!";
    }

    public static String getCatapultBlockCreated(Direction direction, Double forwardVelocity, Double upwardVelocity) {
        return NORMAL + "A catapult block (" + HIGHLIGHT + direction.toString() + NORMAL + ", " 
                                             + HIGHLIGHT + forwardVelocity + NORMAL + ", " 
                                             + HIGHLIGHT + upwardVelocity + NORMAL + ") has been created!";
    }
    
    public static String getCatapultBlockUpdated(Direction direction, Double forwardVelocity, Double upwardVelocity) {
        return NORMAL + "A catapult block (" + HIGHLIGHT + direction.toString() + NORMAL + ", " 
                                             + HIGHLIGHT + forwardVelocity + NORMAL + ", " 
                                             + HIGHLIGHT + upwardVelocity + NORMAL + ") was updated!";
    }

    public static String getBlockRemoved(Direction direction, Double forwardVelocity, Double upwardVelocity) {
        return NORMAL + "A catapult block (" + HIGHLIGHT + direction.toString() + NORMAL + ", " 
                                             + HIGHLIGHT + forwardVelocity + NORMAL + ", " 
                                             + HIGHLIGHT + upwardVelocity + NORMAL + ") was removed!";
    }
    
    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }
    
}
