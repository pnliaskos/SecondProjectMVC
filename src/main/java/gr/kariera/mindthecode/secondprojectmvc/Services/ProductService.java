package gr.kariera.mindthecode.secondprojectmvc.Services;


import gr.kariera.mindthecode.secondprojectmvc.Entities.Product;
import org.springframework.data.domain.Page;

public interface ProductService {
    public abstract Product createOrUpdateProduct(Integer id, Product product) throws Exception;
    public abstract void deleteProduct(Integer id);
    public abstract Page<Product> getProducts(
            String description,
            int page,
            int size,
            String sort
    );



    public abstract Product getById(Integer id);
}
