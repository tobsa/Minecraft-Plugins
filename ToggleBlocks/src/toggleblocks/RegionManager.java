package toggleblocks;

import java.util.LinkedHashMap;
import java.util.Map;

public class RegionManager {
    private Map<String, Region> regions = new LinkedHashMap();
    private Region editRegion;
        
    public boolean findRegion(String name) {
        return regions.containsKey(name);
    }
    
    public void addRegion(String name) {
        regions.put(name, new Region(name));
    }
    
    public Region getRegion(String name) {
        return regions.get(name);
    }
    
    public boolean isEditMode() {
        return editRegion != null;
    }
    
    public void setEditMode(Region editRegion) {
        this.editRegion = editRegion;
    }
    
    public Region getEditRegion() {
        return editRegion;
    }
}
