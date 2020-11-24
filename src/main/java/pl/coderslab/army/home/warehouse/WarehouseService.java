package pl.coderslab.army.home.warehouse;

import java.util.List;

public interface WarehouseService {
    public Warehouse get(Long id);

    public List<Warehouse> getWarehouses();

    public void add(Warehouse warehouse);

    public void delete(Long id);

    public void update(Warehouse warehouse);
}
