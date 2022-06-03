package mancala;

import jakarta.ws.rs.core.UriBuilder;
import mancala.filters.CORSFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.net.URI;

public class App {

    public static void main(String[] args) throws Exception {
        Server server = startServer(8080);
        ServletContextHandler context = createStatefulContext();
        server.setHandler(context);

        server.start();
        server.join();
    }

    private static Server startServer(int port) {
        URI baseUri = UriBuilder.fromUri("http://localhost/").port(port).build();
        ResourceConfig config = new ResourceConfig().packages("mancala.api");
        config.register(CORSFilter.class);

        Server server = JettyHttpContainerFactory.createServer(baseUri, config, false);

        return server;
    }

    private static ServletContextHandler createStatefulContext() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        registerServlets(context);

        return context;
    }

    private static void registerServlets(ServletContextHandler context) {
        ResourceConfig config = new ResourceConfig().packages("mancala.api");
        config.register(CORSFilter.class);
        var container = new ServletContainer(config);

        ServletHolder serverHolder = new ServletHolder(container);
        context.addServlet(serverHolder, "/*");
        serverHolder.setInitOrder(1);
    }
}
