package interactblocks;

import puzzlepack.BaseFileManager;

public class FileManager extends BaseFileManager {
    
    public static InteractBlockManager load() {
        SerializedInteractBlockManager blockManager = (SerializedInteractBlockManager)loadObject("plugins/InteractBlocks/interactblocks.dat");
        if(blockManager == null)
            return new InteractBlockManager();
        
        return blockManager.getInteractBlockManager();
    }
    
    public static void save(InteractBlockManager blockManager) {
        if(!isDirectory("plugins/InteractBlocks"))
            makeDirectory("plugins/InteractBlocks");
        
        saveObject(new SerializedInteractBlockManager(blockManager), "plugins/InteractBlocks/interactblocks.dat");
    }
    
}
