package me.melyukhov.SpringProject.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.melyukhov.SpringProject.DataBase.Data;
import me.melyukhov.SpringProject.kafka.DataServiceImp;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/recipes")
public class RestController {
	
	@Autowired
	private DataServiceImp dataServiceKafka;
	
	@RequestMapping("/")
	public Iterable<Data> getData() {
		return dataServiceKafka.findAll();
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<Object> sendRecipe(@RequestBody(required = true) Data data, @AuthenticationPrincipal Principal principal) {
		if(principal != null) {
			return ResponseEntity.ok(dataServiceKafka.save(data));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRecipe(@PathVariable(name = "id", required = true) int id, @AuthenticationPrincipal Principal principal) {
		if(principal != null) {
		if (dataServiceKafka.findById(id).isPresent()) {
			dataServiceKafka.deleteById(id);
			return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "text/plain; charset=utf-8").build();
		}
			return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Content-Type", "text/plain; charset=utf-8").build();
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Data> changeData(@PathVariable(name = "id", required = true) int id,
			@RequestBody(required = true) Data data, @AuthenticationPrincipal Principal principal) {
		if(principal != null) {
			if(dataServiceKafka.findById(id).isPresent()) {
				Data stored = data;
				stored.setId(id);
				dataServiceKafka.deleteById(id);
				dataServiceKafka.save(stored);
				return ResponseEntity.status(HttpStatus.OK).header("Content-Type", "text/plain; charset=utf-8").build();
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Content-Type", "text/plain; charset=utf-8").build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

	/*
	 * @RequestMapping(value = "/", method = RequestMethod.POST) public
	 * ResponseEntity<Object> sendRecipe(@RequestBody(required=true) Data data){
	 * DataContainer.getInstance().recipes.add(data); return
	 * ResponseEntity.status(HttpStatus.CREATED).build(); }
	 */

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) public
	 * ResponseEntity<Object> deleteRecipe(@PathVariable(name="id", required=true)
	 * int id){ ArrayList<Data> recipes = DataContainer.getInstance().recipes; if(id
	 * >= 0 && id <= recipes.size()){ recipes.remove(id); return
	 * ResponseEntity.status(HttpStatus.OK).build(); } return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); }
	 */

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.PUT) public
	 * ResponseEntity<Object> changeData(@PathVariable(name="id", required=true) int
	 * id, @RequestBody(required=true) Data data){ ArrayList<Data> recipes =
	 * DataContainer.getInstance().recipes; if(id>=0 && id<=recipes.size()){
	 * recipes.get(id).setRecipe(data.getRecipe());
	 * recipes.get(id).setFullName(data.getFullName());
	 * recipes.get(id).setNameOfTheDish(data.getNameOfTheDish()); return
	 * ResponseEntity.status(HttpStatus.OK).build(); } return
	 * ResponseEntity.status(HttpStatus.NOT_FOUND).build(); }
	 */
}
