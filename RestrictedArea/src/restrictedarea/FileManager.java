package restrictedarea;

import puzzlepack.BaseFileManager;

public class FileManager extends BaseFileManager {
    private static final String PATH = "plugins/RestrictedArea";
    private static final String NAME = "/restricted.dat";
    
    public static AreaManager load() {
        SerializedAreaManager areaManager = (SerializedAreaManager)loadObject(PATH + NAME);
        if(areaManager == null)        
            return new AreaManager();
        
        return areaManager.getAreaManager();
    }
    
    public static void save(AreaManager areaManager) {
        if(!isDirectory(PATH))
            makeDirectory(PATH);
        
        saveObject(new SerializedAreaManager(areaManager), PATH + NAME);
    }
    
}
