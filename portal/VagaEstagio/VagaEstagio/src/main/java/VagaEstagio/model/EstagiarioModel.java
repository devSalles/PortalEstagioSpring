package VagaEstagio.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tb__estagio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstagiarioModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String curso;

    @Column(nullable = false)
    private int periodo;

    @OneToOne(mappedBy = "estagiarioModel")
    private VagaModel vagaModel;
}
