package softuni.mobilelele.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import softuni.mobilelele.domain.entities.enums.CategoryType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "models")
@Getter
@Setter
@NoArgsConstructor
public class Model extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String name;
    @Column
    @Enumerated(EnumType.STRING)
    private CategoryType category;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "start_year")
    private Integer startYear;
    @Column(name = "end_year")
    private Integer endYear;
    @Column
    private LocalDateTime created;
    @Column
    private LocalDateTime modified;
    @ManyToOne
    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    private Brand brand;
    @OneToMany(mappedBy = "model", fetch = FetchType.EAGER)
    private Set<Offer> offers;
}