package az.ingress.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageableProductResponse<T> {
    private List<T> product;
    private Integer lastPageNumber;
    private Integer totalPageNumber;
    private boolean hasNextPage;

}
