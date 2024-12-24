package az.ingress.model.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCriteria {
    private String name;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private BigDecimal ratingFrom;
    private BigDecimal ratingTo;
}
