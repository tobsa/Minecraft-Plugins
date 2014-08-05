package interactblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;

public class InteractBlockManager {
    private List<InteractBlock> interactBlocks = new ArrayList();
    
    public void addInteractBlock(InteractBlock block) {
        interactBlocks.add(block);
    }
    
    public InteractBlock getInteractBlock(Block block) {
        for(InteractBlock interactBlock : interactBlocks)
            if(interactBlock.getBlock().equals(block))
                return interactBlock;
        
        return null;
    }
    
    public List<InteractBlock> getInteractBlocks(Block block) {
        List<InteractBlock> blocks = new ArrayList();
        
        for(InteractBlock interactBlock : interactBlocks)
            if(interactBlock.getBlock().equals(block))
                blocks.add(interactBlock);
        
        return blocks;
    }
    
    public boolean removeInteractBlock(InteractBlock block) {
        return interactBlocks.remove(block);
    }
    
    public List<InteractBlock> getInteractBlocks() {
        return interactBlocks;
    }
}
