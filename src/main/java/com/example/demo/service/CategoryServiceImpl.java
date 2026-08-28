

@Service
public class CategoryServiceImpl implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Category save(Category category){
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(long id){
         Optional<Category> category = categoryRepository.findById(id);

         if(category.isPresent()){
            return category.get();
         }

         throw new RunTimeException("category not found.")
    }

    @Override
    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public  void deleteById(long id){
        Category category = findById(id);

        List<Product> products = category.getProducts();

        for(Product tempProduct : producst){
            tempProduct.setCategory(null);
        }
        
        categoryRepository.delete(category);
    }
}