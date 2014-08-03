package catapultblocks;

import java.io.Serializable;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;

public class SerializedCatapultBlock implements Serializable {    
    private int x;
    private int y;
    private int z;
    private Direction direction;
    private double vx;
    private double vy;
    private double vz;
    
    public SerializedCatapultBlock(CatapultBlock catapultBlock) {
        this.x = catapultBlock.getBlock().getX();
        this.y = catapultBlock.getBlock().getY();
        this.z = catapultBlock.getBlock().getZ();
        
        this.direction = catapultBlock.getDirection();
        
        this.vx = catapultBlock.getVelocity().getX();
        this.vy = catapultBlock.getVelocity().getY();
        this.vz = catapultBlock.getVelocity().getZ();
    }
    
    public CatapultBlock getCatapultBlock() {
        return new CatapultBlock(Bukkit.getWorld("world").getBlockAt(x, y, z), direction, new Vector(vx, vy, vz));
    }
}
