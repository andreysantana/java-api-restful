package br.com.jaxrs.texoit;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import br.com.jaxrs.texoit.entities.Movie;
import br.com.jaxrs.texoit.repositories.MovieRepository;
import br.com.jaxrs.texoit.utils.ApplicationBinder;
import br.com.jaxrs.texoit.utils.FileUtils;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/texoit/";
    public static List<Movie> movies = new ArrayList<Movie>();
    public static final String filePath = "src/movielist.csv";

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        final ResourceConfig rc = new ResourceConfig().packages("br.com.jaxrs.texoit");
        rc.register(new ApplicationBinder());
        
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        
        try {
        	MovieRepository _mRepository = new MovieRepository();
            Stream<String> lines = FileUtils.loadCSV();
            if (lines != null) 
            	movies = _mRepository.loadMovies(lines);
            
            System.out.println("All done!");
            System.in.read();
            server.shutdownNow();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			server.shutdownNow();
		}
    }
}

