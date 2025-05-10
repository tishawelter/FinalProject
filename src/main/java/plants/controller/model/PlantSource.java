package plants.controller.model;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;
import plants.entity.Plant;
import plants.entity.Source;

@Data
@NoArgsConstructor
public class PlantSource {

	private Long sourceId;
	private String sourceName;
	
	public PlantSource(Source source) {
		this.sourceId = source.getSourceId();
		this.sourceName = source.getSourceName();
		
	}
}