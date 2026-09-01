public record CategoryrDto(
        Long id,
        String categoryName,
        List<ProductDto> products
) {}