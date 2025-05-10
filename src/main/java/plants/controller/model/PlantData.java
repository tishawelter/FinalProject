
package plants.controller.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;
import plants.entity.Location;
import plants.entity.Plant;
import plants.entity.Source;

@Data
@NoArgsConstructor
public class PlantData {
	
	private Long plantId;
	private String plantName;
	private String flowerColor;
	private String light;
	private Set<PlantLocation> locations = new HashSet<>();
	private PlantSource source;
		
	public PlantData(Plant plant) {
		this.plantId = plant.getPlantId();
		this.plantName = plant.getPlantName();
		this.flowerColor = plant.getFlowerColor();
		this.light = plant.getLight();
		
		for (Location location : plant.getLocations()) {
			locations.add(new PlantLocation(location));
         }

        if (plant.getSource() !=null) {
            this.source = new PlantSource(plant.getSource());
        }
	}
}