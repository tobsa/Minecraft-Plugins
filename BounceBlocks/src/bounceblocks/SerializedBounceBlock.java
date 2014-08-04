package bounceblocks;

import java.io.Serializable;
import org.bukkit.Bukkit;

public class SerializedBounceBlock implements Serializable {
    private int x;
    private int y;
    private int z;
    private double jumpStrength;
    
    public SerializedBounceBlock(BounceBlock bounceBlock) {
        this.x = bounceBlock.getBlock().getX();
        this.y = bounceBlock.getBlock().getY();
        this.z = bounceBlock.getBlock().getZ();
        this.jumpStrength = bounceBlock.getJumpStrength();
    }
    
    public BounceBlock getBounceBlock() {
        return new BounceBlock(Bukkit.getWorld("world").getBlockAt(x, y, z), jumpStrength);
    }
}
