package catapultblocks;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

public class CatapultBlock {
    private Block block;
    private Block activeBlock;
    private Direction direction;
    private Vector velocity;
    
    public CatapultBlock(Block block, Direction direction, Vector velocity) {
        this.block = block;
        this.velocity = velocity;
        
        setDirection(direction);
    }
    
    public Block getBlock() {
        return block;
    }
    
    public Block getActiveBlock() {
        return activeBlock;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public Vector getVelocity() {
        return velocity;
    }
    
    public final void setDirection(Direction direction) {
        this.direction = direction;
        
        switch(direction) {
            case NORTH: activeBlock = block.getRelative(BlockFace.NORTH); break;
            case EAST:  activeBlock = block.getRelative(BlockFace.EAST);  break;
            case SOUTH: activeBlock = block.getRelative(BlockFace.SOUTH); break;
            case WEST:  activeBlock = block.getRelative(BlockFace.WEST);  break;
            case UP:    activeBlock = block.getRelative(BlockFace.UP);    break;
        }
    }
    
    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }
}
