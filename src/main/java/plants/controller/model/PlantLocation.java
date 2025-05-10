package plants.controller.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import plants.entity.Location;

@Data
@NoArgsConstructor
public class PlantLocation {

	private Long locationId;
	private String locationName;
		
	public PlantLocation(Location location) {
		locationId = location.getLocationId();
		locationName = location.getLocationName();
	}

}