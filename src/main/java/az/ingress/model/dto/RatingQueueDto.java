package az.ingress.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingQueueDto {
    private Long productId;
    private BigDecimal averageRating;
    private Integer voteCount;
}
