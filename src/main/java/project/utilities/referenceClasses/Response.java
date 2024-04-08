package project.utilities.referenceClasses;

import java.io.Serializable;

/**
 * Represents a response from an operation, containing a payload and a success indicator.
 *
 * @param <T> The type of the payload.
 */
public class Response<T> implements Serializable {

    private final T payload;
    private final boolean isSuccess;

    /**
     * Constructs a Response object with the specified payload and success indicator.
     *
     * @param isSuccess Indicates whether the operation was successful.
     * @param payload   The payload of the response.
     */
    public Response(boolean isSuccess, T payload) {
        this.isSuccess = isSuccess;
        this.payload = payload;
    }

    /**
     * Gets the payload of the response.
     *
     * @return The payload of the response.
     */
    public T getPayload() {
        return payload;
    }

    /**
     * Checks if the operation was successful.
     *
     * @return true if the operation was successful, false otherwise.
     */
    public boolean isSuccess() {
        return isSuccess;
    }
}
