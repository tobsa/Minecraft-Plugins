package areacollider;

import areacollider.CollisionResponse;
import java.io.Serializable;

public interface SerializedCollisionResponse extends Serializable {
    public CollisionResponse getCollisionResponse();
}
