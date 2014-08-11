package areacollider.bigmessage;

import areacollider.CollisionResponse;
import areacollider.SerializedCollisionResponse;

public class SerializedBigMessageResponse implements SerializedCollisionResponse {
    private String message;
    
    public SerializedBigMessageResponse(String message) {
        this.message = message;
    }

    @Override
    public CollisionResponse getCollisionResponse() {
        return new BigMessageResponse();
    }
    
}
