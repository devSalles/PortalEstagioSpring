package VagaEstagio.model;

import VagaEstagio.enums.VagaStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb__vaga")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VagaModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob @Column(columnDefinition = "LONGTEXT",nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal bolsa;


    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private VagaStatus vaga;

    @ManyToOne
    @JoinColumn(name = "empresa_id",nullable = true)
    @JsonBackReference
    private EmpresaModel empresaModel;

    @OneToOne
    @JoinColumn(name = "estagiario_id",nullable = true)
    @JsonManagedReference
    private EstagiarioModel estagiarioModel;
}
