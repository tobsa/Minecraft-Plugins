package restrictedarea;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;

public class Area {
    private String name;
    private String playerName;
    private List<SubArea> areas = new ArrayList();
    
    public Area(String name, String playerName) {
        this.name = name;
        this.playerName = playerName;
    }
    
    public Area(String name, String playerName, SubArea area) {
        this.name = name;
        this.playerName = playerName;
        areas.add(area);
    }
    
    public String getName() {
        return name;
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addArea(SubArea area) {
        areas.add(area);
    }
    
    public List<SubArea> getAreas(Location location) {
        List<SubArea> collidedAreas = new ArrayList();
        
        for(SubArea area : areas)
            if(area.contains(location))
                collidedAreas.add(area);
        
        return collidedAreas;
    }
    
    public void removeAreas(Location location) {
        areas.removeAll(getAreas(location));
    }
}
