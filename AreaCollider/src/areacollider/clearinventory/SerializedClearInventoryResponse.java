package areacollider.clearinventory;

import areacollider.CollisionResponse;
import areacollider.SerializedCollisionResponse;
import areacollider.clearinventory.ClearInventoryResponse;

public class SerializedClearInventoryResponse implements SerializedCollisionResponse {

    @Override
    public CollisionResponse getCollisionResponse() {
        return new ClearInventoryResponse();
    }
}
