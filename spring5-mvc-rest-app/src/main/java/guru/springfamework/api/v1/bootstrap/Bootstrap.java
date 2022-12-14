package guru.springfamework.api.v1.bootstrap;

import guru.springfamework.api.v1.domain.Category;
import guru.springfamework.api.v1.domain.Customer;
import guru.springfamework.api.v1.domain.Vendor;
import guru.springfamework.api.v1.repositories.CategoryRepository;
import guru.springfamework.api.v1.repositories.CustomerRepository;
import guru.springfamework.api.v1.repositories.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;


    public Bootstrap(CategoryRepository categoryRepository,
                     CustomerRepository customerRepository,
                     VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        Customer fedor =new Customer();
        fedor.setFirstname("Fedor");
        fedor.setLastname("Sherbanyuk");

        Customer vova =new Customer();
        vova.setFirstname("Vladimir");
        vova.setLastname("Sherbanyuk");

        Customer sergei =new Customer();
        sergei.setFirstname("Sergei");
        sergei.setLastname("Melanin");

        Vendor vovaNew =new Vendor();
        vovaNew.setName("vovaNew");

        Vendor fedorNew =new Vendor();
        fedorNew.setName("fedorNew");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        customerRepository.save(fedor);
        customerRepository.save(vova);
        customerRepository.save(sergei);

        vendorRepository.save(vovaNew);
        vendorRepository.save(fedorNew);

        System.out.println("Data Category Loaded = " + categoryRepository.count());
        System.out.println("Data Customer Loaded = " + customerRepository.count());
        System.out.println("Data Vendor Loaded = " + vendorRepository.count());
    }
}