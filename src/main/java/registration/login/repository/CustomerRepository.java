package registration.login.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import registration.login.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByUsername(String username);

}
