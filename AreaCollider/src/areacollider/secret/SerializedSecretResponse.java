package areacollider.secret;

import areacollider.SerializedCollisionResponse;
import areacollider.CollisionResponse;
import areacollider.secret.SecretResponse;

public class SerializedSecretResponse implements SerializedCollisionResponse {

    @Override
    public CollisionResponse getCollisionResponse() {
        return new SecretResponse();
    }
}
