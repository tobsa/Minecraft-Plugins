package restrictedarea.serialized;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import restrictedarea.Area;
import restrictedarea.AreaManager;

public class SerializedAreaManager implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Map<String, SerializedArea> areas = new LinkedHashMap();
    
    public SerializedAreaManager(AreaManager areaManager) {
        for(Area area : areaManager.get())
            areas.put(area.getName(), new SerializedArea(area));
    }
    
    public AreaManager getAreaManager() {
        AreaManager areaManager = new AreaManager();
        
        for(SerializedArea area : areas.values())
            areaManager.add(area.getArea());
        
        return areaManager;
    }
}
