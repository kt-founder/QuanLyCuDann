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

import com.example.Repositories.CondominumService;
import com.example.model.Condominum;


@RestController
@RequestMapping("/api")

public class CondominumRestApiController {
public static Logger logger = LoggerFactory.getLogger(CondominumRestApiController.class);
	
	@Autowired
	CondominumService condominumService;
	
	@RequestMapping(value = "/condominum/", method = RequestMethod.GET)
	public ResponseEntity<List<Condominum>> listAllCondominum(){
		List<Condominum> listCondominum= condominumService.findAll();
		if(listCondominum.isEmpty()) {
			return new ResponseEntity<List<Condominum>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Condominum>>(listCondominum, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/condominum/{id}", method = RequestMethod.GET)
	public Condominum findCondominum(@PathVariable("id") long id) {
		@SuppressWarnings("deprecation")
		Condominum condominum= condominumService.getOne(id);
		if(condominum == null) {
			ResponseEntity.notFound().build();
		}
		return condominum;
	}
	
	@RequestMapping(value = "/condominum/", method = RequestMethod.POST)
	public Condominum saveCondominum(@Valid @RequestBody Condominum condominum) {
		return condominumService.save(condominum);
	}
	
	@RequestMapping(value = "/condominum/", method = RequestMethod.PUT)
	public ResponseEntity<Condominum> updateCondominum(@PathVariable(value = "id") Long condominumId, 
	                                       @Valid @RequestBody Condominum condominumForm) {
		@SuppressWarnings("deprecation")
		Condominum condominum = condominumService.getOne(condominumId);
	    if(condominum == null) {
	        return ResponseEntity.notFound().build();
	    }
	    condominum.setNumber(condominumForm.getNumber());
	    condominum.setArea(condominumForm.getArea());
	    condominum.setPrice(condominumForm.getPrice());

	    Condominum updatedCondominum = condominumService.save(condominum);
	    return ResponseEntity.ok(updatedCondominum);
	}
	
	@RequestMapping(value = "/condominum/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Condominum> deleteCondominum(@PathVariable(value = "id") Long id) {
		@SuppressWarnings("deprecation")
		Condominum condominum = condominumService.getOne(id);
	    if(condominum == null) {
	        return ResponseEntity.notFound().build();
	    }

	    condominumService.delete(condominum);
	    return ResponseEntity.ok().build();
	}
}
