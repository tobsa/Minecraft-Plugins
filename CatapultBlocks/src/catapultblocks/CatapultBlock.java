package catapultblocks;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class CatapultBlock {
    private Block block;
    private Direction direction;
    private double forwardVelocity;
    private double upwardVelocity;
    
    public CatapultBlock(Block block, Direction direction, double forwardVelocity, double upwardVelocity) {
        this.block = block;
        this.direction = direction;
        this.forwardVelocity = forwardVelocity;
        this.upwardVelocity = upwardVelocity;
    }
    
    public Block getBlock() {
        return block;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public Block getActiveBlock() {
        switch(direction) {
            case NORTH: return block.getRelative(BlockFace.NORTH);
            case EAST:  return block.getRelative(BlockFace.EAST);   
            case SOUTH: return block.getRelative(BlockFace.SOUTH);
            case WEST:  return block.getRelative(BlockFace.WEST);
        }
        
        return null;
    }
    
    public double getForwardVelocity() {
        return forwardVelocity;
    }
    
    public double getUpwardVelocity() {
        return upwardVelocity;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void setForwardVelocity(double forwardVelocity) {
        this.forwardVelocity = forwardVelocity;
    }
    
    public void setUpwardVelocity(double upwardVelocity) {
        this.upwardVelocity = upwardVelocity;
    }
}
