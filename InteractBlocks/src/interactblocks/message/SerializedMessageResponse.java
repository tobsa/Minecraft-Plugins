package interactblocks.message;

import interactblocks.InteractResponse;
import interactblocks.SerializedInteractResponse;

public class SerializedMessageResponse implements SerializedInteractResponse{
    private String message;

    public SerializedMessageResponse(MessageResponse response) {
        this.message = response.getMessage();
    }
    
    @Override
    public InteractResponse getResponse() {
        return new MessageResponse(message);
    }
}
