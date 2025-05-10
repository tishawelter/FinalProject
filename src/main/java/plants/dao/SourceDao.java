package plants.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import plants.entity.Source;

public interface SourceDao extends JpaRepository<Source, Long> {

}
