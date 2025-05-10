package plants.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import plants.entity.Plant;

public interface PlantDao extends JpaRepository<Plant, Long> {

}