package soundblocks;

import puzzlepack.BaseFileManager;

public class FileManager extends BaseFileManager {
    
    public static SoundBlockManager load() {
        SerializedSoundBlockManager serializedSoundBlockManager = (SerializedSoundBlockManager)loadObject("plugins/SoundBlocks/soundblocks.dat");
        
        if(serializedSoundBlockManager == null)
            return new SoundBlockManager();
        
        return serializedSoundBlockManager.getSoundBlockManager();
    }
    
    public static void save(SoundBlockManager soundBlockManager) {
        if(!isDirectory("plugins/SoundBlocks"))
            makeDirectory("plugins/SoundBlocks");
                
        saveObject(new SerializedSoundBlockManager(soundBlockManager), "plugins/SoundBlocks/soundblocks.dat");
    }
}
