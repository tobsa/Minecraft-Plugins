package toggleblocks;

import org.bukkit.block.Block;
import puzzlepack.BaseMessage;

public class PlayerMessage extends BaseMessage {
        
    public static String invalidArguments(String usage) {
        return ERROR + "Invalid arguments. Usage: " + usage;
    }
        
    public static String blockPlaced(String name) {
        return NORMAL + "A block has been added to region '" + HIGHLIGHT + name + NORMAL + "'!";
    }
    
    public static String regionCreated(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' has been created!";
    }
    
    public static String blockRemoved(String name) {
        return NORMAL + "A block has been removed from region '" + HIGHLIGHT + name + NORMAL + "'!";
    }
    
    public static String missingRegion(String name) {
        return ERROR + "Region '" + HIGHLIGHT + name + ERROR + "' doesn't exist!";
    }
    
    public static String linkSet(String name) {
        return NORMAL + "A link for region '" + HIGHLIGHT + name + NORMAL + "' has been set!";
    }
    
    public static String redstoneLinkSet(String name) {
        return NORMAL + "A redstone link for region '" + HIGHLIGHT + name + NORMAL + "' has been set!";
    }
    
    public static String editmodeYes(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' is now in edit mode!";  
    }
    
    public static String editmodeNo(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' is no longer in edit mode!";  
    }
    
    public static String listHeader() {
        return HEADER + "========== Regions ==========";
    }
    
    public static String regionRemoved(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' was removed!";
    }
    
    public static String getLinkDeleted(String name) {
        return NORMAL + "A link for region '" + HIGHLIGHT + name + NORMAL + "' was removed!";
    }

    public static String toggledOff(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' has been toggled off!";
    }

    public static String toggledOn(String name) {
        return NORMAL + "Region '" + HIGHLIGHT + name + NORMAL + "' has been toggled on!";
    }
    
    public static String help() {
        return HIGHLIGHT + "========== Help ==========";
    }

    public static String missingLink(Block block) {
        return ERROR + "No region information at this location (" + HIGHLIGHT + block.getX() + ERROR + ", "
                                                                  + HIGHLIGHT + block.getY() + ERROR + ", "
                                                                  + HIGHLIGHT + block.getZ() + ERROR + ")!";
    }

    public static String infoHeader() {
        return HIGHLIGHT + "========== Region info ==========";
    }
    
    public static String regionInfoBlock(LinkBlock block) {
        return NORMAL + "  Link block: (" + HIGHLIGHT + block.getBlock().getX() + NORMAL + ", "
                                          + HIGHLIGHT + block.getBlock().getY() + NORMAL + ", "
                                          + HIGHLIGHT + block.getBlock().getZ() + NORMAL + ")!";
    }
    
    public static String linkType(LinkType type) {
        return NORMAL + "  Link type: " + HIGHLIGHT + type.toString() + NORMAL + "";
    }
    
    public static String regionName(String name) {
        return NORMAL + "  Region: " + HIGHLIGHT + name + NORMAL + "";
    }

    public static String regionExists(String name) {
        return ERROR + "A region '" + HIGHLIGHT + name + ERROR + "' already exists!";
    }

    public static String regionRenamed(String originalName, String newName) {
        return NORMAL + "Region '" + HIGHLIGHT + originalName + NORMAL + "' has been renamed to '" + HIGHLIGHT + newName + NORMAL + "'!";  
    }

    public static String missingRegionSelection() {
        return ERROR + "Make a region selection first!";
    }

    public static String includeBlocks(int numBlocks, String name) {
        return NORMAL + "An amount of " + HIGHLIGHT + numBlocks + NORMAL + " blocks has been added to region '" + HIGHLIGHT + name + NORMAL + "'!";
    }

    public static String omittedBlocks(int numOmittedBlocks) {
        return NORMAL + "Omitted blocks: " + HIGHLIGHT + numOmittedBlocks + NORMAL + "";
    }

    public static String removedBlocks(int removedBlocks, String name) {
        return NORMAL + "An amount of " + HIGHLIGHT + removedBlocks + NORMAL + " blocks has been removed from region '" + HIGHLIGHT + name + NORMAL + "'!";
    }
}
