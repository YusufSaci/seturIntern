public interface CategoryService{

    void save(Category category);

    Category findById(long id);

    List<Category> findAll();

    void deleteById(long id);

    
}