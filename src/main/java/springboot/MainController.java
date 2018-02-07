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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller  
@RequestMapping(path="/data") 
public class MainController{
	

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private DaoFunctions daoFunctions; 

	@GetMapping(path="/print")
	public @ResponseBody String pp()
	{
		return "Yo";
	}
	
	/*@GetMapping(path="/{id}")
	public ResponseEntity<DataSet> getDataById(@PathVariable(value="id") Long id)
	{
		DataSet data=userRepository.findOne(id);
		if(data == null)
		{
			 return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(data);
	}*/
	
	@GetMapping(path="/{domain}")
	public ResponseEntity<DataListModel> getDataByType(@PathVariable(value="domain") String domain,@RequestParam("type")String type)
	{
		System.out.println(type);
		System.out.println("aman");
		List<DataSet> a =new ArrayList<>();
		DataSet dd=new DataSet();
		dd.setId(12);
		dd.setDomain("ng");
		dd.setType("asdasd");
		dd.setValid(true);
		dd.setValue("asdasdasdasd");
		DataSet dd1=new DataSet();
		dd1.setId(12);
		dd1.setDomain("ng");
		dd1.setType("asdasd");
		dd1.setValid(true);
		dd1.setValue("asdasdasdasd");
		a.add(dd);
		a.add(dd1);
		DataListModel aa=new DataListModel();
		aa.setDataSet(daoFunctions.findUsers(type,domain));
		//aa.setDataSet(a);
		return ResponseEntity.ok().body(aa);
	}
	
	/*@GetMapping(path="/{domain}")
	public ResponseEntity<DataSet> getDataByType(@PathVariable(value="domain") String domain , @RequestParam("type") String type)
	{
		DataSet data[]=userRepository.findAll(val);
		if(data == null)
		{
			 return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.ok().body(data);
	}*/
	
//    @GetMapping(path="/add") 
	public @ResponseBody String addNewUser (@RequestParam String Type
			, @RequestParam boolean Valid, @RequestParam String Value,@RequestParam String Domain) {

		DataSet n = new DataSet();
		n.setType(Type);
		n.setValid(Valid);
		n.setValue(Value);
		System.out.println(Value);
		n.setDomain(Domain);
		userRepository.save(n);
		return "Saved";	
	}
    
    @PostMapping(path = "/add")
	public ResponseEntity<DataListModel> addUs(@RequestBody DataListModel request)
	{
    	//System.out.println(request.DataSet.get(0));
    	for(DataSet temp:request.getDataSet())
    		userRepository.save(temp);
    	return ResponseEntity.ok().body(request);
	}
    
	@GetMapping(path="/all")
	public @ResponseBody Iterable<DataSet> getAllData() {
		System.out.println("--------------------------------------------------------------");
		return userRepository.findAll();
	}
}