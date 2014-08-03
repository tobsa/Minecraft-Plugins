package pushblocks;

import puzzlepack.BaseMessage;

public class PlayerMessage extends BaseMessage {

    public static String invalidSelection() {
        return ERROR + "Invalid selection! Only one side can be longer than 1";
    }

    public static String invalidPath() {
        return ERROR + "Path is colliding with another path!";
    }

    public static String pathCreated(Path path) {
        return NORMAL + "A path between ("  + HIGHLIGHT + path.getMinimumBlock().getX() + NORMAL + ", "
                                            + HIGHLIGHT + path.getMinimumBlock().getY() + NORMAL + ", "
                                            + HIGHLIGHT + path.getMinimumBlock().getZ() + NORMAL + ") and ("
                                            + HIGHLIGHT + path.getMaximumBlock().getX() + NORMAL + ", "
                                            + HIGHLIGHT + path.getMaximumBlock().getY() + NORMAL + ", "
                                            + HIGHLIGHT + path.getMaximumBlock().getZ() + NORMAL + ") has been created!";
    }

    public static String missingPath() {
        return ERROR + "A path doesn't exist at this location!";
    }

    public static String pathRemoved(Path path) {
        return NORMAL + "A path (" + HIGHLIGHT + path.getMinimumBlock().getX() + NORMAL + ", "
                                   + HIGHLIGHT + path.getMinimumBlock().getY() + NORMAL + ", "
                                   + HIGHLIGHT + path.getMinimumBlock().getZ() + NORMAL + ") and ("
                                   + HIGHLIGHT + path.getMaximumBlock().getX() + NORMAL + ", "
                                   + HIGHLIGHT + path.getMaximumBlock().getY() + NORMAL + ", "
                                   + HIGHLIGHT + path.getMaximumBlock().getZ() + NORMAL + ") has been removed!";
    }
    
}
