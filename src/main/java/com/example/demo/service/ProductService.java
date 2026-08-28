public interface ProductService{

    void save(Product product);

    Product findById(long id);

    List<Product> findAll();

    void deleteById(long id);

   
}