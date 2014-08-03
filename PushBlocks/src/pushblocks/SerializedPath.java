package pushblocks;

import java.io.Serializable;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;

public class SerializedPath implements Serializable {
    private int minimumX;
    private int minimumY;
    private int minimumZ;
    private int maximumX;
    private int maximumY;
    private int maximumZ;
    private int activeX;
    private int activeY;
    private int activeZ;
    private boolean forward;
    
    public SerializedPath(Path path) {
        minimumX = path.getMinimumBlock().getX();
        minimumY = path.getMinimumBlock().getY();
        minimumZ = path.getMinimumBlock().getZ();
        
        maximumX = path.getMaximumBlock().getX();
        maximumY = path.getMaximumBlock().getY();
        maximumZ = path.getMaximumBlock().getZ();
        
        activeX  = path.getActiveBlock().getX();
        activeY  = path.getActiveBlock().getY();
        activeZ  = path.getActiveBlock().getZ();
        
        forward  = path.getForward();
    }
    
    public Path getPath() {
        Block minimumBlock = Bukkit.getWorld("world").getBlockAt(minimumX, minimumY, minimumZ);
        Block maximumBlock = Bukkit.getWorld("world").getBlockAt(maximumX, maximumY, maximumZ);
        Block activeBlock  = Bukkit.getWorld("world").getBlockAt(activeX, activeY, activeZ);
        
        return new Path(minimumBlock, maximumBlock, activeBlock, forward);     
    }
}
