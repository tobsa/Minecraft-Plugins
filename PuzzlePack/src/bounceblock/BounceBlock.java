package bounceblock;

import org.bukkit.block.Block;

public class BounceBlock {
    private Block block;
    private double jumpStrength;
    
    public BounceBlock(Block block, double jumpStrength) {
        this.block = block;
        this.jumpStrength = jumpStrength;
    }
        
    public Block getBlock() {
        return block;
    }
    
    public double getJumpStrength() {
        return jumpStrength;
    }
    
    public void setJumpStrength(double jumpStrength) {
        this.jumpStrength = jumpStrength;
    }
}
