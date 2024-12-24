package az.ingress.dao.repository;

import az.ingress.dao.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductRepository extends JpaRepository<ProductEntity,Long> , JpaSpecificationExecutor<ProductEntity> {
}
