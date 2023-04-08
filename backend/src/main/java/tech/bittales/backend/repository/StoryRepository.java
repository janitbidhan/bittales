package tech.bittales.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.bittales.backend.model.Stories;

import java.util.List;

@Repository
public interface StoryRepository extends JpaRepository<Stories, Long> {
    List<Stories> findByGenre(String genre);
    List<Stories> findByUserCreated(Boolean userCreated);
}