package telepads;

import puzzlepack.BaseFileManager;
import static puzzlepack.BaseFileManager.isDirectory;
import static puzzlepack.BaseFileManager.loadObject;
import static puzzlepack.BaseFileManager.makeDirectory;
import static puzzlepack.BaseFileManager.saveObject;

public class FileManager extends BaseFileManager {
    
    public static TelepadManager load() {
        SerializedTelepadManager serializedTelepadManager = (SerializedTelepadManager)loadObject("plugins/Telepads/telepads.dat");
        if(serializedTelepadManager == null)
            return new TelepadManager();
        
        return serializedTelepadManager.getTelepadManager();
    }
    
    public static void save(TelepadManager telepadManager) {
        if(!isDirectory("plugins/Telepads"))
            makeDirectory("plugins/Telepads");
        
        saveObject(new SerializedTelepadManager(telepadManager), "plugins/Telepads/telepads.dat");
    }
    
}
