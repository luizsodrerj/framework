package framework.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

/**
 * Classe utilitária para formatação de numeros
 * @author Luiz Alberto
 */
public class FormatNumberUtil  {

    private double value;
    private DecimalFormat formato;
    //private DateFormat formato_data;
    //private boolean temSepMilhar = false;
    //private boolean temSepDecim  = false;
    public static final int SEM_FORMATO               = 0;
    public static final int FORMATO_QTD               = 3;
    public static final int FORMATO_QTD_DEC1          = 1;
    public static final int UMA_CASA_DECIMAL          = 1;
    public static final int FORMATO_QTD_DEC2          = 4;
    public static final int DUAS_CASAS_DECIMAIS       = 4;
    public static final int FORMATO_QTD_DEC3          = 5;
    public static final int TRES_CASAS_DECIMAIS       = 5;
    public static final int FORMATO_QTD_DEC4          = 6;
    public static final int QUATRO_CASAS_DECIMAIS     = 6;
    public static final int FORMATO_INTEIRO           = 7;
    public static final int FORMATO_INTEIRO_SEM_ZERO  = 8;
    private int nFmt;
    private Locale brazil = new Locale("pt","BR");

    
    public FormatNumberUtil(int mascara) {
        value = 0;
        setFormato(mascara);
    }
    public FormatNumberUtil(int mascara, boolean zero) {
        value = 0;
        setFormato(mascara);
    }
    public FormatNumberUtil(double num, int mascara) {
        value = num;
        setFormato(mascara);
    }

    public FormatNumberUtil(float num, int mascara) {
        value = num;
        setFormato(mascara);
    }

    public FormatNumberUtil(Double num, int mascara) {
        value = num.doubleValue();
        setFormato(mascara);
    }

    public FormatNumberUtil(String num, int mascara) {
        value = (new Double(num)).doubleValue();
        setFormato(mascara);
    }

    public static final String format(BigDecimal valueToFormat, int formatType) {
    	return valueToFormat != null ? format(valueToFormat.doubleValue(), formatType) : null;
    }
    
    public static final String format(String valueToFormat, int formatType) {
    	return valueToFormat != null && !"".equals(valueToFormat.trim()) ? format(Double.parseDouble(valueToFormat), formatType) : valueToFormat;
    }

    public static final String format(Float valueToFormat, int formatType) {
    	return valueToFormat != null ? format(valueToFormat.doubleValue(), formatType) : null;
    }
    
    public static final String format(Double valueToFormat, int formatType) {
    	return valueToFormat != null ? format(valueToFormat.doubleValue(), formatType) : null;
    }
    
    public static final String format(float valueToFormat, int formatType) {
    	double value = valueToFormat;
    	
    	return format(value, formatType);
    }
    
    public static final String format(double valueToFormat, int formatType) {
    	
    	DecimalFormat decimalFormat = getFormat(formatType);
    	
        return decimalFormat.format(valueToFormat);
    }
    
    private static final DecimalFormat getFormat(int formatType) {
    	Locale 			brazil 			= new Locale("pt","BR");
    	DecimalFormat	decimalFormat 	= null;
    	
        switch(formatType) {
	        case(FORMATO_QTD):
	                decimalFormat = new DecimalFormat("###,###,###,##0");
	                decimalFormat.getDecimalFormatSymbols().setDecimalSeparator(',');
	                decimalFormat.getDecimalFormatSymbols().setGroupingSeparator('.');
	                decimalFormat.getDecimalFormatSymbols().setCurrencySymbol("R$");
	                break;
	        case(FORMATO_QTD_DEC1):
		            decimalFormat = (DecimalFormat)DecimalFormat.getInstance(brazil);
		            decimalFormat.getDecimalFormatSymbols().setDecimalSeparator(',');
		            decimalFormat.getDecimalFormatSymbols().setGroupingSeparator('.');
		            decimalFormat.getDecimalFormatSymbols().setCurrencySymbol("R$");
		            decimalFormat.setMaximumFractionDigits(1);
		            decimalFormat.setMinimumFractionDigits(1);
		            break;
	        case(FORMATO_QTD_DEC2):
	                decimalFormat = (DecimalFormat)DecimalFormat.getInstance(brazil);
	                decimalFormat.getDecimalFormatSymbols().setDecimalSeparator(',');
	                decimalFormat.getDecimalFormatSymbols().setGroupingSeparator('.');
	                decimalFormat.getDecimalFormatSymbols().setCurrencySymbol("R$");
	                decimalFormat.setMinimumFractionDigits(2);
	                break;
	        case(FORMATO_QTD_DEC3):
	                decimalFormat = new DecimalFormat("###,###,###,##0.000");
	                decimalFormat.getDecimalFormatSymbols().setDecimalSeparator(',');
	                decimalFormat.getDecimalFormatSymbols().setGroupingSeparator('.');
	                decimalFormat.getDecimalFormatSymbols().setCurrencySymbol("R$");
	                decimalFormat.setMinimumFractionDigits(3);
	                break;
	        case(FORMATO_QTD_DEC4):
	                decimalFormat = new DecimalFormat("###,###,###,##0.0000");
	                decimalFormat.getDecimalFormatSymbols().setDecimalSeparator(',');
	                decimalFormat.getDecimalFormatSymbols().setGroupingSeparator('.');
	                decimalFormat.getDecimalFormatSymbols().setCurrencySymbol("R$");
	                decimalFormat.setMinimumFractionDigits(4);
	                break;
	         case(FORMATO_INTEIRO):
	                decimalFormat = new DecimalFormat("###########");
	                decimalFormat.getDecimalFormatSymbols().setCurrencySymbol("R$");
	                break;
	         case(FORMATO_INTEIRO_SEM_ZERO):
	                decimalFormat = new DecimalFormat("###########");
	                decimalFormat.getDecimalFormatSymbols().setCurrencySymbol("R$");
	                break;
	         default: String exception = "Foi passado como parâmetro uma constante de formatação inválida"; 
	                 throw new NumberFormatException(exception);
	    }
        return decimalFormat;
    }
    
    
    private void setFormato(int tipo){
        String masc;
        nFmt = tipo;
        switch(tipo){
            case(FORMATO_QTD):masc = "###,###,###,##0";
                    formato = new DecimalFormat(masc);
                    formato.getDecimalFormatSymbols().setDecimalSeparator(',');
                    formato.getDecimalFormatSymbols().setGroupingSeparator('.');
                    formato.getDecimalFormatSymbols().setCurrencySymbol("R$");
                    //temSepMilhar = true;
                    //temSepDecim  = true;
                    break;
            case(FORMATO_QTD_DEC1):
		            formato = (DecimalFormat)DecimalFormat.getInstance(brazil);
		            formato.getDecimalFormatSymbols().setDecimalSeparator(',');
		            formato.getDecimalFormatSymbols().setGroupingSeparator('.');
		            formato.getDecimalFormatSymbols().setCurrencySymbol("R$");
		            formato.setMaximumFractionDigits(1);
		            formato.setMinimumFractionDigits(1);
		            //temSepMilhar	= true;
		            //temSepDecim		= true;
		            nFmt		 	= 0;
		            break;
            case(FORMATO_QTD_DEC2):masc = "###,###,###,##0.00###";
                    formato = new DecimalFormat(masc);
                    formato = (DecimalFormat)DecimalFormat.getInstance(brazil);
                    formato.getDecimalFormatSymbols().setDecimalSeparator(',');
                    formato.getDecimalFormatSymbols().setGroupingSeparator('.');
                    formato.getDecimalFormatSymbols().setCurrencySymbol("R$");
                    formato.setMinimumFractionDigits(2);
                    //temSepMilhar = true;
                    //temSepDecim  = true;
                    break;
            case(FORMATO_QTD_DEC3):masc = "###,###,###,##0.000";
                    formato = new DecimalFormat(masc);
                    formato.getDecimalFormatSymbols().setDecimalSeparator(',');
                    formato.getDecimalFormatSymbols().setGroupingSeparator('.');
                    formato.getDecimalFormatSymbols().setCurrencySymbol("R$");
                    formato.setMinimumFractionDigits(3);
                    //temSepMilhar = true;
                    //temSepDecim  = true;
                    break;
            case(FORMATO_QTD_DEC4):masc = "###,###,###,##0.0000";
                    formato = new DecimalFormat(masc);
                    formato.getDecimalFormatSymbols().setDecimalSeparator(',');
                    formato.getDecimalFormatSymbols().setGroupingSeparator('.');
                    formato.getDecimalFormatSymbols().setCurrencySymbol("R$");
                    formato.setMinimumFractionDigits(4);
                    //temSepMilhar = true;
                    //temSepDecim  = true;
                    break;
             case(FORMATO_INTEIRO):masc = "###########";
                    formato = new DecimalFormat(masc);
                    formato.getDecimalFormatSymbols().setCurrencySymbol("R$");
                    //temSepMilhar = false;
                    //temSepDecim  = false;
                    break;
             case(FORMATO_INTEIRO_SEM_ZERO):masc = "###########";
                    formato = new DecimalFormat(masc);
                    formato.getDecimalFormatSymbols().setCurrencySymbol("R$");
                    //temSepMilhar = false;
                    //temSepDecim  = false;
                    break;
             default: String exception = "Foi passado como parâmetro uma constante de formatação inválida"; 
                     throw new NumberFormatException(exception);
        }
    }

    public String toString() {
        if (nFmt == FORMATO_INTEIRO_SEM_ZERO && value == 0) {
        	return "";
        }
        if (nFmt == FORMATO_QTD_DEC1) {
			int intValue = (int) value;

			if (value == ((double) intValue)) {
				return formato.format(value) + ",00";
			}
		}
        return formato.format(value);
    }
 
    public Integer getIntValue(){
        return new Integer((int)value);
    }

    public Long getLongValue(){
        return new Long((long)value);
    }

    public Float getFloatValue(){
        return new Float((float)value);
    }

    public Double getDoubleValue(){
        return new Double(value);
    }
    public int getTipFormato(){
      return nFmt;
    }
    public Double parse(String dado){
        try{
            return new Double(formato.parse(dado).doubleValue());
        }
        catch(Exception e){
            return new Double(-1);
        }
    }



}