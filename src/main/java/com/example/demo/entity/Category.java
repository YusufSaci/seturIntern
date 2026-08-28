
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "category_name")
    private String categoryName;


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "category",
        cascade = {
        CascadeType.PERSIST,
        CascadeType.MERGE
        }
    )
    private List<Product> products;

    // constructor
    public Category() {}

    public Category(String categoryName){
        this.categoryName = categoryName;
       
    }

    // getter ve setter
     
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getCategoryName(){
        return categoryName;
    }

    public void setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }

    public List<Product> getProducts(){
        return products;
    }

    public void setProducts(List<Product> products){
        this.products = products;
    }

    public void addProduct(Product product){
    
        if(products == null){
            products = new ArrayList<>();
        }
        products.add(product);
        product.setCategory(this);
    }

   

}