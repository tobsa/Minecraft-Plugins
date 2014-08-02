package blockglider;

import org.bukkit.block.Block;

public class GlidePath {
    private Block minBlock;
    private Block maxBlock;
    private Direction direction;
    private Direction orientation;
    private double velocity;
    
    public Block getMinBlock() {
        return minBlock;
    }
    
    public Block getMaxBlock() {
        return maxBlock;
    }
    
    public Direction getDirection() {
        return direction;
    }
    
    public Direction getOrientation() {
        return orientation;
    }
    
    public double getVelocity() {
        return velocity;
    }
    
    public void setMinBlock(Block block) {
        minBlock = block;
    }
    
    public void setMaxBlock(Block block) {
        maxBlock = block;
    }
    
    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }
    
    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }
    
    public boolean intersects(Block block) {
        return  block.getX() >= minBlock.getX() && block.getX() <= maxBlock.getX() &&
                block.getY() >= minBlock.getY() && block.getY() <= maxBlock.getY() &&
                block.getZ() >= minBlock.getZ() && block.getZ() <= maxBlock.getZ();
    }
    
    
} 
