package toggleblocks;

import puzzlepack.BaseFileManager;
import static puzzlepack.BaseFileManager.isDirectory;
import static puzzlepack.BaseFileManager.loadObject;
import static puzzlepack.BaseFileManager.makeDirectory;
import static puzzlepack.BaseFileManager.saveObject;
import toggleblocks.group.GroupManager;

public class FileManager extends BaseFileManager {
    private static final String PATH = "plugins/ToggleBlocks";
    private static final String REGIONS  = "/regions";
    private static final String GROUPS = "/groups.dat";
    
    public static RegionManager loadRegionManager() {
        SerializedRegionManager regionManager = (SerializedRegionManager)loadObject(PATH + REGIONS);
        if(regionManager == null)        
            return new RegionManager();
        
        return regionManager.getRegionManager();
    }
    
    public static void save(RegionManager regionManager) {
        if(!isDirectory(PATH))
            makeDirectory(PATH);
        
        saveObject(new SerializedRegionManager(regionManager), PATH + REGIONS);
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
