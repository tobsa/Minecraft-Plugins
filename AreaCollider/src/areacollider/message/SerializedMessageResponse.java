package areacollider.message;

import areacollider.CollisionResponse;
import areacollider.SerializedCollisionResponse;

public class SerializedMessageResponse implements SerializedCollisionResponse {
    private String message;
    
    public SerializedMessageResponse(String message) {
        this.message = message;
    }

    @Override
    public CollisionResponse getCollisionResponse() {
        return new MessageResponse(message);
    }
    
}
