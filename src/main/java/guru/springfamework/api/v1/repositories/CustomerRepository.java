package guru.springfamework.api.v1.repositories;

import guru.springfamework.api.v1.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jt on 9/24/17.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByFirstname(String name);
    Customer findByLastname(String name);
}
