package toggleblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;

public class Region {
    private List<ToggleBlock> blocks = new ArrayList();
    private String name;
    
    public Region(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void addBlock(ToggleBlock block) {
        blocks.add(block);
    }
    
    public boolean removeBlock(Block block) {
        for(ToggleBlock tblock : blocks) {
            if(tblock.getBlock().getLocation().equals(block.getLocation())) {
                blocks.remove(tblock);
                return true;
            }   
        }
        
        return false;
    }
    
    public void toggleOn() {
        for(ToggleBlock block : blocks)
            block.toggleOn();
    }
    
    public void toggleOff() {
        for(ToggleBlock block : blocks)
            block.toggleOff();
    }
}
