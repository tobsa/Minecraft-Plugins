package redstonecombiner;

import java.io.Serializable;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;

public class SerializedBlock implements Serializable {
    private int x;
    private int y;
    private int z;
    
    public SerializedBlock(Block block) {
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
    }
    
    public Block getBlock() {
        return Bukkit.getWorld("world").getBlockAt(x, y, z);
    }
}
