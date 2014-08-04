package bounceblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;

public class OldLoader {
    private List<BounceBlock> bounceBlocks = new ArrayList();
    
    public boolean load(BounceBlocks plugin) {
        if(!plugin.getConfig().contains("bounceblocks"))
            return false;
        
        for(String name : plugin.getConfig().getConfigurationSection("bounceblocks").getKeys(false)) {
            int x = plugin.getConfig().getInt("bounceblocks." + name + ".x");
            int y = plugin.getConfig().getInt("bounceblocks." + name + ".y");
            int z = plugin.getConfig().getInt("bounceblocks." + name + ".z");
            double jumpStrength = plugin.getConfig().getDouble("bounceblocks." + name + ".jumpStrength");

            bounceBlocks.add(new BounceBlock(new Location(plugin.getServer().getWorld("world"), x, y, z).getBlock(), jumpStrength));
        }
        
        return true;
    }
    
    public BounceBlockManager getBounceBlockManager() {
        BounceBlockManager bounceBlockManager = new BounceBlockManager();
        
        for(BounceBlock bounceBlock : bounceBlocks)
            bounceBlockManager.addBounceBlock(bounceBlock);
        
        return bounceBlockManager;
    }
}
