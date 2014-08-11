package forestertool;

import basepack.BaseMessage;

public class Message extends BaseMessage {

    public static String radius(Integer radius) {
        return NORMAL + "Tool radius: " + HIGHLIGHT + radius + NORMAL + " blocks!"; 
    }

    public static String air(Integer air) {
        return NORMAL + "Tool air: " + HIGHLIGHT + air + NORMAL + "%"; 
    }
    
    public static String leaves(Integer air) {
        return NORMAL + "Tool leaves: " + HIGHLIGHT + air + NORMAL + "%"; 
    }

    public static String replace(boolean value) {
        return NORMAL + "Tool replace: " + HIGHLIGHT + value + NORMAL + "";
    }

    public static String distance(boolean value) {
        return NORMAL + "Tool distance: " + HIGHLIGHT + value + NORMAL + "";
    }
        
}
