package vg.mathias.delacruz.hackathon.project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String surname;

    @Column(name = "type_doc", length = 3)
    private String typeDoc;

    @Column(name = "doc_numb", length = 20, unique = true)
    private String docNumb;

    private String pais;
    private String address;

    @Column(length = 1500)
    private String description;

    @Column(name = "is_active")
    private Boolean isActive = true;

    /** Auditoría */
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
    private LocalDateTime restoredAt;
}
