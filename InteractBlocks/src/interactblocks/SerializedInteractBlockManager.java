package interactblocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedInteractBlockManager implements Serializable {
    private List<SerializedInteractBlock> blocks = new ArrayList();
    
    public SerializedInteractBlockManager(InteractBlockManager blockManager) {
        for(InteractBlock block : blockManager.getInteractBlocks())
            blocks.add(new SerializedInteractBlock(block));
    }
    
    public InteractBlockManager getInteractBlockManager() {
        InteractBlockManager blockManager = new InteractBlockManager();
        
        for(SerializedInteractBlock block : blocks)
            blockManager.addInteractBlock(block.getInteractBlock());
        
        return blockManager;
    }
}
