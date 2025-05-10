package plants.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import plants.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long> {

}
