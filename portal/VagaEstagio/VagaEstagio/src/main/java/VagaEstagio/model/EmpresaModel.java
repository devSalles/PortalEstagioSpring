package VagaEstagio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb__empresa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpresaModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String cnpj;

    @Column(nullable = false)
    private String area;

    @OneToMany(mappedBy = "empresaModel")
    private List<VagaModel>vagaModel;
    
}
