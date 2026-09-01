

@Component
public class ProductMapper{


    public ProductDto toDto(Product product){
        return new ProductDto(
            product.getId(),,
            product.getProductName(),
            product.getPrice(),
            product.getCategory.getCategoryName()

        );
    }

    public Product toEntity(ProductDto productDto, Category category){
        Product product = new Product(productDto.productName(), productDto.price());
        product.setCategory(category);

        return product;

    }



}