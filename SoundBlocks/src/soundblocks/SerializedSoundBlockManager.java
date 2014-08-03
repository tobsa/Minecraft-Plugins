package soundblocks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SerializedSoundBlockManager implements Serializable {
    private List<SerializedSoundBlock> serializedSoundBlocks = new ArrayList();

    public SerializedSoundBlockManager(SoundBlockManager soundBlockManager) {
        for(SoundBlock soundBlock : soundBlockManager.getSoundBlocks())
            serializedSoundBlocks.add(new SerializedSoundBlock(soundBlock));
    }
    
    public SoundBlockManager getSoundBlockManager() {
        SoundBlockManager soundBlockManager = new SoundBlockManager();
        
        for(SerializedSoundBlock serializedSoundBlock : serializedSoundBlocks)
            soundBlockManager.addSoundBlock(serializedSoundBlock.getSoundBlock());
        
        return soundBlockManager;
    }
}
