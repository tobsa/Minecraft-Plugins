package bounceblocks;

import puzzlepack.BaseMessage;


public class PlayerMessage extends BaseMessage {
        
    public static String woolCheck() {
        return ERROR + "You must look at a white wool block!";
    }
    
    public static String bounceBlockCreated(double jumpStrength) {
        return NORMAL + "A bounce block with jump strength '" + HIGHLIGHT + jumpStrength + NORMAL + "' was created";
    }
    
    public static String bounceBlockUpdated(double jumpStrength) {
        return NORMAL + "A bounce block was updated with jump strength '" + HIGHLIGHT + jumpStrength + NORMAL + "'";
    }
    
    public static String numberCheck(String arg) {
        return ERROR + "Argument '" + HIGHLIGHT + arg + ERROR + "' must be a number!";
    }
    
    public static String bounceBlockDestroyed() {
        return NORMAL + "A bounce block was destroyed!";
    }
    
    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }
}
