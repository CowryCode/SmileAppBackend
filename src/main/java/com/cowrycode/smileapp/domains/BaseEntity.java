package com.cowrycode.smileapp.domains;

        import lombok.Data;

        import javax.persistence.GeneratedValue;
        import javax.persistence.GenerationType;
        import javax.persistence.Id;
        import javax.persistence.MappedSuperclass;
        import java.io.Serializable;
        import java.time.LocalDateTime;

@Data
@MappedSuperclass
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
