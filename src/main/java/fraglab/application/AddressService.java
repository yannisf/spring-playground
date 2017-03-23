package fraglab.application;

import java.util.Optional;

public interface AddressService {

    Optional<Address> find(String id);

    Address save(Address address);

    void delete(String id);
}
