package io.jenkins.plugins.tuleap_api.client.internals.exceptions;

import okhttp3.Response;

public class InvalidTuleapResponseException extends Exception {
    private Response response;

    public InvalidTuleapResponseException(Response response) {
        this.response = response;
    }

    @Override
    public String getMessage() {
        return "The response received from Tuleap is invalid: " + response.toString();
    }
}
