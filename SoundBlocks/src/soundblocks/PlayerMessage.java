package soundblocks;

import puzzlepack.BaseMessage;

public class PlayerMessage extends BaseMessage {
                
    public static String invalidSound(String name) {
        return ERROR + "Sound '" + HIGHLIGHT + name + ERROR + "' is not valid!";
    }
     
    public static String invalidNumber1(Integer page) {
        return ERROR + "'" + HIGHLIGHT + page + ERROR + "' must be a number!";
    }
    
    public static String invalidNumber2(Integer page) {
        return ERROR + "'" + HIGHLIGHT + page + ERROR + "' must either be 1 or 2!";
    }
    
    public static String listFooter(Integer page) {
        return HEADER + "========== Sounds (" + NORMAL + page + "/2" + HEADER + ") ==========";
    }

    public static String soundBlockCreated(SoundBlock soundBlock) {
        return NORMAL + "A sound block (" + HIGHLIGHT + soundBlock.getBlock().getX() + NORMAL + ", " 
                                          + HIGHLIGHT + soundBlock.getBlock().getY() + NORMAL + ", "
                                          + HIGHLIGHT + soundBlock.getBlock().getZ() + NORMAL + ", "
                                          + HIGHLIGHT + soundBlock.getSound().toString() + NORMAL + ") was created!";
    }

    public static String soundBlockExist(SoundBlock soundBlock) {
        return ERROR + "A sound block ("  + HIGHLIGHT + soundBlock.getBlock().getX() + ERROR + ", " 
                                          + HIGHLIGHT + soundBlock.getBlock().getY() + ERROR + ", "
                                          + HIGHLIGHT + soundBlock.getBlock().getZ() + ERROR + ", "
                                          + HIGHLIGHT + soundBlock.getSound().toString() + ERROR + ") already exist at this location!";
    }

    public static String soundBlockRemoved(SoundBlock soundBlock) {
        return NORMAL + "A sound block (" + HIGHLIGHT + soundBlock.getBlock().getX() + NORMAL + ", " 
                                          + HIGHLIGHT + soundBlock.getBlock().getY() + NORMAL + ", "
                                          + HIGHLIGHT + soundBlock.getBlock().getZ() + NORMAL + ", "
                                          + HIGHLIGHT + soundBlock.getSound().toString() + NORMAL + ") was removed!";
    }

    public static String missingSoundBlock() {
        return ERROR + "There is no soundblock at this location!";
    }
}
