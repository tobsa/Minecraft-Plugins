package areacollider;

import areacollider.SerializedArea;
import areacollider.Area;
import areacollider.AreaManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedAreaManager implements Serializable {
    private List<SerializedArea> serializedAreas = new ArrayList();
    
    public SerializedAreaManager(AreaManager areaManager) {
        for(Area area : areaManager.getAreas())
            serializedAreas.add(new SerializedArea(area));
    }
    
    public AreaManager getAreaManager() {
        AreaManager areaManager = new AreaManager();
        
        for(SerializedArea serializedArea : serializedAreas)
            areaManager.addArea(serializedArea.getArea());
        
        return areaManager;
    }
}
