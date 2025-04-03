package io.github.tcsizmadia.herocoders;

import io.helidon.microprofile.server.Server;

/**
 * Main application class.
 */
public final class Main {

    /**
     * Cannot be instantiated.
     */
    private Main() {
    }

    /**
     * Application main entry point.
     * @param args command line arguments
     */
    public static void main(final String[] args) {
        // MicroProfile server will automatically discover and
        // register JAX-RS resources and other components
        Server server = Server.builder().build().start();
        
        System.out.println("Server started at: http://localhost:" + server.port());
        System.out.println("Endpoint available at: http://localhost:" + server.port() + "/api/heroes");
    }
}
