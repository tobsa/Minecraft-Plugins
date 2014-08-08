package restrictedarea;

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
    
    public List<Area> getAreas(String playerName) {
        List<Area> values = new ArrayList();
        
        for(Area area : areas.values())
            if(area.getPlayerName().equalsIgnoreCase(playerName))
                values.add(area);
        
        return values;                
    }
        
    public List<Area> getAreas() {
        return new ArrayList(areas.values());
    }
    
    public void removeArea(Area area) {
        areas.remove(area.getName());
    }
    
    public void setIndex(Area area, int index) {
        List<Area> list = getAreas();
        
        list.remove(area);
        list.add(index, area);
        
        areas.clear();
        
        for(Area listarea : list)
            areas.put(listarea.getName(), listarea);
    }
    
    public void renameArea(Area area, String newName) {
        area.setName(newName); 
        
        Map<String, Area> renamedAreas = new LinkedHashMap();
        
        for(Area oldArea : areas.values())
            renamedAreas.put(oldArea.getName(), oldArea);
        
        areas = renamedAreas;
    }
}
