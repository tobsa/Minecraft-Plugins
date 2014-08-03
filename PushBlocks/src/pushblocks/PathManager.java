package pushblocks;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.block.Block;

public class PathManager {
    private List<Path> paths = new ArrayList();
    
    public void addPath(Path path) {
        paths.add(path);
    }
    
    public boolean isValid(Block minimumBlock, Block maximumBlock) {
        for(Path p : paths) {
            boolean isMinimumInvalid = minimumBlock.equals(p.getMinimumBlock()) || minimumBlock.equals(p.getMaximumBlock()) || minimumBlock.equals(p.getActiveBlock()); 
            boolean isMaximumInvalid = maximumBlock.equals(p.getMinimumBlock()) || maximumBlock.equals(p.getMaximumBlock()) || maximumBlock.equals(p.getActiveBlock());
            
            if(isMinimumInvalid || isMaximumInvalid)
                return false;
        }
        
        return true;
    }
    
    public Path getPath(Block block) {
        for(Path path : paths)
            if(path.getActiveBlock().equals(block))
                return path;
        
        return null;
    }
    
    public boolean removePath(Path path) {
        return paths.remove(path);
    }
    
    public List<Path> getPaths() {
        return paths;
    }
}
