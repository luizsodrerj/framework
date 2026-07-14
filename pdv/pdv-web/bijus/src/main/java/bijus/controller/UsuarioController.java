package bijus.controller;

import bijus.entity.Endereco;
import bijus.entity.Usuario;
import bijus.service.UsuarioService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class UsuarioController {
	
    private Usuario usuario = new Usuario();
    private Endereco endereco;
    
    private String email;
    private String senha;

    @EJB
    private UsuarioService usuarioService;

    
     
    public String cadastrarUsuario() {
        if (usuarioService.emailExiste(usuario.getEmail())) {
        	String msg = "Email j\u00E1 cadastrado";
            FacesContext.getCurrentInstance().addMessage(null,
              new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg)
            );
            return null;
        }
        usuarioService.cadastrarUsuario(usuario);
        usuario = usuarioService.getPersistence().findObject(Usuario.class, usuario.getId());

        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucesso", "Usu\u00E1rio cadastrado"));

        return "/shop/usuario/CadastroEndereco.xhtml?faces-redirect=true";
    }
  
    public String salvarEndereco() {
        usuarioService.salvarEndereco(endereco, usuario);
        usuario  = usuarioService.getUsuarioById(usuario.getId());
        endereco = usuario.getEndereco();
        
        return "/shop/home/home-shop.xhtml";
    }    
     
    @PostConstruct
    public void init() {
        reset();
    }    

    private void reset() {
    	endereco = new Endereco(); 
    	usuario = new Usuario();
    }
     
    public String login() {
        Usuario u = usuarioService.autenticar(email, senha);

        if (u != null) {
            usuario = u;
            return "home.xhtml?faces-redirect=true";
        }
        FacesContext.getCurrentInstance().addMessage(
          null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro", "Usu\u00E1rio ou senha inv\u00E1lidos")
        );
        return null;
    }

    public String logout() {
        FacesContext.getCurrentInstance()
                .getExternalContext()
                .invalidateSession();

        return "login.xhtml?faces-redirect=true";
    }

    // Getters e Setters

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }    
    
}