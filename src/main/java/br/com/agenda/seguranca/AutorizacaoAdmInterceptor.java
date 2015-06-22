package br.com.agenda.seguranca;

import javax.inject.Inject;

import br.com.agenda.controllers.UsuarioController;
import br.com.caelum.vraptor.Accepts;
import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;

@Intercepts
public class AutorizacaoAdmInterceptor {
	private UsuarioLogado usuarioLogado;
	private Result result;
	private ControllerMethod method;

	@Inject
	public AutorizacaoAdmInterceptor(UsuarioLogado usuarioLogado, Result result, ControllerMethod method){
		this.usuarioLogado = usuarioLogado;
		this.result = result;
		this.method = method;
	}
	
	public AutorizacaoAdmInterceptor(){
		
	}
	
	@Accepts
	public boolean accept(){
		return method.containsAnnotation(OpenAdmin.class);
	}
	
	@AroundCall
	public void intercepts(SimpleInterceptorStack stack){
		if(usuarioLogado.isLogado() && usuarioLogado.isAdmin()){
			stack.next();
		}else{
			result.redirectTo(UsuarioController.class).formCadastro();
		}
	}
}
