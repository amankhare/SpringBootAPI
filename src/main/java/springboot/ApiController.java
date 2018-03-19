package springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping(path="/api")
public class ApiController {
	
	@Autowired
	private ApiRepository apiRepository;
	
	@Autowired
	private DaoApiFunctions daoApiFunctions; 

	@GetMapping(path="/all")
	public @ResponseBody Iterable<ApiSet> getAllData() {
		return apiRepository.findAll();
	}
	
	@PostMapping(path = "/add")
	public ResponseEntity<ApiListModel> addApiData(@RequestBody ApiListModel request)
	{
    	for(ApiSet temp:request.getApiSet())
    		apiRepository.save(temp);
    	return ResponseEntity.ok().body(request);
	}
	
	@GetMapping(path = "/{app_index}")
	public ResponseEntity<ApiListModel> findById(@PathVariable(value="app_index") long app_index)
	{	
		List<ApiSet> index=new ArrayList<>(); 
		ApiListModel aa=new ApiListModel();
		index.add(apiRepository.findOne(app_index));
		aa.setApiSet(index);
		return ResponseEntity.ok().body(aa);
		
	}
	
	@PostMapping(path = "/delete/{app_index}")
	public ResponseEntity deleteApiData(@PathVariable(value="app_index") long app_index)
	{
		if(apiRepository.exists(app_index))
		{
			apiRepository.delete(app_index);
			return ResponseEntity.ok().body(null);
		}
		else
			return ResponseEntity.badRequest().body("Validation Error!!");
	}
	
}
