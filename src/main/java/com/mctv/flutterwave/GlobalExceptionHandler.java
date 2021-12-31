package com.mctv.flutterwave;

import com.mctv.flutterwave.controllers.ConfirmationController;
import feign.FeignException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import static com.mctv.flutterwave.utils.URLs.MICROSERVICE_URL;

/**
 * A class to handle exceptions thrown by the microservice.
 */
@RestControllerAdvice(assignableTypes = ConfirmationController.class)
public class GlobalExceptionHandler {
    /**
     * @param e        Feign exception
     * @param response HttpServletResponse
     * @return returns the error page view with the specified error message
     *         <p>
     *         This method handles all bad request(400) exceptions thrown by the
     *         confirmation controller.
     */
    @ExceptionHandler(FeignException.BadRequest.class)
    public ModelAndView handleFeignException(FeignException e, HttpServletResponse response) {
        response.setStatus(200);
        String status;
        String message;
        JSONObject res = new JSONObject(e.contentUTF8());
        if (res.getString("status").isEmpty()) {
            status = "unspecified error";
        } else {
            status = res.getString("status");
        }
        if (res.getString("message").isEmpty()) {
            message = "unspecified message";
        } else {
            message = res.getString("message");
        }

        return new ModelAndView("redirect:" + MICROSERVICE_URL + "/error?status=" + status + "&message=" + message);

    }
}
