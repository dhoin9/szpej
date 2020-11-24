package pl.coderslab.army.home.products;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public Product getById(long id);


    @Query(value ="SELECT DISTINCT name from product", nativeQuery = true)
    public List<String> findAllUniqueName();


}


