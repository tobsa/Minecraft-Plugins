package portals;

import puzzlepack.BaseFileManager;
import static puzzlepack.BaseFileManager.isDirectory;
import static puzzlepack.BaseFileManager.loadObject;
import static puzzlepack.BaseFileManager.makeDirectory;
import static puzzlepack.BaseFileManager.saveObject;

public class FileManager extends BaseFileManager {
    
    public static PortalManager load() {
        SerializedPortalManager serializedPortalManager = (SerializedPortalManager)loadObject("plugins/Portals/portals.dat");
        if(serializedPortalManager == null)
            return new PortalManager();
        
        return serializedPortalManager.getPortalManager();
    }
    
    public static void save(PortalManager portalManager) {
        if(!isDirectory("plugins/Portals"))
            makeDirectory("plugins/Portals");
        
        saveObject(new SerializedPortalManager(portalManager), "plugins/Portals/portals.dat");
    }
    
}
