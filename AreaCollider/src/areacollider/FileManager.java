package areacollider;

import puzzlepack.BaseFileManager;

public class FileManager extends BaseFileManager {
    
    public static AreaManager load() {
        SerializedAreaManager serializedAreaManager = (SerializedAreaManager)loadObject("plugins/AreaCollider/areas.dat");
        if(serializedAreaManager == null)
            return new AreaManager();
        
        return serializedAreaManager.getAreaManager();
    }
    
    public static void save(AreaManager areaManager) {
        if(!isDirectory("plugins/AreaCollider"))
            makeDirectory("plugins/AreaCollider");
        
        saveObject(new SerializedAreaManager(areaManager), "plugins/AreaCollider/areas.dat");
    }
    
}
