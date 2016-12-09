package bean;

import bean.util.beanUtil;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import model.Usuario;
import session.UsuarioFacade;

/**
 *
 * @author jairb
 */
@Named(value = "UsuarioMB")
@SessionScoped
public class UsuarioMB implements Serializable{
    private Usuario usuarioSelecionado;
    @Inject
    @EJB
    private UsuarioFacade usuarioFacade;
    private Usuario usuario;
    /**
     * Creates a new instance of ClienteMB
     */
    public UsuarioMB() {
        usuarioSelecionado = new Usuario();
    }
    
    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
    
    public List<Usuario> getListaUsuario(){
        return usuarioFacade.findAll();
    }
    public String novoUsuario(){
        usuarioSelecionado = new Usuario();
        return("/admin/formularioCadastro?faces-redirect=true");
    }
    
    public String adicionarUsuario(){
        usuarioSelecionado.setCliAtivo(Boolean.TRUE);
        usuarioFacade.create(usuarioSelecionado);
        return(this.novoUsuario());
    }
    
    public String editarUsuario(Usuario u){
        usuarioSelecionado = u;
        return("/admin/formularioEdicao?faces-redirect=true");        
    }
    
    public String atualizarUsuario(){
        usuarioFacade.edit(usuarioSelecionado);
        return("/admin/index?faces-redirect=true");
    }
    
    public void removerUsuario(Usuario usuario){
        usuarioFacade.remove(Usuario);
    }
    public SelectItem[] getItemsAvailableSelectOne() {
        return beanUtil.getSelectItems(usuarioFacade.findAll(), true);
    }
}