package main.java.tdd.tp;

import app.RulesValidatorApp;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan
public class Main implements WebMvcConfigurer {

    public static void main(String args[]) throws Exception {

        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(Main.class);

        Server server = new Server(8080);
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(webContext));
        servletContextHandler.addServlet(servletHolder, "/*");

        initializeRulesApp();

        server.setHandler(servletContextHandler);
        server.start();
        server.join();

    }

    public static void initializeRulesApp() {
        //This is done due to clojure interop internal issue with multiple class creation at the same time.
        RulesValidatorApp.getInstance();
    }
}
