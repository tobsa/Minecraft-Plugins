package soundblocks;

import org.bukkit.Sound;
import org.bukkit.block.Block;

public class SoundBlock {
    private Block block;
    private Sound sound;
    
    public SoundBlock(Block block, Sound sound) {
        this.block = block;
        this.sound = sound;
    }
    
    public Block getBlock() {
        return block;
    }
    
    public Sound getSound() {
        return sound;
    }
}
