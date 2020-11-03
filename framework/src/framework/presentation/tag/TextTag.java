package framework.presentation.tag;

import java.util.Date;

import javax.servlet.jsp.JspException;

import org.apache.struts.taglib.html.BaseFieldTag;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ResponseUtils;

import framework.util.DateUtil;
import framework.util.FormatNumberUtil;


/**
 * @author luiz.sodre
 *
 */
public class TextTag extends BaseFieldTag {

	private static final int EVAL_BODY	= 2;
	
	
	public TextTag() {
		super();
		type = "text";
	}
 	
	
	public int doStartTag() throws JspException {
		StringBuffer stringbuffer = new StringBuffer("<input type=\"");
		stringbuffer.append(type);
		stringbuffer.append("\" name=\"");
		if (indexed) {
			prepareIndex(stringbuffer, name);
		}
		stringbuffer.append(property);
		stringbuffer.append("\"");
		if (accesskey != null) {
			stringbuffer.append(" accesskey=\"");
			stringbuffer.append(accesskey);
			stringbuffer.append("\"");
		}
		if (accept != null) {
			stringbuffer.append(" accept=\"");
			stringbuffer.append(accept);
			stringbuffer.append("\"");
		}
		if (maxlength != null) {
			stringbuffer.append(" maxlength=\"");
			stringbuffer.append(maxlength);
			stringbuffer.append("\"");
		}
		if (cols != null) {
			stringbuffer.append(" size=\"");
			stringbuffer.append(cols);
			stringbuffer.append("\"");
		}
		if (tabindex != null) {
			stringbuffer.append(" tabindex=\"");
			stringbuffer.append(tabindex);
			stringbuffer.append("\"");
		}
		stringbuffer.append(" value=\"");
		if (value != null) {
			stringbuffer.append(ResponseUtils.filter(value));
		} else if (redisplay || !"password".equals(type)) {
			Object obj = formatValue(RequestUtils.lookup(pageContext, name, property, null));
			if (obj == null) {
				obj = "";
			}
			stringbuffer.append(ResponseUtils.filter(obj.toString()));
		}
		stringbuffer.append("\"");
		stringbuffer.append(prepareEventHandlers());
		stringbuffer.append(prepareStyles());
		stringbuffer.append(getElementClose());
		ResponseUtils.write(pageContext, stringbuffer.toString());
		
		return EVAL_BODY;
	}
	
	protected Object formatValue(Object value) throws JspException {
		Object formatedValue = value;
		
		try {
			if (value != null && value instanceof Double) {
				formatedValue = FormatNumberUtil.format((Double)value, FormatNumberUtil.DUAS_CASAS_DECIMAIS);
			}
			if (value != null && value instanceof Date) {
				formatedValue = DateUtil.formatToSimpleDate((Date)value);
			}
		} catch (Exception e) {
			formatedValue = value;
		}
		
		return formatedValue;
	}
	
}





