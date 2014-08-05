package interactblocks;

import interactblocks.giveitem.GiveItemResponse;
import interactblocks.message.MessageResponse;
import interactblocks.soundblock.SoundResponse;
import puzzlepack.BaseMessage;

public class PlayerMessage extends BaseMessage {
                
    public static String invalidSound(String name) {
        return ERROR + "Sound '" + HIGHLIGHT + name + ERROR + "' is not valid!";
    }
     
    public static String invalidNumber1(String number) {
        return ERROR + "'" + HIGHLIGHT + number + ERROR + "' must be a number!";
    }
    
    public static String invalidNumber2(Integer page, int numPages) {
        return ERROR + "Page '" + HIGHLIGHT + page + ERROR + "' is invalid! Must be between 1 and " + numPages + "!";
    }
    
    public static String listMaterial(Integer page, int numPages) {
        return HEADER + "========== Materials (" + NORMAL + page + "/" + numPages + HEADER + ") ==========";
    }
    
    public static String listSound(Integer page, int numPages) {
        return HEADER + "========== Sounds (" + NORMAL + page + "/" + numPages + HEADER + ") ==========";
    }

    public static String soundBlockCreated(InteractBlock block, SoundResponse response) {
        return NORMAL + "A sound block (" + HIGHLIGHT + block.getBlock().getX() + NORMAL + ", " 
                                          + HIGHLIGHT + block.getBlock().getY() + NORMAL + ", "
                                          + HIGHLIGHT + block.getBlock().getZ() + NORMAL + ", "
                                          + HIGHLIGHT + response.getSound().toString() + NORMAL + ") was created!";
    }

    public static String interactBlockRemoved(InteractBlock block) {
        return NORMAL + "A " + block.getResponse().getName() + " block (" + HIGHLIGHT + block.getBlock().getX() + NORMAL + ", " 
                                                                          + HIGHLIGHT + block.getBlock().getY() + NORMAL + ", "
                                                                          + HIGHLIGHT + block.getBlock().getZ() + NORMAL + ") was removed!";
    }

    public static String missingInteractBlock() {
        return ERROR + "There is no interact block at this location!";
    }

    public static String messageBlockCreated(InteractBlock block, MessageResponse response) {
       return NORMAL + "A message block (" + HIGHLIGHT + block.getBlock().getX() + NORMAL + ", " 
                                           + HIGHLIGHT + block.getBlock().getY() + NORMAL + ", "
                                           + HIGHLIGHT + block.getBlock().getZ() + NORMAL + ") was created!";
    }

    public static String giveItemCreated(InteractBlock block, GiveItemResponse response) {
        return NORMAL + "A give item block (" + HIGHLIGHT + block.getBlock().getX() + NORMAL + ", " 
                                              + HIGHLIGHT + block.getBlock().getY() + NORMAL + ", "
                                              + HIGHLIGHT + block.getBlock().getZ() + NORMAL + ") was created!";
    }

    public static String invalidMaterial(String material) {
        return ERROR + "Material '" + HIGHLIGHT + material + ERROR + "' is not valid!";
    }
}
