package plants.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import plants.controller.model.PlantData;
import plants.controller.model.PlantLocation;
import plants.controller.model.PlantSource;
import plants.dao.LocationDao;
import plants.dao.PlantDao;
import plants.dao.SourceDao;
import plants.entity.Location;
import plants.entity.Plant;
import plants.entity.Source;

@Service
public class PlantService {

	@Autowired
	SourceDao sourceDao;
	
	@Autowired
	LocationDao locationDao;
	
	@Autowired
	PlantDao plantDao;
	
	@Transactional(readOnly = false)
	public PlantSource saveSource(PlantSource plantSource) {
		Long sourceId = plantSource.getSourceId();
		Source source = findOrCreateSource(sourceId);
		
		setSourceFields(source, plantSource);
	
		return new PlantSource(sourceDao.save(source));
	}
	
	private void setSourceFields(Source source, PlantSource plantSource) {
		source.setSourceName(plantSource.getSourceName());
	}
	
	private Source findOrCreateSource(Long sourceId) {
		if(Objects.isNull(sourceId)) {
			return new Source();
		} else {
			return findSourceById(sourceId);
		}
	}
	
	@Transactional(readOnly = true)
	public PlantSource retrieveSourceById(Long sourceId) {
		Source source = findSourceById(sourceId);
		return new PlantSource(source);
	}

	private Source findSourceById(Long sourceId) {
		return sourceDao.findById(sourceId).orElseThrow(()-> new NoSuchElementException("Source with ID " + sourceId + " was not found."));
	}
	
	@Transactional(readOnly = true)
	public List<PlantSource> retrieveAllSources() {
		List<Source> sources = sourceDao.findAll();
		List<PlantSource> result = new LinkedList<>();
		
		for(Source source : sources) {
			PlantSource plantSource = new PlantSource(source);
			result.add(plantSource);
		}
		return result;
	}

	@Transactional(readOnly = false)
	public void deleteSource(Long sourceId) {
		Source source = findSourceById(sourceId);
		sourceDao.delete(source);	
	}

	@Transactional
	public PlantLocation saveLocation(PlantLocation plantLocation) {
		Long locationId = plantLocation.getLocationId();
		Location location = findOrCreateLocation(locationId);
		
		setLocationFields(location, plantLocation);
		
		return new PlantLocation(locationDao.save(location));
	}
	
	private void setLocationFields(Location location, PlantLocation plantLocation) {
		location.setLocationName(plantLocation.getLocationName());
	}
	
	private Location findOrCreateLocation(Long locationId) {
		if (Objects.isNull(locationId)) {
			return new Location();
		} else {
			return findLocationById(locationId);
		}
	}
	
	@Transactional(readOnly = true)
	public PlantLocation retrieveLocationById(Long locationId) {
		Location location = findLocationById(locationId);
		return new PlantLocation(location);
	}
		
	private Location findLocationById(Long locationId) {
		return locationDao.findById(locationId).orElseThrow(
				() -> new NoSuchElementException("Location with ID " + locationId + " was not found."));
	}
		
	@Transactional(readOnly = true)
	public List<PlantLocation> retrieveAllLocations() {
		List<Location> locations = locationDao.findAll();
		List<PlantLocation> result = new LinkedList<>();
		
		for (Location location : locations) {
			PlantLocation plantLocation = new PlantLocation(location);
			result.add(plantLocation);
		}
		return result;
	}
	
	@Transactional(readOnly = false)
	public void deleteLocation(Long locationId) {
		Location location = findLocationById(locationId);
		locationDao.delete(location);	
	}

	public PlantData createPlant(PlantData plantData) {
        //create a new plant entity from the plant data
        Plant plant = new Plant();
        plant.setPlantName(plantData.getPlantName());
        plant.setFlowerColor(plantData.getFlowerColor());
        plant.setLight(plantData.getLight());

        //checks the source exists
        if (plantData.getSource() != null && plantData.getSource().getSourceId() != null) {
            // Fetch the Source entity from the database using the sourceId
            Source source = sourceDao.findById(plantData.getSource().getSourceId())
                    .orElseThrow(() -> new RuntimeException("Source with ID " + plantData.getSource().getSourceId() + " was not found."));
            plant.setSource(source);  // Set the source on the plant
        } else {
            throw new RuntimeException("Source is required and must be provided.");
        }

        //sets locations (many-to-many relationship)
        Set<Location> locations = new HashSet<>();
        for (PlantLocation plantLocation : plantData.getLocations()) {
            Location location = locationDao.findById(plantLocation.getLocationId())
                    .orElseThrow(() -> new RuntimeException("Location with ID " + plantLocation.getLocationId() + " was not found."));
            locations.add(location);
        }
        plant.setLocations(locations);

        //saves the plant entity
        Plant savedPlant = plantDao.save(plant);

        //returns a plant data object
        return new PlantData(savedPlant);
    }

	@Transactional
	public PlantData updatePlant(Long plantId, PlantData plantData) {
		//gets existing plant
		Plant plant = plantDao.findById(plantId)
			.orElseThrow(() -> new RuntimeException("Plant with ID " + plantId + " was not found."));

		//updates plant fields
		plant.setPlantName(plantData.getPlantName());
		plant.setFlowerColor(plantData.getFlowerColor());
		plant.setLight(plantData.getLight());

		//updates source
		if (plantData.getSource() != null && plantData.getSource().getSourceId() != null) {
			Source source = sourceDao.findById(plantData.getSource().getSourceId())
				.orElseThrow(() -> new RuntimeException("Source with ID " + plantData.getSource().getSourceId() + " was not found."));
			plant.setSource(source);
		} else {
			throw new RuntimeException("Source is required and must be provided.");
		}

		//updates locations
		Set<Location> updatedLocations = new HashSet<>();
		for (PlantLocation locData : plantData.getLocations()) {
			Location location = locationDao.findById(locData.getLocationId())
				.orElseThrow(() -> new RuntimeException("Location with ID " + locData.getLocationId() + " was not found."));
			updatedLocations.add(location);
		}
		plant.setLocations(updatedLocations);

		//saves changes
		Plant updatedPlant = plantDao.save(plant);
		return new PlantData(updatedPlant);
	}
	
	private Plant findPlantById(Long plantId) {
		return plantDao.findById(plantId).orElseThrow(() -> new NoSuchElementException("Plant with ID " + plantId + " was not found."));
	}
	
	@Transactional(readOnly = true)
	public PlantData retrievePlantById(Long plantId) {
		return new PlantData(findPlantById(plantId));
	}

	@Transactional(readOnly = true)
	public List<PlantData> retrieveAllPlants() {
		List<Plant> plants = plantDao.findAll();
		List<PlantData> result = new LinkedList<>();
		
		for(Plant plant : plants) {
			PlantData plantData = new PlantData(plant);
			
			result.add(plantData);
		}
		return result;
	}

	@Transactional(readOnly = false)
	public void deletePlantById(Long plantId) {
		Plant plant = findPlantById(plantId);
		plantDao.delete(plant);	
	}

}