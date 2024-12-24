package az.ingress.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Builder
public class ProductImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageUrl; // Şəkilin URL-i
    private Integer imageSequence ;  // Şəkil sırası, hansı şəkil əvvəl görünəcək
    private Long size; // Şəkilin ölçüsü (KB, MB olaraq)

    @CreationTimestamp
    private LocalDateTime createdAt;  // Şəkilin yüklənmə tarixi

    @ManyToOne(
            fetch = LAZY,
            cascade = {ALL}
    )
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    private ProductEntity product;  // Hər şəkil bir məhsula aid olacaq
}
