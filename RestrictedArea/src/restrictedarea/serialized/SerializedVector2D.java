package restrictedarea.serialized;

import com.sk89q.worldedit.BlockVector2D;
import java.io.Serializable;

public class SerializedVector2D implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private double x;
    private double z;
    
    public SerializedVector2D(BlockVector2D vector) {
        this.x = vector.getX();
        this.z = vector.getZ();
    }
    
    public BlockVector2D getPoint() {
        return new BlockVector2D(x, z);
    }
}

