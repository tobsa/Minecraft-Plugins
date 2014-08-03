package catapultblocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedCatapultManager implements Serializable {
    private List<SerializedCatapultBlock> serializedCatapultBlocks = new ArrayList();
    
    public SerializedCatapultManager(CatapultManager catapultManager) {
        for(CatapultBlock catapultBlock : catapultManager.getCapultBlocks())
            serializedCatapultBlocks.add(new SerializedCatapultBlock(catapultBlock));
    }

    public CatapultManager getCatapultManager() {
        CatapultManager catapultManager = new CatapultManager();
        
        for(SerializedCatapultBlock serializedCatapultBlock : serializedCatapultBlocks)
            catapultManager.addCatapultBlock(serializedCatapultBlock.getCatapultBlock());
        
        return catapultManager;
    }
    
}
