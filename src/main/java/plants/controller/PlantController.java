package plants.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import plants.controller.model.PlantData;
import plants.controller.model.PlantLocation;
import plants.controller.model.PlantSource;
import plants.service.PlantService;

@RestController
@RequestMapping("/plants")
@Slf4j
public class PlantController {

	@Autowired
	PlantService plantService;
	
	@PostMapping("/source")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PlantSource createSource(@RequestBody PlantSource plantSource) {
		log.info("Creating source {}", plantSource);
		return plantService.saveSource(plantSource);
	}
	
	@GetMapping("/source/{sourceId}")
	public PlantSource retrieveSourceById(@PathVariable Long sourceId) {
		log.info("Retrieving source with ID {}", sourceId);
		return plantService.retrieveSourceById(sourceId);
	}
	
	@GetMapping("/source")
	public List<PlantSource> retrieveAllSources() {
		log.info("Retrieving all sources");
		return plantService.retrieveAllSources();
	}
	
	@DeleteMapping("/source/{sourceId}")
	public Map<String, String> deleteSource(@PathVariable Long sourceId) {
		log.info("Deleting source with ID ", sourceId);
		plantService.deleteSource(sourceId);
		return Map.of("message", "Source with ID " + sourceId + " was deleted successfully.");
	}
	
	//post or create method for the location table
	@PostMapping("/location")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PlantLocation createLocation(@RequestBody PlantLocation plantLocation) {
		//logs message indicating a location is being added
		log.info("Adding location {}", plantLocation);
		
		//calls the service layer to have the plant location object and returns the saved location
		return plantService.saveLocation(plantLocation);
	}
	
	//get or read method to retrieve location by a specific location Id
	@GetMapping("/location/{locationId}")
	public PlantLocation retrieveLocationById(@PathVariable Long locationId) {
		//logs message indicating a specific location with Id requested is being retrieved
		log.info("Retrieving location with ID {}", locationId);
		
		//calls the service layer to retrieve the location by its ID and returns the location object
		return plantService.retrieveLocationById(locationId);
	}
	
	//get or read method to retrieve all locations
	@GetMapping("/location")
	public List<PlantLocation> retrieveAllLocations() {
		//logs message indicating all locations are being retrieved
		log.info("Retrieving all locations");
		
		//calls the service layer to retrieve all locations and returns the list
		return plantService.retrieveAllLocations();
	}
	
	@DeleteMapping("/location/{locationId}")
	public Map<String, String> deleteLocation(@PathVariable Long locationId) {
		log.info("Deleting location with ID ", locationId);
		plantService.deleteLocation(locationId);
		
		return Map.of("message", "Location with ID " + locationId + " was deleted successfully.");
	}
	
	//post or create method for the plant table
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public PlantData createPlant(@RequestBody PlantData plantData) {
		//logs message indicating a plant is being added
		log.info("Creating plant {}", plantData);
		
		//calls the service layer to have the plant data object and returns the saved plant data
		return plantService.createPlant(plantData);
	}
	
	//get or read method for retrieving a plant by a specific id
	@GetMapping("/{plantId}")
	public PlantData retrievePlantById(@PathVariable Long plantId) {
		//logs message indicating a plant by a specific id is being retrieved
		log.info("Retrieving plant with ID " + plantId);
		
		//calls the service layer to retrieve a plant by its ID and returns the plant data object
		return plantService.retrievePlantById(plantId);
	}
	
	//get or read method for retrieving all plants
	@GetMapping
	public List<PlantData> retrieveAllPlants() {
		//logs message indicating all plants are being retrieved
		log.info("Retrieving all plants");
		
		//calls the service layer to retrieve all plants and returns the list
		return plantService.retrieveAllPlants();
	}
	
	//put or update method for a plant
	@PutMapping("/{plantId}")
	public PlantData updatePlant(@PathVariable Long plantId, @RequestBody PlantData plantData) {
		//logs message indicating a specific plant by ID is being updated
		log.info("Updating plant {}", plantData);
		
		//calls the service layer to update the plant with given ID and returns updated plant data object
		return plantService.updatePlant(plantId, plantData);
	}
	
	//deleting method for a plant by specific ID
	@DeleteMapping("/{plantId}")
	public Map<String, String> deletePlantById(@PathVariable Long plantId) {
		//logs message indicating a specific plant by ID is being deleted
		log.info("Deleting plant with ID ", plantId);
		
		//calls service layer to delete a specific plant by its ID
		plantService.deletePlantById(plantId);
		
		//returns message indicating the plant was successfully deleted from table
		return Map.of("message", "Plant with ID " + plantId + " was deleted successfully.");
	}

//	CODE IS WORKING ON
//	
//	@PutMapping("source/{sourceId}")
//	public PlantSource updateSource(@PathVariable Long sourceId, @RequestBody PlantSource plantSource) {
//		plantSource.setSourceId(sourceId);
//		log.info("Updating source {}", plantSource);
//		return plantService.saveSource(plantSource);
//	}
//	
//
//	@PutMapping("location/{locationId}")
//	public PlantLocation updateLocation(@PathVariable Long locationId, @RequestBody PlantLocation plantLocation) {
//		plantLocation.setLocationId(locationId);
//		log.info("Updating location {}", plantLocation);
//		return plantService.saveLocation(plantLocation);
//	}
//	

}
