package az.ingress.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private String categoryId;
    private List<ProductImageInfo> productImageInfo;

}
