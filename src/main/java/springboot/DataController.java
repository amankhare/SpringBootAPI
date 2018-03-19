package springboot;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping(path="/data") 
public class DataController{
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DaoDataFunctions daoFunctions; 

	@GetMapping(path="/print")
	public @ResponseBody String pp()
	{
		return "Yo";
	}
	
	@GetMapping(path="/{domain}")
	public ResponseEntity<DataListModel> getDataByType(@PathVariable(value="domain") String domain,@RequestParam("type")String type)
	{
		DataListModel aa=new DataListModel();
		aa.setDataSet(daoFunctions.findUsers(type,domain));
		return ResponseEntity.ok().body(aa);
	}
	

    @PostMapping(path = "/add")
	public ResponseEntity<DataListModel> addUser(@RequestBody DataListModel request)
	{
    	for(DataSet temp:request.getDataSet())
    		userRepository.save(temp);
    	return ResponseEntity.ok().body(request);
	}
    
    @PostMapping(path = "/update")
	public ResponseEntity<DataListModel> updateUser(@RequestBody DataListModel request)
	{
    	for(DataSet temp:request.getDataSet())
    		daoFunctions.updateData(temp);
    	return ResponseEntity.ok().body(request);
	}
    
	@GetMapping(path="/all")
	public @ResponseBody Iterable<DataSet> getAllData() {
		return userRepository.findAll();
	}
}