package br.com.jaxrs.texoit.repositories;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.jaxrs.texoit.entities.Movie;
import br.com.jaxrs.texoit.entities.Producer;
import jakarta.inject.Inject;

//@RequestScoped
public class MovieRepository {
	
	public List<Movie> getWinnerMovies(List<Movie> movies) throws Exception {
		List<Movie> winMovies = movies.stream()
				.filter(Movie::isWinner)
				.sorted(Comparator.comparingInt(Movie::getYear))
				.collect(Collectors.toList());
		return winMovies;
	}
	
	public List<Movie> loadMovies(Stream<String> records) throws Exception {
		try {
		    return records.skip(1).map(mapToItem).collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private static Function<String, Movie> mapToItem = (line) -> {
	    String[] record = line.split(";");
	    Movie movie = new Movie();
	    if (record[0] != null && record[0].trim().length() > 0) {
	        movie.setYear(Integer.parseInt(record[0]));
	    }
	    if (record[1] != null) {
	        movie.setTitle(record[1]);
	    }
	    if (record[2] != null) {
	        movie.setStudios(record[2]);
	    }
	    if (record[3] != null) {
	    	List<String> producers = Arrays.asList(record[3].toUpperCase().split(",\\s|(AND)\\s"));
	    	List<Producer> listProducers = producers.stream()
	    			.map(name -> new Producer(name))
	    			.collect(Collectors.toList());
	        movie.setProducers(listProducers);
	    }
    	if (record.length == 5 && record[4] != null && record[0].trim().length() > 0) {
	        movie.setWinner(true);
	    }
	    return movie;
	};
}
