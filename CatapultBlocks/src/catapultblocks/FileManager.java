package catapultblocks;

import puzzlepack.BaseFileManager;


public class FileManager extends BaseFileManager {
    
    public static CatapultManager load() {
        SerializedCatapultManager serializedCatapultManager = (SerializedCatapultManager)loadObject("plugins/CatapultBlocks/catapults.dat");
        if(serializedCatapultManager == null)
            return new CatapultManager();
        
        return serializedCatapultManager.getCatapultManager();
    }
    
    public static void save(CatapultManager catapultManager) {
        if(!isDirectory("plugins/CatapultBlocks"))
            makeDirectory("plugins/CatapultBlocks");
        
        saveObject(new SerializedCatapultManager(catapultManager), "plugins/CatapultBlocks/catapults.dat");
    }    
}
