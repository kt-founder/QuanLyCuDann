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

import com.example.Repositories.ApartmentService;
import Model.Apartment;


@RestController
@RequestMapping("/apartment")	
public class ApartmentRestApiController {
public static Logger logger = LoggerFactory.getLogger(ApartmentRestApiController.class);
	
	@Autowired
	ApartmentService apartmentService;
	
	@RequestMapping(value = "/apartment/", method = RequestMethod.GET)
	public ResponseEntity<List<Apartment>> listAllApartment(){
		List<Apartment> listApartment= apartmentService.findAll();
		if(listApartment.isEmpty()) {
			return new ResponseEntity<List<Apartment>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Apartment>>(listApartment, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/apartment/{id}", method = RequestMethod.GET)
	public Apartment findApartment(@PathVariable("id") long id) {
		@SuppressWarnings("deprecation")
		Apartment apartment= apartmentService.getOne(id);
		if(apartment == null) {
			ResponseEntity.notFound().build();
		}
		return apartment;
	}
	
	@RequestMapping(value = "/apartment/", method = RequestMethod.POST)
	public Apartment saveApartment(@Valid @RequestBody Apartment apartment) {
		return apartmentService.save(apartment);
	}
	
	@RequestMapping(value = "/apartment/", method = RequestMethod.PUT)
	public ResponseEntity<Apartment> updateApartment(@PathVariable(value = "id") Long apartmentId, 
	                                       @Valid @RequestBody Apartment apartmentForm) {
		@SuppressWarnings("deprecation")
		Apartment apartment = apartmentService.getOne(apartmentId);
	    if(apartment == null) {
	        return ResponseEntity.notFound().build();
	    }
	    apartment.setName(apartmentForm.getName());
	    apartment.setAddress(apartmentForm.getAddress());

	    Apartment updatedApartment = apartmentService.save(apartment);
	    return ResponseEntity.ok(updatedApartment);
	}
	
	@RequestMapping(value = "/apartment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Apartment> deleteApartment(@PathVariable(value = "id") Long id) {
		@SuppressWarnings("deprecation")
		Apartment apartment = apartmentService.getOne(id);
	    if(apartment == null) {
	        return ResponseEntity.notFound().build();
	    }

	    apartmentService.delete(apartment);
	    return ResponseEntity.ok().build();
	}
}
