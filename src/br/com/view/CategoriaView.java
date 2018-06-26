package br.com.view;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.persistence.Id;

import br.com.controller.CategoriaController;
import br.com.model.Categoria;

@ManagedBean(name = "categoriaView")
@ViewScoped
public class CategoriaView {

	@Id
	private int codCategoria;

	private String nomeCategoria;

	private List<Categoria> listaCategoria = new CategoriaController().Listar();
	
	public int getCodCategoria() {
		return codCategoria;
	}

	public void setCodCategoria(int codCategoria) {
		this.codCategoria = codCategoria;
	}
	
	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	public List<Categoria> getListaCategoria() {
		return listaCategoria;
	}

	public void setListaCategoria(List<Categoria> listaCategoria) {	
		this.listaCategoria = listaCategoria;
	}

	public void btnSalvarCategoria(ActionEvent actionEvent) {

		FacesMessage message = null;

		if (this.nomeCategoria == null || this.nomeCategoria.trim().length() < 1) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencha o campo nome da categoria!", null);

		} else {

			Categoria c = new Categoria(this.nomeCategoria);
			new CategoriaController().Salvar(c);
			this.nomeCategoria = "";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados da categoria salvos com sucesso!", null);

		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}
	
	public void btnExcluirAdm() {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String a = params.get("codCategoria");
		
		FacesMessage message = null;

		new CategoriaController().Excluir(Integer.parseInt(a));
		
		this.listaCategoria = new CategoriaController().Listar();
		
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados da categoria excluídos com sucesso!", null);

		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
}