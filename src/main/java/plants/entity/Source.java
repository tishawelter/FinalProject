package plants.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Source {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sourceId;
	
	private String sourceName;
	
	@OneToMany(mappedBy = "source", cascade = CascadeType.PERSIST)
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Set<Plant> plants = new HashSet<>();
	
}