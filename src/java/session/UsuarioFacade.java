package session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import model.Usuario;
import session.AbstractFacade;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "ProjetoJSFJPAPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario buscarPorNomeUsuario(String nomeUsuario) {
        String jpql = "select u from Usuario u where u.nomeUsuario=:nomeUsuario";
        TypedQuery<Usuario> query = em.createQuery(jpql,Usuario.class);
        query.setParameter ("nomeUsuario",nomeUsuario);
        Usuario u = query.getSingleResult();
        return u;
    }
    
}