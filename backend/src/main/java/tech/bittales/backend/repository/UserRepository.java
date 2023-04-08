package tech.bittales.backend.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tech.bittales.backend.model.User;

@Repository
@Qualifier("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {
}

