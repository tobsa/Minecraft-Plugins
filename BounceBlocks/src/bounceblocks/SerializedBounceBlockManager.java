package bounceblocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedBounceBlockManager implements Serializable {
    private List<SerializedBounceBlock> bounceBlocks = new ArrayList();
    
    public SerializedBounceBlockManager(BounceBlockManager bounceBlockManager) {
        for(BounceBlock bounceBlock : bounceBlockManager.getBounceBlocks())
            bounceBlocks.add(new SerializedBounceBlock(bounceBlock));
    }
    
    public BounceBlockManager getBounceBlockManager() {
        BounceBlockManager bounceBlockManager = new BounceBlockManager();
        
        for(SerializedBounceBlock serializedBounceBlock : bounceBlocks)
            bounceBlockManager.addBounceBlock(serializedBounceBlock.getBounceBlock());
        
        return bounceBlockManager;
    }
}
