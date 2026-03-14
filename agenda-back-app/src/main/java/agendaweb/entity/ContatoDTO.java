package agendaweb.entity;

import javax.persistence.Column;

public class ContatoDTO {

    private String id;
    private String contato;
    private String referencia;
    private String endereco;
    private String telefones;
    private String emails;
    private String obs;


    public ContatoDTO() {
    }

    public ContatoDTO(Contato contato) {
        id = contato.getId().toString();
        this.contato = contato.getContato();
        referencia = contato.getReferencia();
        endereco = contato.getReferencia();
        telefones = contato.getTelefones();
        emails = contato.getEmails();
        obs = contato.getObs();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefones() {
        return telefones;
    }

    public void setTelefones(String telefones) {
        this.telefones = telefones;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }
}
