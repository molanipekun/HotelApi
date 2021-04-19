package registration.login.repository;

import java.io.Serializable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface CommonRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {


    @Transactional
    void delete(ID id);

    @Transactional
    void delete(Iterable<? extends T> entities);

    Page<T> findUsingPattern(String pattern, Pageable details);

}
