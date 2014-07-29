package toggleblocks;

import org.bukkit.Material;
import org.bukkit.block.Block;

public class ToggleBlock {
    private Block block;
    private Material material;
    
    public ToggleBlock(Block block) {
        this.block = block;
        this.material = block.getType();
    }
    
    public ToggleBlock(Block block, Material material) {
        this.block = block;
        this.material = material;
    }
    
    public Block getBlock() {
        return block;
    }
    
    public Material getMaterial() {
        return material;
    }
    
    public void toggle() {
        if(block.getType() == Material.AIR)
            toggleOn();
        else
            toggleOff();
    }
    
    public void toggleOn() {
        block.setType(material);
    }
    
    public void toggleOff() {
        block.setType(Material.AIR);
    }
}
