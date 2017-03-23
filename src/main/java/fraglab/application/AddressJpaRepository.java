package fraglab.application;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepository extends JpaRepository<Address, String> {

}
