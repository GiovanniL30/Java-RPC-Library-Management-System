package project.utilities.referenceClasses;

import project.utilities.utilityClasses.TypeObject;

public class Response<T> {

    private T payload;
    private boolean isSuccess;

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
