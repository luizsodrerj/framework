package bijus.service;

import bijus.entity.Endereco;
import bijus.entity.Usuario;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;

@Stateless
public class UsuarioService extends BaseService {

    @PersistenceContext
    private EntityManager em;

    
    public void salvarEndereco(Endereco endereco, Usuario usuario) {
        if (endereco.getId() == null) {
        	usuario = getPersistence().findObject(Usuario.class,usuario.getId());
        	endereco.setUsuario(usuario);
            em.persist(endereco);
        } else {
            em.merge(endereco);
        }
    }
     
    
    public Usuario getUsuarioById(Long id) {
    	Usuario user = new Usuario();
    	Usuario u = getPersistence().findObject(Usuario.class, id);
    	user.setId(u.getId());
    	user.setDataCadastro(u.getDataCadastro());
    	user.setEmail(u.getEmail());
    	user.setNome(u.getNome());
    	user.setSenha(u.getSenha());
    	user.setEndereco(new Endereco());
    	try {
    		Endereco end = user.getEndereco();
    		BeanUtils.copyProperties(end, u.getEndereco());
    		end.setTelefone(u.getEndereco().getTelefone());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    	return user;
    }
    
    public void cadastrarUsuario(Usuario usuario) {
        em.persist(usuario);
    }

    public Usuario autenticar(String email, String senha) {
        try {
            return em.createQuery(
                    "SELECT u FROM Usuario u WHERE u.email = :email AND u.senha = :senha",
                    Usuario.class)
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public boolean emailExiste(String email) {
        Long total = em.createQuery(
                        "SELECT COUNT(u) FROM Usuario u WHERE u.email = :email",
                        Long.class)
                .setParameter("email", email)
                .getSingleResult();

        return total > 0;
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}