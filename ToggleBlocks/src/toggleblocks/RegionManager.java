package toggleblocks;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.block.Block;

public class RegionManager {
    private Map<String, Region> regions;
    private Map<String, Region> editRegions = new LinkedHashMap();
    
    public RegionManager(Map<String, Region> regions) {
        this.regions = regions;
    }
    
    public void addRegion(Region region) {
        regions.put(region.getName(), region);
    }
    
    public Region getRegion(String playerName, String name) {
        Region region = regions.get(name);
        if(region == null)
            return null;
        
        if(region.getPlayerName().equalsIgnoreCase(playerName))
            return region;
        
        return null;
    }
    
    public void removeRegion(Region region) {
        regions.remove(region.getName());
    }
    
    public List<Region> getRegions(String playerName) {
        List<Region> playerRegions = new ArrayList();
        
        for(Region region : regions.values())
            if(region.getPlayerName().equalsIgnoreCase(playerName))
                playerRegions.add(region);
        
        return playerRegions;
    }
    
    public List<Region> getRegions() {
        return new ArrayList(regions.values());
    }
    
    public Region getEditRegion(String playerName) {
        return editRegions.get(playerName);
    }
    
    public void setEditRegion(String playerName, Region region) {
        editRegions.put(playerName, region);
    }
    
    public Region getRegionByLinkBlock(String playerName, Block block) {
        for(Region region : regions.values())
            if(playerName.equalsIgnoreCase(region.getPlayerName()) && region.isLinkBlock(block))
                return region;
        
        return null;
    }
    
    public void renameRegion(Region region, String newName) {
        region.setName(newName);
        
        Map<String, Region> renamedRegions = new LinkedHashMap();
        
        for(Region oldRegion : regions.values())
            renamedRegions.put(oldRegion.getName(), oldRegion);
        
        regions = renamedRegions;        
    }
}
