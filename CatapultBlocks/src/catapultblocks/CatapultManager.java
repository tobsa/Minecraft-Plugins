package catapultblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;

public class CatapultManager {
    private List<CatapultBlock> catapultBlocks = new ArrayList();
    
    public CatapultManager() {
        catapultBlocks = FileManager.load();
    }
    
    public void addCatapultBlock(CatapultBlock catapultBlock) {
        catapultBlocks.add(catapultBlock);
        FileManager.save(catapultBlock);
    }
        
    public CatapultBlock getCatapultBlock(Block block) {
        for(CatapultBlock catapultBlock : catapultBlocks)
            if(block.equals(catapultBlock.getBlock()))
                return catapultBlock;
        
        return null;
    }

    public List<CatapultBlock> getCapultBlocks() {
        return catapultBlocks;
    }
    
    public void removeCatapultBlock(CatapultBlock catapultBlock) {
        catapultBlocks.remove(catapultBlock);
        FileManager.remove(catapultBlock);
    }
}
