package com.mctv.flutterwave.feignclient;

import com.mctv.flutterwave.FeignLoggingConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

import static com.mctv.flutterwave.utils.URLs.BASE_URL;

/**
 * The Api service proxy. This class is used to make the calls to the API.
 */
@FeignClient(name = "backend-api-service", url = BASE_URL, configuration = FeignLoggingConfiguration.class)
public interface ApiServiceProxy extends ApiService {
}
