package com.example.test;

import com.example.test.service.HelloWorldService;
import com.smartapibox.sdk.GPTClient;
import com.smartapibox.sdk.factory.GPTClientFactory;
import com.smartapibox.sdk.util.EnvLoader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Integration test for HelloWorldService using SmartApiBox GPTClient.
 * <p>
 * Works both in SANDBOX and LIVE environments.
 * Use GPTClientFactory.live("live_xxx") or GPTClientFactory.sandbox("test_xxx") for sandbox or live environment.
 * Requires a valid API key.
 * </p>
 */
@Disabled("Integration test - run manually")
public class HelloWorldServiceTest {

    @Test
    void sayHello_shouldReturnMyPluginResponse() {
        EnvLoader.load();
        final GPTClient gptClient = GPTClientFactory.live("live_00fd50be-ef6f-479f-b479-af33c9f06fee");
        final HelloWorldService service = new HelloWorldService(gptClient);
        final String result = service.sayHello();

        // Simple checks
        assertNotNull(result, "GPT response should not be null");
        assertTrue(result.length() > 0, "GPT response should not be empty");

        // Print output to console
        System.out.println(result);
    }
}