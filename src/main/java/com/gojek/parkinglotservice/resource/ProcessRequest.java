package com.gojek.parkinglotservice.resource;

/**
 * The interface Process request.
 */
public interface ProcessRequest {

    /**
     * Is valid request boolean.
     *
     * @param request the request
     * @return the boolean
     */
    boolean isValidRequest(String request);

    /**
     * Execute request.
     *
     * @param request the request
     * @throws Exception the exception
     */
    void executeRequest(String request) throws Exception;

}
