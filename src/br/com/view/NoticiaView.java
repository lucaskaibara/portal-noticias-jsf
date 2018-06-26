package br.com.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import br.com.controller.NoticiaController;
import br.com.controller.CategoriaController;
import br.com.model.Noticia;
import br.com.model.Categoria;

@ManagedBean(name = "noticiaView")
@ViewScoped
public class NoticiaView {

	@Id
	private int codNot;

	private String tituloNoticia;
	private String materia;
	
	private int codCategoria;

	private List<Noticia> listaNot = new NoticiaController().Listar();  
	private List<Categoria> listaCategoria = new CategoriaController().Listar();    

	private Noticia notSelecionado = new Noticia();
	
	private String categoriaSelecionada;
    private HashMap<String, Integer> opcoesCategorias = new CategoriaController().ListarCategoriasNot(listaCategoria);

	public int getCodNot() {
		return codNot;
	}

	public void setCodNot(int codNot) {
		this.codNot = codNot;
	}

	public String getTituloNoticia() {
		return tituloNoticia;
	}

	public void setTituloNoticia(String tituloNoticia) {
		this.tituloNoticia = tituloNoticia;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}
	
	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}

	public List<Noticia> getListaNot() {
		return listaNot;
	}

	public void setListaNot(List<Noticia> listaNot) {
		this.listaNot = listaNot;
	}
	
    public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {
		this.listaCategoria = listaCategoria;
	}

	public Noticia getNotSelecionado() {
		return notSelecionado;
	}

	public void setNotSelecionado(Noticia notSelecionado) {
		this.notSelecionado = notSelecionado;
	}

	public String getCategoriaSelecionada() {
        return categoriaSelecionada;
    }
 
    public void setCategoriaSelecionada(String categoriaSelecionada) {
        this.categoriaSelecionada = categoriaSelecionada;
    }

	public HashMap<String, Integer> getOpcoesCategorias() {
		return opcoesCategorias;
	}

	public void setOpcoesCategorias(HashMap<String, Integer> opcoesCategorias) {
		this.opcoesCategorias = opcoesCategorias;
	}

	public void btnSalvarNot(ActionEvent actionEvent) {

		FacesMessage message = null;

		if (this.tituloNoticia == null || this.tituloNoticia.trim().length() < 1) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencha o campo nome completo!", null);

		} else if (this.categoriaSelecionada == null || this.categoriaSelecionada.trim().length() < 1) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencha o campo categoria de notícia!", null);

		} else if (this.materia == null || this.materia.trim().length() < 1) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencha o campo materia!", null);

		} else {

			Noticia not = new Noticia(this.tituloNoticia, this.materia, Integer.parseInt(this.categoriaSelecionada));
			new NoticiaController().Salvar(not);
			this.tituloNoticia = "";
			this.categoriaSelecionada = "";
			this.materia = "";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados do Noticia salvos com sucesso!", null);

		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}
	
	public void btnExcluirNot() {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String a = params.get("codNot");
		
		FacesMessage message = null;

		new NoticiaController().Excluir(Integer.parseInt(a));
		
		this.listaNot = new NoticiaController().Listar();
		
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados do Noticia excluídos com sucesso!", null);

		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
	public void btnLogarNot(ActionEvent actionEvent) {

		FacesMessage message = null;

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logado!", null);

		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
    @PostConstruct
    public void init() {

		FacesContext fc = FacesContext.getCurrentInstance();
		
		String url = (((HttpServletRequest) fc.getExternalContext().getRequest()).getRequestURI()).trim();
		
		if (url.equals("/TrabalhoAvaliacao/noticia.xhtml")) {
			
			Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

			int codNot = Integer.parseInt(params.get("codNot"));
			
			Noticia not = new NoticiaController().CarregarNoticia(codNot);
			
			setCodNot(not.getCodNot());
			setTituloNoticia(not.getTituloNoticia());
			setMateria(not.getMateria());
			setCodCategoria(not.getCodCategoria());
			
		}

    } 
	
}