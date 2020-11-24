package pl.coderslab.army.home.warehouse;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class JpaWarehouseService implements WarehouseService {

    private final WarehouseRepository repository;

    public JpaWarehouseService(WarehouseRepository repository) {
        this.repository = repository;
    }


    @Override
    public Warehouse get(Long id) {
        return repository.getOne(id);
    }

    @Override
    public List<Warehouse> getWarehouses() {
        return repository.findAll();
    }

    @Override
    public void add(Warehouse warehouse) {
        repository.save(warehouse);
    }

    @Override
    public void delete(Long id) {
    repository.deleteById(id);
    }

    @Override
    public void update(Warehouse warehouse) {
        repository.save(warehouse);

    }
}




