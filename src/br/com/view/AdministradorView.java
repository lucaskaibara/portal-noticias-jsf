package br.com.view;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import br.com.controller.AdministradorController;
import br.com.model.Administrador;

@ManagedBean(name = "administradorView")
@ViewScoped
public class AdministradorView {

	@Id
	private int codAdm;

	private String nomeCompleto;
	private String email;
	private String senha;

	private List<Administrador> listaAdm = new AdministradorController().Listar();      

	private Administrador admSelecionado = new Administrador();   

    private Administrador admCarregado;
    
	public int getCodAdm() {
		return codAdm;
	}

	public void setCodAdm(int codAdm) {
		this.codAdm = codAdm;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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
		MessageDigest md;
		
		try {
			md = MessageDigest.getInstance("MD5");
			
			md.update(senha.getBytes(), 0, senha.length());

			this.senha = new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			this.senha = "202cb962ac59075b964b07152d234b70";
		}	
	}

	public List<Administrador> getListaAdm() {
		return listaAdm;
	}

	public void setListaAdm(List<Administrador> listaAdm) {
		this.listaAdm = listaAdm;
	}

	public Administrador getAdmSelecionado() {
		return admSelecionado;
	}

	public void setAdmSelecionado(Administrador admSelecionado) {
		this.admSelecionado = admSelecionado;
	}
	
	public Administrador getAdmCarregado() {
		return admCarregado;
	}

	public void setAdmCarregado(Administrador admCarregado) {
		this.admCarregado = admCarregado;
	}
	
	public void btnSalvarAdm(ActionEvent actionEvent) {

		FacesMessage message = null;

		if (this.nomeCompleto == null || this.nomeCompleto.trim().length() < 1) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencha o campo nome completo!", null);

		} else if (this.email == null || this.email.trim().length() < 1) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencha o campo email!", null);

		} else if (this.senha == null || this.senha.trim().length() < 1) {

			message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor, preencha o campo senha!", null);

		} else {

			Administrador adm = new Administrador(this.nomeCompleto, this.email, this.senha);
			new AdministradorController().Salvar(adm);
			this.nomeCompleto = "";
			this.email = "";
			this.senha = "";
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados do administrador salvos com sucesso!", null);

		}

		FacesContext.getCurrentInstance().addMessage(null, message);

	}
	
	public void btnExcluirAdm() {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		String a = params.get("codAdm");
		
		FacesMessage message = null;

		new AdministradorController().Excluir(Integer.parseInt(a));
		
		this.listaAdm = new AdministradorController().Listar();
		
		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Dados do administrador excluídos com sucesso!", null);

		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
	public void btnLogarAdm(ActionEvent actionEvent) {

		FacesMessage message = null;

		message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Logado!", null);

		FacesContext.getCurrentInstance().addMessage(null, message);
		
	}
	
	public void btnCarregarAdm(ActionEvent actionEvent) {
		
		FacesContext fc = FacesContext.getCurrentInstance();
		
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		
		int codAdm = Integer.parseInt(params.get("codAdm"));
		
		this.admCarregado = new AdministradorController().CarregarAdm(codAdm);
		
	}
	
    @PostConstruct
    public void init() {

		FacesContext fc = FacesContext.getCurrentInstance();
		
		String url = (((HttpServletRequest) fc.getExternalContext().getRequest()).getRequestURI()).trim();
		
		if (url.equals("/TrabalhoAvaliacao/editarAdministrador.xhtml")) {
			
			Map<String, String> params = fc.getExternalContext().getRequestParameterMap();

			int codAdm = Integer.parseInt(params.get("codAdm"));
			
			Administrador adm = new AdministradorController().CarregarAdm(codAdm);

			setCodAdm(adm.getCodAdm());
			setNomeCompleto(adm.getNomeCompleto());
			setEmail(adm.getEmail());
			setSenha(adm.getSenha());
			
		}

    }

}