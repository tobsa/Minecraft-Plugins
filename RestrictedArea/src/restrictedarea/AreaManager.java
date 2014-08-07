package restrictedarea;

import java.util.LinkedHashMap;
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
    
    public void removeArea(Area area) {
        areas.remove(area.getName());
    }
    
    public void renameArea(Area area, String newName) {
        area.setName(newName);
        
        Map<String, Area> renamedAreas = new LinkedHashMap();
        
        for(Area oldArea : areas.values())
            renamedAreas.put(oldArea.getName(), oldArea);
        
        areas = renamedAreas;
    }
}
