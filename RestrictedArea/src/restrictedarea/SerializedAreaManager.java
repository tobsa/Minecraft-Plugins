package restrictedarea;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class SerializedAreaManager implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Map<String, SerializedArea> areas = new LinkedHashMap();
    
    public SerializedAreaManager(AreaManager areaManager) {
        for(Area area : areaManager.getAreas())
            areas.put(area.getName(), new SerializedArea(area));
    }
    
    public AreaManager getAreaManager() {
        AreaManager areaManager = new AreaManager();
        
        for(SerializedArea area : areas.values())
            areaManager.addArea(area.getArea());
        
        return areaManager;
    }
}
