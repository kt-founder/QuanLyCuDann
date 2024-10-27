package Controller;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repositories.ServiceService;
import com.example.model.Service;


@RestController
@RequestMapping("/api")

public class ServiceRestApiController {
public static Logger logger = LoggerFactory.getLogger(CondominumRestApiController.class);
	
	@Autowired
	ServiceService serviceService;
	
	@RequestMapping(value = "/service/", method = RequestMethod.GET)
	public ResponseEntity<List<Service>> listAllService(){
		List<Service> listService= serviceService.findAll();
		if(listService.isEmpty()) {
			return new ResponseEntity<List<Service>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Service>>(listService, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/service/{id}", method = RequestMethod.GET)
	public Service findService(@PathVariable("id") long id) {
		@SuppressWarnings("deprecation")
		Service service= serviceService.getOne(id);
		if(service == null) {
			ResponseEntity.notFound().build();
		}
		return service;
	}
	
	@RequestMapping(value = "/service/", method = RequestMethod.POST)
	public Service saveService(@Valid @RequestBody Service service) {
		return serviceService.save(service);
	}
	
	@RequestMapping(value = "/service/", method = RequestMethod.PUT)
	public ResponseEntity<Service> updateService(@PathVariable(value = "id") Long serviceId, 
	                                       @Valid @RequestBody Service serviceForm) {
		@SuppressWarnings("deprecation")
		Service service = serviceService.getOne(serviceId);
	    if(service == null) {
	        return ResponseEntity.notFound().build();
	    }
	    service.setName(serviceForm.getName());
	    service.setUnit(serviceForm.getUnit());
	    service.setPrice(serviceForm.getPrice());

	    Service updatedService = serviceService.save(service);
	    return ResponseEntity.ok(updatedService);
	}
	
	@RequestMapping(value = "/service/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Service> deleteService(@PathVariable(value = "id") Long id) {
		@SuppressWarnings("deprecation")
		Service service = serviceService.getOne(id);
	    if(service == null) {
	        return ResponseEntity.notFound().build();
	    }

	    serviceService.delete(service);
	    return ResponseEntity.ok().build();
	}
}
