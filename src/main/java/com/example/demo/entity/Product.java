
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // constructor
    public Product() {}

    public Product(String productName, int price){
        this.productName = productName;
        this.price = price;

    }

    // getter ve setter
     
    public long getId(){
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getProductName(){
        return productName;
    }

    public void setProductName(String productName){
        this.productName = productName;
    }
    

    public int getPrice(){
        return  price;
    }

    public void setPrice(int price){
        this.price=price;
    }

    public Category getCategory(){
        return category
    }

    public void setCategory(Category category){
        this.category = category;
    }



}