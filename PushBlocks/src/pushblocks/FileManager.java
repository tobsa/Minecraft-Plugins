package pushblocks;

import puzzlepack.BaseFileManager;


public class FileManager extends BaseFileManager {
    
    public static PathManager load() {
        SerializedPathManager serializedPathManager = (SerializedPathManager)loadObject("plugins/PushBlocks/paths.dat");
        if(serializedPathManager == null)
            return new PathManager();
        
        return serializedPathManager.getPathManager();
    }
    
    public static void save(PathManager pathManager) {
        if(!isDirectory("plugins/PushBlocks"))
            makeDirectory("plugins/PushBlocks");
        
        saveObject(new SerializedPathManager(pathManager), "plugins/PushBlocks/paths.dat");
    }
}
