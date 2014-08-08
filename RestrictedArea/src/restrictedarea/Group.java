package restrictedarea;

import java.util.ArrayList;
import java.util.List;

public class Group {
    private String name;
    private String playerName;
    private List<Area> areas = new ArrayList(); 
    
    public Group(String name, String playerName) {
        this.name = name;
        this.playerName = playerName;
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
    
    public void addArea(Area area) {
        areas.add(area);
    }
    
    public void removeArea(Area area) {
        areas.remove(area);
    }
    
    public List<Area> getAreas() {
        return areas;
    }
    
    
}
