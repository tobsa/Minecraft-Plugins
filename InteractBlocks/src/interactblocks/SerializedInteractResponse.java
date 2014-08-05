package interactblocks;

import java.io.Serializable;

public interface SerializedInteractResponse extends Serializable {
    public InteractResponse getResponse();
}
