package com.example.test;

import com.example.test.controller.HelloWorldController;
import com.example.test.service.HelloWorldService;
import com.smartapibox.plugin.PluginMetadata;
import com.smartapibox.plugin.PluginRegistrar;
import com.smartapibox.plugin.SmartApiPlugin;

import java.util.List;

/**
 * Example implementation of a SmartApiBox plugin.
 * <p>
 * This plugin exposes a simple REST API endpoint that returns a "Hello" message generated via GPT.
 * It demonstrates how to define plugin metadata, register components, and expose controllers
 * dynamically through the SmartApiBox platform.
 * </p>
 */
public class HelloWorldPlugin implements SmartApiPlugin {

    /**
     * Returns the metadata of this plugin.
     * <p>
     * The metadata is used by SmartApiBox to:
     * <ul>
     *   <li>Describe the plugin in the public catalog</li>
     *   <li>Display its name, description, version, and author</li>
     *   <li>Document the main exposed endpoint (path and HTTP method)</li>
     * </ul>
     * </p>
     *
     * @return the {@link PluginMetadata} describing this plugin
     */
    @Override
    public PluginMetadata getMetadata() {
        return new PluginMetadata(
                "HelloWorldPlugin",                   // Unique plugin name
                "A simple Hello World plugin",              // Short description
                "1.0.0",                                    // Plugin Version
                "Your Name",                                // Author or contributor
                "/api/v1/plugin/external/hello",            // Main endpoint path
                PluginMetadata.HttpMethod.GET               // HTTP method for the endpoint
        );
    }

    /**
     * Called by SmartApiBox when the plugin is loaded.
     * <p>
     * This method is the entry point for registering beans, controllers, or utility components.
     * All registered beans become part of the Spring context and can use dependency injection.
     * </p>
     *
     * @param registrar the {@link PluginRegistrar} provided by SmartApiBox to register components
     */
    @Override
    public void onLoad(final PluginRegistrar registrar) {
        registrar.registerBean(HelloWorldService.class);     // Service containing GPT logic
        registrar.registerController(HelloWorldController.class);  // Exposed REST controller
    }

    /**
     * Returns the list of REST controllers exposed by this plugin.
     * <p>
     * SmartApiBox uses this information to:
     * <ul>
     *   <li>Dynamically register the controller during plugin loading</li>
     *   <li>Automatically generate OpenAPI documentation for the endpoints</li>
     * </ul>
     * </p>
     * <p>
     * Controllers must be annotated with {@code @RestController} and use standard
     * Spring MVC mapping annotations such as {@code @RequestMapping} or {@code @GetMapping}.
     * </p>
     *
     * @return a list of controller classes exposed by the plugin
     */
    @Override
    public List<Class<?>> getRestControllers() {
        return List.of(HelloWorldController.class);
    }
}