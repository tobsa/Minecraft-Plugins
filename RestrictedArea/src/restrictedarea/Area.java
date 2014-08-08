package restrictedarea;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class Area {
    private String name;
    private String playerName;
    private Location location;
    private List<SubArea> areas = new ArrayList();
    
    public Area(String name, String playerName) {
        this.name = name;
        this.playerName = playerName;
    }
    
    public Area(String name, String playerName, SubArea area, Location location) {
        this.name = name;
        this.playerName = playerName;
        this.location = location;
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
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public boolean contains(Location location) {
        for(SubArea area : areas)
            if(area.contains(location))
                return true;
        
        return false;
    }
    
    public List<SubArea> getAreas(Location location) {
        List<SubArea> collidedAreas = new ArrayList();
        
        for(SubArea area : areas)
            if(area.contains(location))
                collidedAreas.add(area);
        
        return collidedAreas;
    }
    
    public List<SubArea> getAreas() {
        return areas;
    }
    
    public Block getMinimumBlock() {
        int minimumX = Integer.MAX_VALUE;
        int minimumY = Integer.MAX_VALUE;
        int minimumZ = Integer.MAX_VALUE;
        
        for(SubArea area : areas) {
            minimumX = Math.min(minimumX, area.getMinimumX());
            minimumY = Math.min(minimumY, area.getMinimumY());
            minimumZ = Math.min(minimumZ, area.getMinimumZ());
        }
            
        return Bukkit.getWorld("world").getBlockAt(minimumX, minimumY, minimumZ);
    }
    
    public Block getMaximumBlock() {
        int maximumX = Integer.MIN_VALUE;
        int maximumY = Integer.MIN_VALUE;
        int maximumZ = Integer.MIN_VALUE;
        
        for(SubArea area : areas) {
            maximumX = Math.max(maximumX, area.getMaximumX());
            maximumY = Math.max(maximumY, area.getMaximumY());
            maximumZ = Math.max(maximumZ, area.getMaximumZ());
        }
            
        return Bukkit.getWorld("world").getBlockAt(maximumX, maximumY, maximumZ);
    }
    
    public void removeArea(int index) {
        areas.remove(index);
    }
    
    public SubArea getArea(int index) {
        return areas.get(index);
    }
}
