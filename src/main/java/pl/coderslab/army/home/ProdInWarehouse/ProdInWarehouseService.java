package pl.coderslab.army.home.ProdInWarehouse;

import java.util.List;

public interface ProdInWarehouseService {
    public ProdInWarehouse get(Long id);

    public List<ProdInWarehouse> getProdInWarehouses();

    public void add(ProdInWarehouse prodInWarehouse);

    public void delete(Long id);

    public void update(ProdInWarehouse prodInWarehouse);
}
