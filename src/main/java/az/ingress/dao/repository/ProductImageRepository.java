package az.ingress.dao.repository;

import az.ingress.dao.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImageEntity,Long> {
}
