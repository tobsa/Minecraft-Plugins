package redstonecombiner;

import puzzlepack.BaseFileManager;

public class FileManager extends BaseFileManager {
    
    public static CombinerManager load() {
        SerializedCombinerManager combinerManager = (SerializedCombinerManager)loadObject("plugins/RedstoneCombiner/combiners.dat");
        if(combinerManager == null)
            return new CombinerManager();
        
        return combinerManager.getCombinerManager();
    } 
    
    public static void save(CombinerManager combinerManager) {
        if(!isDirectory("plugins/RedstoneCombiner"))
            makeDirectory("plugins/RedstoneCombiner");
        
        saveObject(new SerializedCombinerManager(combinerManager), "plugins/RedstoneCombiner/combiners.dat");
    }
    
       
}
