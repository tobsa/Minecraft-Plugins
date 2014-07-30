package toggleblocks;

import org.bukkit.block.Block;

public class LinkBlock {    
    private Block block;
    private LinkType linkType;
    
    public LinkBlock(Block block, LinkType linkType) {
        this.block = block;
        this.linkType = linkType;
    }
    
    public Block getBlock() {
        return block;
    }
    
    public LinkType getLinkType() {
        return linkType;
    }
}
