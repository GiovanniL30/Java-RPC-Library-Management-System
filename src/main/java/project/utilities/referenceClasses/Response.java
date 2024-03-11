package project.utilities.referenceClasses;

import java.io.Serializable;

public class Response<T> implements Serializable {

    private final T payload;
    private final boolean isSuccess;

    public Response(boolean isSuccess, T payload) {
        this.isSuccess = isSuccess;
        this.payload = payload;
    }

    public T getPayload() {
        return payload;
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
