package pushblocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedPathManager implements Serializable {
    private List<SerializedPath> serializedPaths = new ArrayList();
    
    public SerializedPathManager(PathManager pathManager) {
        for(Path path : pathManager.getPaths())
            serializedPaths.add(new SerializedPath(path));
    }
    
    public PathManager getPathManager() {
        PathManager pathManager = new PathManager();
        
        for(SerializedPath serializedPath : serializedPaths)
            pathManager.addPath(serializedPath.getPath());
        
        return pathManager;
    }
}
