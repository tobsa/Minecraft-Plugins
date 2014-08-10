package toggleblocks;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class SerializedRegionManager implements Serializable {
    private Map<String, SerializedRegion> serializedRegions = new LinkedHashMap();
    
    public SerializedRegionManager(RegionManager regionManager) {
        for(Region region : regionManager.get())
            serializedRegions.put(region.getName(), new SerializedRegion(region));
    }
    
    public RegionManager getRegionManager() {
        RegionManager regionManager = new RegionManager();
        
        for(SerializedRegion serializedRegion : serializedRegions.values())
            regionManager.add(serializedRegion.getRegion());
        
        return regionManager;
    }
}
