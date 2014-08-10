package toggleblocks;

import java.io.Serializable;
import org.bukkit.block.Block;

public class SerializedBlock implements Serializable {
    private static final long serialVersionUID = 2420914920403085039L;
    
    private int x;
    private int y;
    private int z;
    private String material;
    
    public SerializedBlock(Block block) {
        this.x = block.getX();
        this.y = block.getY();
        this.z = block.getZ();
        this.material = block.getType().toString();
    }
    
    public SerializedBlock(ToggleBlock toggleBlock) {
        this.x = toggleBlock.getBlock().getX();
        this.y = toggleBlock.getBlock().getY();
        this.z = toggleBlock.getBlock().getZ();
        this.material = toggleBlock.getMaterial().toString();
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getZ() {
        return z;
    }
    
    public String getMaterial() {
        return material;
    }
    
    public boolean isBlock(Block block) {
        return x == block.getX() && y == block.getY() && z == block.getZ();
    }
}
