package br.com.jaxrs.texoit.repositories;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.com.jaxrs.texoit.entities.Movie;
import br.com.jaxrs.texoit.entities.Producer;

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
		    return records.skip(1).map(mapToItem)
		    		.filter(Objects::nonNull)
		    		.collect(Collectors.toList());
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private static Function<String, Movie> mapToItem = (line) -> {
	    String[] record = line.split(";");
	    
	    if (record.length < 4) {
	    	System.out.println("Ignored - Invalid Line: " + line);
	    	return null;
	    }

	    Movie movie = new Movie();
	    if (record[0] != null && record[0].trim().length() > 0) {
	    	try {
	    		 movie.setYear(Integer.parseInt(record[0]));
			} catch (Exception e) {
				System.out.println("Ignored - Invalid Year: " + line);
		    	return null;
			}
	    }
	    if (record[1] != null) {
	        movie.setTitle(record[1]);
	    }
	    if (record[2] != null) {
	        movie.setStudios(record[2]);
	    }
	    if (record[3] != null) {
	    	try {
	    		List<String> producers = Arrays.asList(record[3].toUpperCase().split(",\\s|(AND)\\s"));
		    	List<Producer> listProducers = producers.stream()
		    			.map(name -> new Producer(name))
		    			.collect(Collectors.toList());
		        movie.setProducers(listProducers);
			} catch (Exception e) {
				System.out.println("Ignored - Invalid Producers: " + line);
			}
	    }
    	if (record.length == 5 && record[4] != null && record[0].trim().length() > 0) {
	        movie.setWinner(true);
	    }
	    return movie;
	};
}
