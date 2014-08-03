package pushblocks;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class Path {
    private Block minimumBlock;
    private Block maximumBlock;
    private Block activeBlock;
    private boolean forward = true;
    private Direction direction;

    private enum Direction {
        X, Y, Z, None,
    }
        
    public Path(Block minimumBlock, Block maximumBlock) {
        this.minimumBlock = minimumBlock;
        this.maximumBlock = maximumBlock;
        
        direction = getDirection();
               
        activeBlock = minimumBlock;
        activeBlock.setType(Material.WOOL);
        activeBlock.setData(DyeColor.BLACK.getData());
    }
    
    public Path(Block minimumBlock, Block maximumBlock, Block activeBlock, boolean forward) {
       this.minimumBlock = minimumBlock;        
       this.maximumBlock = maximumBlock;
       this.activeBlock  = activeBlock;
       this.forward = forward;
       
       direction = getDirection();
    }
    
    public Block getMinimumBlock() {
        return minimumBlock;
    }
    
    public Block getMaximumBlock() {
        return maximumBlock;
    }
    
    public Block getActiveBlock() {
        return activeBlock;
    }
    
    public boolean getForward() {
        return forward;
    }
        
    public boolean isBlockActive(Block block) {
            return isBlocksEqual(block, activeBlock);
    }
    
    private boolean isBlocksEqual(Block block1, Block block2) {
        return block1.getX() == block2.getX() && block1.getY() == block2.getY() && block1.getZ() == block2.getZ();
    }
    
    public void move() {
        if(activeBlock.equals(maximumBlock))
            forward = false;
        if(activeBlock.equals(minimumBlock))
            forward = true;
        
        Block nextBlock = getNextBlock(activeBlock, forward);
        if(nextBlock.getType() != Material.AIR)
            return;
                    
        activeBlock.setType(Material.AIR);
        activeBlock = nextBlock;
        activeBlock.setType(Material.WOOL);
        activeBlock.setData(DyeColor.BLACK.getData());
    }
    
    private Direction getDirection() {
        if(minimumBlock.getY() != maximumBlock.getY())
            return Direction.Y;
        if(minimumBlock.getX() == maximumBlock.getX())
            return Direction.Z;
        if(minimumBlock.getZ() == maximumBlock.getZ())
            return Direction.X;
        
        return Direction.None;
    }
    
    private Block getNextBlock(Block block, boolean forward) {
        if(direction == Direction.X)
            return block.getRelative(forward ? BlockFace.EAST : BlockFace.WEST);
        if(direction == Direction.Y)
            return block.getRelative(forward ? BlockFace.UP : BlockFace.DOWN);
        if(direction == Direction.Z)
            return block.getRelative(forward ? BlockFace.SOUTH : BlockFace.NORTH);
        
        return block;
    }
}
