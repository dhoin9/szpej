package pl.coderslab.army.home.ProdInWarehouse;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaProdInWarehouseService implements ProdInWarehouseService {

    private final ProdInWarehouseRepository repository;

    public JpaProdInWarehouseService(ProdInWarehouseRepository repository) {
        this.repository = repository;
    }


    @Override
    public ProdInWarehouse get(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<ProdInWarehouse> getProdInWarehouses() {
        return repository.findAll();
    }

    @Override
    public void add(ProdInWarehouse prodInWarehouse) {
        repository.save(prodInWarehouse);
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    @Override
    public void update(ProdInWarehouse prodInWarehouse) {
        repository.save(prodInWarehouse);

    }
}




