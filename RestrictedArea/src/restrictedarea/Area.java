package restrictedarea;

import basepack.BaseItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;

public class Area extends BaseItem {
    private Location location;
    private String message;
    private List<SubArea> subAreas = new ArrayList();
    
    public Area(String name, String playerName) {
        super(name, playerName);
    }
    
    public Area(String name, String playerName, SubArea subArea, Location location) {
        super(name, playerName);
        
        this.location = location;
        subAreas.add(subArea);
    }
    
    public String getMessage() {
        return message;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public List<SubArea> getSubAreas() {
        return subAreas;
    }
        
    public void setMessage(String message) {
        this.message = message;
    }
    
    public void addSubArea(SubArea subArea) {
        subAreas.add(subArea);
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
    
    public boolean contains(Location location) {
        for(SubArea area : subAreas)
            if(area.contains(location))
                return true;
        
        return false;
    }
    
    public void removeSubArea(int index) {
        subAreas.remove(index);
    }
    
    public Block getMinimumBlock() {
        int minimumX = Integer.MAX_VALUE;
        int minimumY = Integer.MAX_VALUE;
        int minimumZ = Integer.MAX_VALUE;
        
        for(SubArea area : subAreas) {
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
        
        for(SubArea area : subAreas) {
            maximumX = Math.max(maximumX, area.getMaximumX());
            maximumY = Math.max(maximumY, area.getMaximumY());
            maximumZ = Math.max(maximumZ, area.getMaximumZ());
        }
            
        return Bukkit.getWorld("world").getBlockAt(maximumX, maximumY, maximumZ);
    }
    
    @Override
    public boolean equals(Object object) {
        if(object == null)
            return false;
        if(object == this)
            return true;
        if(!(object instanceof Area))
            return false;
        
        Area area = (Area)object;
        return area.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.location);
        hash = 37 * hash + Objects.hashCode(this.message);
        hash = 37 * hash + Objects.hashCode(this.subAreas);
        return hash;
    }

}
