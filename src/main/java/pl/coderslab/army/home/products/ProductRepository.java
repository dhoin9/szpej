package pl.coderslab.army.home.products;

import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface ProductRepository extends JpaRepository<Book, Long> {

    public Book getById(long id);
}


