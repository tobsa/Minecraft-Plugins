package interactblocks;

import org.bukkit.block.Block;

public class InteractBlock {
    private Block block;
    private InteractResponse response;
    private SerializedInteractResponse serializedResponse;
    
    public InteractBlock(Block block, InteractResponse response) {
        this.block = block;
        this.response = response;
        this.serializedResponse = response.getSerializedResponse();
    }
    
    public Block getBlock() {
        return block;
    }
    
    public InteractResponse getResponse() {
        return response;
    }
    
    public SerializedInteractResponse getSerializedResponse() {
        return serializedResponse;
    }
}
