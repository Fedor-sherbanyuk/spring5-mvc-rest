package guru.springfamework.api.v1.repositories;

import guru.springfamework.api.v1.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by jt on 9/24/17.
 */
@Repository
public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Vendor findByname(String name);
}
