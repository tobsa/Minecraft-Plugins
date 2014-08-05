package interactblocks;

import java.io.Serializable;
import org.bukkit.Bukkit;

public class SerializedInteractBlock implements Serializable {
    private int x;
    private int y;
    private int z;
    private SerializedInteractResponse response;
    
    public SerializedInteractBlock(InteractBlock block) {
        this.x = block.getBlock().getX();
        this.y = block.getBlock().getY();
        this.z = block.getBlock().getZ();
        this.response = block.getSerializedResponse();
    }
    
    public InteractBlock getInteractBlock() {
        return new InteractBlock(Bukkit.getWorld("world").getBlockAt(x, y, z), response.getResponse());
    }
}
