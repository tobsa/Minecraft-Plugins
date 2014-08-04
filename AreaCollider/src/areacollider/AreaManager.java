package areacollider;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AreaManager {
    private Map<String, Area> areas = new LinkedHashMap();
        
    public void addArea(Area area) {
        areas.put(area.getName(), area);
    }
    
    public Area getArea(String playerName, String name) {
        Area area = areas.get(name);
        if(area == null)
            return null;
        
        if(area.getPlayerName().equalsIgnoreCase(playerName))
            return area;
        
        return null;
    }
    
    public List<Area> getAreas() {
        return new ArrayList(areas.values());
    }
    
    public void renameArea(Area area, String newName) {
        Area newArea = new Area(area.getPlayerName(), newName, area.getMinimumBlock(), area.getMaximumBlock(), area.getResponse());
        removeArea(area.getName());
        addArea(newArea);
    }
    
    public void removeArea(String name) {
        areas.remove(name);
    }
}
