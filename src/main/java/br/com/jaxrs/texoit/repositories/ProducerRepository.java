package br.com.jaxrs.texoit.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.jaxrs.texoit.Main;
import br.com.jaxrs.texoit.entities.Interval;
import br.com.jaxrs.texoit.entities.Movie;
import br.com.jaxrs.texoit.entities.Producer;

public class ProducerRepository {
	
	public Interval getIntervals() throws Exception {
		Interval interval = new Interval();
		MovieRepository _movieRepository = new MovieRepository();
		try {
			List<Movie> winMovies = _movieRepository.getWinnerMovies(Main.movies);
			if (!winMovies.isEmpty()) {
				List<Producer> winProducers = getWinnerProducers(winMovies);
				if (!winProducers.isEmpty()) {
					int min = winProducers.stream()
		                    .mapToInt(p -> p.getInterval())
		                    .min()
		                    .orElse(0);
					
					int max = winProducers.stream()
		                    .mapToInt(p -> p.getInterval())
		                    .max()
		                    .orElse(0);
					
					if (max > 0) {
						interval.setMax(winProducers.stream()
								.filter(p -> p.getInterval() == max).collect(Collectors.toList()));
					}
					if (min > 0) {
						interval.setMin(winProducers.stream()
								.filter(p -> p.getInterval() == min).collect(Collectors.toList()));
					}
				}
			} 
			
			return interval;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	private List<Producer> getWinnerProducers(List<Movie> winMovies) throws Exception {
		List<Producer> winProducers = new ArrayList<Producer>();
		Producer mathProducer = null;
		Producer winProducer = null;

		for (Movie movie : winMovies) {
			for (Producer producer : movie.getProducers()) {
				
				Optional<Producer> matchingProducer = winProducers.stream().
					    filter(p -> p.getName().equals(producer.getName())).
					    findFirst();
				
				if (matchingProducer.isEmpty()) {
					winProducer = new Producer();
					winProducer.setName(producer.getName());
					winProducer.setPreviousWin(movie.getYear());
					
					winProducers.add(winProducer);
				} else {
					mathProducer = matchingProducer.get();
					if (mathProducer.getFollowWin() == null) {
						mathProducer.setInterval(movie.getYear() - mathProducer.getPreviousWin());
						mathProducer.setFollowWin(movie.getYear());
					} else {
						winProducer = new Producer();
						winProducer.setName(producer.getName());
						winProducer.setPreviousWin(mathProducer.getFollowWin());
						winProducer.setInterval(movie.getYear() - winProducer.getPreviousWin());
						winProducer.setFollowWin(movie.getYear());
						
						winProducers.add(winProducer);
					}
				}
			}
		}
		
		return winProducers.stream().filter(p -> p.getInterval() > 0).collect(Collectors.toList());
	}
	
	public boolean containsName(final List<Producer> list, final String name){
	    return list.stream().filter(o -> o.getName().equals(name)).findFirst().isPresent();
	}
}
