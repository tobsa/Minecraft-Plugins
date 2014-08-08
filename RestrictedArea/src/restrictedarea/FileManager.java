package restrictedarea;

import restrictedarea.serialized.SerializedAreaManager;
import puzzlepack.BaseFileManager;
import restrictedarea.group.GroupManager;
import restrictedarea.serialized.SerializedGroupManager;

public class FileManager extends BaseFileManager {
    private static final String PATH = "plugins/RestrictedArea";
    private static final String AREAS  = "/restricted.dat";
    private static final String GROUPS = "/groups.dat";
    
    public static AreaManager loadAreaManager() {
        SerializedAreaManager areaManager = (SerializedAreaManager)loadObject(PATH + AREAS);
        if(areaManager == null)        
            return new AreaManager();
        
        return areaManager.getAreaManager();
    }
    
    public static void save(AreaManager areaManager) {
        if(!isDirectory(PATH))
            makeDirectory(PATH);
        
        saveObject(new SerializedAreaManager(areaManager), PATH + AREAS);
    }
    
    public static GroupManager loadGroupManager() {
        SerializedGroupManager groupManager = (SerializedGroupManager)loadObject(PATH + GROUPS);
        if(groupManager == null)
            return new GroupManager();
        
        return groupManager.getGroupManager();
    }
    
    public static void save(GroupManager groupManager) {
        if(!isDirectory(PATH))
            makeDirectory(PATH);
        
        saveObject(new SerializedGroupManager(groupManager), PATH + GROUPS);
    }
}
