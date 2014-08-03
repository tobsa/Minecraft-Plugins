package soundblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;

public class SoundBlockManager {
    private List<SoundBlock> soundBlocks = new ArrayList();
    
    public void addSoundBlock(SoundBlock soundBlock) {
        soundBlocks.add(soundBlock);
    }
    
    public boolean removeSoundBlock(SoundBlock soundBlock) {
        return soundBlocks.remove(soundBlock);
    }
    
    public SoundBlock getSoundBlock(Block block) {
        for(SoundBlock soundBlock : soundBlocks) {
            if(soundBlock.getBlock().equals(block))
                return soundBlock;
        }
        
        return null;
    }
    
    public List<SoundBlock> getSoundBlocks() {
        return soundBlocks;
    }
}
