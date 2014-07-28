package bounceblock;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import puzzlepack.PuzzlePack;

public class BounceBlockManager {
    private PuzzlePack plugin;
    private List<BounceBlock> bounceBlocks = new ArrayList();
    
    public BounceBlockManager(PuzzlePack plugin) {
        this.plugin = plugin;
        
        if(plugin.getConfig().contains("bounceblocks")) {
            for(String name : plugin.getConfig().getConfigurationSection("bounceblocks").getKeys(false)) {
                int x = plugin.getConfig().getInt("bounceblocks." + name + ".x");
                int y = plugin.getConfig().getInt("bounceblocks." + name + ".y");
                int z = plugin.getConfig().getInt("bounceblocks." + name + ".z");
                double jumpStrength = plugin.getConfig().getDouble("bounceblocks." + name + ".jumpStrength");
                
                bounceBlocks.add(new BounceBlock(new Location(plugin.getServer().getWorld("world"), x, y, z).getBlock(), jumpStrength));
            }
        }
    }
    
    public void addBounceBlock(Block block, double jumpStrength) {
        BounceBlock bounceBlock = new BounceBlock(block, jumpStrength);
        
        int x = bounceBlock.getBlock().getX();
        int y = bounceBlock.getBlock().getY();
        int z = bounceBlock.getBlock().getZ();
                
        plugin.getConfig().set("bounceblocks." + x + "," + y + "," + z + ".x", bounceBlock.getBlock().getX());
        plugin.getConfig().set("bounceblocks." + x + "," + y + "," + z + ".y", bounceBlock.getBlock().getY());
        plugin.getConfig().set("bounceblocks." + x + "," + y + "," + z + ".z", bounceBlock.getBlock().getZ());
        plugin.getConfig().set("bounceblocks." + x + "," + y + "," + z + ".jumpStrength", bounceBlock.getJumpStrength());
        plugin.saveConfig();
        
        bounceBlocks.add(bounceBlock);
    }
    
    public BounceBlock getBounceBlock(Block block) {
        for(BounceBlock bounceBlock : bounceBlocks) {
            if(bounceBlock.getBlock().equals(block))
                return bounceBlock;
        }
        
        return null;
    }
    
    public List<BounceBlock> getBounceBlocks() {
        return bounceBlocks;
    }
    
    public boolean removeBlock(Block block) {
        for(BounceBlock bounceBlock : bounceBlocks)
            if(block.equals(bounceBlock.getBlock())) {
                bounceBlocks.remove(bounceBlock);
                
                int x = bounceBlock.getBlock().getX();
                int y = bounceBlock.getBlock().getY();
                int z = bounceBlock.getBlock().getZ();
                
                plugin.getConfig().set("bounceblocks." + x + "," + y + "," + z, null);
                plugin.saveConfig();
                
                return true;
            }
        
        return false;
    }
}
