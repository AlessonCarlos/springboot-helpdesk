package helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;

import helpdesk.domain.Chamado;
import jakarta.validation.constraints.NotNull;

public class chamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L; 

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    @NotNull(message = "O campo tem que ser preenchido!" )
    private Integer prioridade;
    @NotNull(message = "O campo tem que ser preenchido!" )
    private Integer status;
    @NotNull(message = "O campo tem que ser preenchido!" )
    private String titulo;
    @NotNull(message = "O campo tem que ser preenchido!" )
    private String observacoes;
    @NotNull(message = "O campo tem que ser preenchido!" )
    private Integer tecnico;
    @NotNull(message = "O campo tem que ser preenchido!" )
    private Integer cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public chamadoDTO(){
        super();
    }

    public chamadoDTO(Chamado obj ) {
    this.id = obj.getId();
    this.dataAbertura = obj.getDataAbertura();
    this.dataFechamento = obj.getDataFechamento();
    this.prioridade = (obj.getPrioridade() != null) ? obj.getPrioridade().getCodigo() : null;
    this.status = (obj.getStatus() != null) ? obj.getStatus().getCodigo() : null;

    this.titulo = obj.getTitulo();
    this.observacoes = obj.getObservacoes();

    if (obj.getTecnico() != null) {
        this.tecnico = obj.getTecnico().getId();
        this.nomeTecnico = obj.getTecnico().getNome();
    } else {
        this.tecnico = null;
        this.nomeTecnico = null;
    }

    if (obj.getCliente() != null) {
        this.cliente = obj.getCliente().getId();
        this.nomeCliente = obj.getCliente().getNome();
    } else {
        this.cliente = null;
        this.nomeCliente = null;
    }
}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Integer getTecnico() {
        return tecnico;
    }

    public void setTecnico(Integer tecnico) {
        this.tecnico = tecnico;
    }

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public String getNomeTecnico() {
        return nomeTecnico;
    }

    public void setNomeTecnico(String nomeTecnico) {
        this.nomeTecnico = nomeTecnico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    

    

}
