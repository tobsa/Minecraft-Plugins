package bounceblocks;

import puzzlepack.BaseFileManager;

public class FileManager extends BaseFileManager {
    
    public static BounceBlockManager load() {
        SerializedBounceBlockManager serializedBounceBlockManager = (SerializedBounceBlockManager)loadObject("plugins/BounceBlocks/bounceblocks.dat");
        if(serializedBounceBlockManager == null)
            return new BounceBlockManager();
        
        return serializedBounceBlockManager.getBounceBlockManager();
    }
    
    public static void save(BounceBlockManager bounceBlockManager) {
        if(!isDirectory("plugins/BounceBlocks"))
            makeDirectory("plugins/BounceBlocks");
        
        saveObject(new SerializedBounceBlockManager(bounceBlockManager), "plugins/BounceBlocks/bounceblocks.dat");
    }
    
}
