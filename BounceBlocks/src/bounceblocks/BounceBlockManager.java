package bounceblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;

public class BounceBlockManager {
    private List<BounceBlock> bounceBlocks = new ArrayList();
        
    public void addBounceBlock(BounceBlock bounceBlock) {     
        bounceBlocks.add(bounceBlock);
    }
    
    public BounceBlock getBounceBlock(Block block) {
        for(BounceBlock bounceBlock : bounceBlocks) {
            if(bounceBlock.getBlock().equals(block))
                return bounceBlock;
        }
        
        return null;
    }
    
    public boolean removeBounceBlock(BounceBlock bounceBlock) {
        return bounceBlocks.remove(bounceBlock);
    }
    
    public List<BounceBlock> getBounceBlocks() {
        return bounceBlocks;
    }
    
    public void setJumpStrength(BounceBlock block, double jumpStrength) {
        block.setJumpStrength(jumpStrength);     
    }
    
    public boolean removeBlock(Block block) {
        for(BounceBlock bounceBlock : bounceBlocks)
            if(block.equals(bounceBlock.getBlock()))
                return bounceBlocks.remove(bounceBlock);                
        
        return false;
    }
}
