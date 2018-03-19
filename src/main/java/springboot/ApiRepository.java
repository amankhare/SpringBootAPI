package springboot;

import org.springframework.data.repository.CrudRepository;


	public interface ApiRepository extends CrudRepository<ApiSet, Long> {
		
		
	}
