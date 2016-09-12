package pdv.session;

import pdv.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

@Name("formaPagamentoList")
public class FormaPagamentoList extends EntityQuery<FormaPagamento> {

	private static final long serialVersionUID = -3559560011059786630L;

	private static final String EJBQL = "select formaPagamento from FormaPagamento formaPagamento";

	private static final String[] RESTRICTIONS = {
			"lower(formaPagamento.forma) like lower(concat(#{formaPagamentoList.formaPagamento.forma},'%'))", };

	private FormaPagamento formaPagamento = new FormaPagamento();

	
	
	public FormaPagamentoList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
}
