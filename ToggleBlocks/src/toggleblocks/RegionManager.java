package toggleblocks;

import basepack.ItemManager;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.block.Block;

public class RegionManager extends ItemManager<Region>{
    private Map<String, Region> editRegions = new LinkedHashMap();
        
    public Region getEditRegion(String playerName) {
        return editRegions.get(playerName);
    }
    
    public void setEditRegion(String playerName, Region region) {
        editRegions.put(playerName, region);
    }
    
    public Region getRegionByLinkBlock(String playerName, Block block) {
        for(Region region : get())
            if(playerName.equalsIgnoreCase(region.getPlayerName()) && region.isLinkBlock(block))
                return region;
        
        return null;
    }
}
