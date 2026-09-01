
@Component
public class CategoryMapper{

    private ProductMapper productMapper;

    public CategoryMapper(ProductMapper productMapper){
        this.productMapper = productMapper;
    }


    public CategoryDto toDto(Category category){

        List<ProductDto> productsDto = category.getProducts.stream()
                        .map(product -> productMapper.toDto(product)).toList();

        return new CategoryDto(
            category.getId(),,
            category.getCategoryName(),
            product.getCategory.getCategoryName(),
            productsDto

        );
    }

    public Category toEntity(CategoryDto categoryDto){

        Category category = new Category( categoryDto.categoryName());
        return  category;
       


    }

}