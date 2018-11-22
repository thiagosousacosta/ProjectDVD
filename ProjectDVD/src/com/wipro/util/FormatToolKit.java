/**@nome: br.com.bradesco.gcpj.webservice.dao
 *
 * Compilador: JDK 1.6
 *
 * @propósito: .
 *
 * Data da Criação: Jan 20, 2016
 *
 * Parâmetros de Compilação:  -d
 *
 */
package com.wipro.util;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Vector;

/**
 * @author João Germano Filho
 * @Version 1.0
 */

/**
 * Utilitário para manipulação de data. <br>
 * Para converção de datas, é necessário informar o formato da data: <br>
 * 
 * <pre>
 * &lt;br&gt; Symbol   Meaning                 Presentation        Example
 * &lt;br&gt; ------   -------                 ------------        -------
 * &lt;br&gt; G        era designator          (Text)              AD
 * &lt;br&gt; y        year                    (Number)            1996
 * &lt;br&gt; M        month in year           (Text &amp; Number)     July &amp; 07
 * &lt;br&gt; d        day in month            (Number)            10
 * &lt;br&gt; h        hour in am/pm (1&tilde;12)    (Number)            12
 * &lt;br&gt; H        hour in day (0&tilde;23)      (Number)            0
 * &lt;br&gt; m        minute in hour          (Number)            30
 * &lt;br&gt; s        second in minute        (Number)            55
 * &lt;br&gt; S        millisecond             (Number)            978
 * &lt;br&gt; E        day in week             (Text)              Tuesday
 * &lt;br&gt; D        day in year             (Number)            189
 * &lt;br&gt; F        day of week in month    (Number)            2 (2nd Wed in July)
 * &lt;br&gt; w        week in year            (Number)            27
 * &lt;br&gt; W        week in month           (Number)            2
 * &lt;br&gt; a        am/pm marker            (Text)              PM
 * &lt;br&gt; k        hour in day (1&tilde;24)      (Number)            24
 * &lt;br&gt; K        hour in am/pm (0&tilde;11)    (Number)            0
 * &lt;br&gt; z        time zone               (Text)              Pacific Standard Time
 * &lt;br&gt; '        escape for text         (Delimiter)
 * &lt;br&gt; ''       single quote            (Literal)           '
 * </pre>
 * 
 * @version 1.0
 * @author João Germano Filho
 */

public final class FormatToolKit {

	/**
	 * Formatação pt - BR
	 */
	private static final Locale locale = new Locale("pt", "BR");

	/**
	 * Formatação de números, de acordo com o locale pt BR
	 */
	private static final NumberFormat nf = NumberFormat.getNumberInstance(locale);

	public static final int POSICAO_LEFT = 1;
	public static final int POSICAO_RIGHT = 2;

	public static final String BLANK = " ";

	private FormatToolKit() {
		throw new IllegalStateException("Classe Utilitaria");
	}

	/**
	 * Converte uma data para um string no formato desejado.
	 * 
	 * @param date
	 *            - Data a ser convertida.
	 * @param format
	 *            Formato da data.
	 * @return String A data no formato string.
	 */
	public static String dateToStr(Date date, String format) {
		String retorno = "";
		if ((null != date) && (null != format)) {
			SimpleDateFormat formater = new SimpleDateFormat(format);
			retorno = formater.format(date);
		}
		return retorno;
	}

	public static String getDb2TimeStamp(String value, String format) {
		String retorno = "";
		Date data = strToDate(value, format);
		retorno = dateToStr(data, "yyyy-MM-dd-HH.mm.ss.SSSSSS"); // "2018-04-14-18.09.36.671341"
		return retorno;
	}

	public static String getDb2Date(String value, String format) {
		String retorno = "";
		Date data = strToDate(value, format);
		retorno = dateToStr(data, "dd.MM.yyyy"); // "25.04.2018"
		return retorno;
	}

	public static Short getShortValue(Integer value) {
		Short retorno = null;
		retorno = value.shortValue();
		return retorno;
	}

	public static BigDecimal getBigDecimalValue(Double valor) {
		BigDecimal retorno = BigDecimal.ZERO;
		try {
			if (valor != null) {
				retorno = (BigDecimal.valueOf(valor)).setScale(2);
			}
		} catch (Exception e) {
		}
		return retorno;
	}

	/**
	 * Verificar se valor informado é nulo ou vazio.
	 * 
	 * @param
	 */
	public static boolean isEmpty(String value) {
		boolean retorno = false;
		if (value == null || value.length() == 0)
			retorno = true;
		return retorno;
	}

	/**
	 * Recuperar data atual no formato informado.
	 * 
	 * @param formato
	 *            - String contendo o formato em que a data atual será
	 *            retornado.
	 * @return Objeto String contendo a data atual no formato que foi informado.
	 */
	public static String getStrDataAtual(String formato) {
		return FormatToolKit.dateToStr(FormatToolKit.getDataAtual(), formato);
	}

	public static String getIntegerValue(String valor) {
		String retorno = valor;
		try {
			retorno = getNumberFormat(valor, 0);
		} catch (Exception e) {
		}
		return retorno;
	}

	/**
	 * Converte uma String Date para um string no novo formato desejado.
	 * 
	 * @param date
	 *            - Data a ser convertida.
	 * @param format
	 *            Formato da data.
	 * @param newFormat
	 *            Novo Formato da data.
	 * @return String A data no formato string.
	 */
	public static String convertDateToFormat(String date, String format, String newFormat) {
		String retorno = "";
		if ((null != date) && (null != format) && (null != newFormat)) {
			Date data = FormatToolKit.strToDate(date, format);
			retorno = FormatToolKit.dateToStr(data, newFormat);
		}
		return retorno;
	}

	/**
	 * Method calcIntervaloDias.
	 * 
	 * @param dataIni
	 *            Data Inicial
	 * @param dataFim
	 *            Data Final
	 * @return long quantidade de dias entre as duas datas
	 */
	public static long getIntervaloDias(Date dataIni, Date dataFim) {
		long retorno = -1;
		if ((null != dataIni) && (null != dataFim)) {
			GregorianCalendar c1 = new GregorianCalendar();
			c1.setTime(dataIni);
			GregorianCalendar c2 = new GregorianCalendar();
			c2.setTime(dataFim);
			retorno = ((c2.getTime().getTime() - c1.getTime().getTime()) / 1000 / 60 / 60 / 24);
		}
		return retorno;
	}

	/**
	 * Retorna uma Data
	 * 
	 * @param data
	 * @param dias
	 * @return Date
	 */
	public static Date addDays(Date data, int dias) {
		Date d = null;
		if (data != null) {
			GregorianCalendar c1 = new GregorianCalendar();
			c1.setTime(data);
			c1.add(Calendar.DAY_OF_MONTH, dias);
			d = c1.getTime();
		}
		return d;
	}

	public static Date addDaysDate(Date data, int dias) {
		Date d = addDays(data, dias);
		String sData = dateToStr(d, "dd/MM/yyyy") + " 00:00:00";
		d = strToDate(sData, "dd/MM/yyyy HH:mm:ss");
		return d;
	}

	/**
	 * Retorna uma Data
	 * 
	 * @param data
	 * @param dias
	 * @return Date
	 */
	public static Date deducteDays(Date data, int dias) {
		Date d = null;
		if (data != null) {
			GregorianCalendar c1 = new GregorianCalendar();
			c1.setTime(data);
			c1.add(Calendar.DAY_OF_MONTH, dias * -1);
			d = c1.getTime();
		}
		return d;
	}

	/**
	 * Converte um string para uma data.
	 * 
	 * @param srtDate
	 *            O string a ser convertido.
	 * @param format
	 *            Formato da data.
	 * @return Date A data no formato Date.
	 */
	public static Date strToDate(String srtDate, String format) {
		Date retorno = null;
		if ((null != srtDate) && (null != format)) {
			SimpleDateFormat formater = new SimpleDateFormat(format);
			TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
			formater.setTimeZone(tz);
			retorno = formater.parse(srtDate, new ParsePosition(0));
		}
		return retorno;
	}

	/**
	 * Retorna a Data Atual do Sistema no Formato especificado.
	 * 
	 * @param format
	 *            - Uma String contendo o formato a ser retornado a data atual.
	 * @return Date - Data Atual do Sistema
	 */
	public static Date getDataAtual(String format) {
		Date retorno = null;
		retorno = FormatToolKit.strToDate(FormatToolKit.dateToStr(FormatToolKit.getDataAtual(), format), format);
		return retorno;
	}

	public static boolean isStrDateValid(String data, int format) {
		boolean retorno = false;
		try {
			int dia = Integer.parseInt(data.substring(0, 2));
			int mes = Integer.parseInt(data.substring(3, 5));
			int ano = Integer.parseInt(data.substring(6));
			if (dia <= 0 || dia > 31)
				return false;
			if (mes <= 0 || mes > 12)
				return false;
			if (ano < 1970)
				return false;
			retorno = true;
		} catch (Exception e) {
			return retorno;
		}
		return retorno;
	}

	public static boolean isStrDateValid(String data, String format) {
		boolean retorno = false;
		try {
			Date date = FormatToolKit.strToDate(data, format);
			if (date != null) {
				String dataRetornada = FormatToolKit.dateToStr(date, format);
				retorno = (!dataRetornada.equalsIgnoreCase(data) ? false : true);
			}
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}

	/**
	 * Converte um string para um timestamp.
	 * 
	 * @param srtTimestamp
	 *            O string a ser convertido.
	 * @param format
	 *            Formato do timestamp.
	 * @return Timestamp O string no formato Timestamp.
	 */
	public static Timestamp strToTimestamp(String srtTimestamp, String format) {

		Date date = strToDate(srtTimestamp, format);

		return new Timestamp(date.getTime());
	}

	/**
	 * Converte um timestamp para um string no formato desejado.
	 * 
	 * @param timestamp
	 *            Timestamp a ser convertido.
	 * @param format
	 *            Formato da data.
	 * @return String O timestamp no formato string.
	 */
	public static String timestampToStr(Timestamp timestamp, String format) {

		return dateToStr(timestamp, format);
	}

	/**
	 * Converte um timestamp para Date no formato desejado.
	 * 
	 * @param timestamp
	 *            Timestamp a ser convertido.
	 * @param format
	 *            Formato da data.
	 * @return Data um Date no formato Date.
	 */
	public static Date TimestampToDate(Timestamp timestamp, String format) {
		String d = FormatToolKit.timestampToStr(timestamp, format);
		return strToDate(d, format);
	}

	/**
	 * Trunca uma data de acordo com o formato informado. <br>
	 * E.g: "yyyyMM" - volta a data para a primeira hora do primeiro dia do mês.
	 * <br>
	 * "yyyy" - volta a data para a primeira hora do primeiro dia do ano.
	 * 
	 * @param date
	 *            Data a ser truncada.
	 * @param format
	 *            Formato usado para truncar.
	 * @return Date A data trucada.
	 */
	public static Date trunc(Date date, String format) {

		return strToDate(dateToStr(date, format), format);
	}

	/**
	 * Trunca uma data na posição informada.
	 * 
	 * @param date
	 *            Data a ser truncada.
	 * @param format
	 *            Posição para truncar.
	 * @return Timestamp A data trucada.
	 */
	public static Timestamp trunc(Timestamp date, String format) {

		return strToTimestamp(timestampToStr(date, format), format);
	}

	/**
	 * Retorna a data de do dia anterior.
	 * 
	 * @return Date A data de agora, menos um dia.
	 */
	public static Date yesterday() {

		return new Date(System.currentTimeMillis() - 86400000L);
	}

	/**
	 * Retorna os milis-segundos de uma hora no formato HH:MM.
	 * 
	 * @param time
	 *            Hora no forma do HH:MM.
	 * @return long Milisegundos da data.
	 */
	public static long timeToMillis(String time) {

		if (time.equals(""))
			return 0;

		int pos = time.indexOf(":");
		int hours = Integer.parseInt(time.substring(0, pos));
		int minutes = Integer.parseInt(time.substring(pos + 1, time.length()));

		return ((hours * 60) + minutes) * 60000L;
	}

	/**
	 * Retorna o primeiro dia do mês.
	 * 
	 * @param date
	 *            Data que se deseja o primeiro dia do mês.
	 * @return Date Data no primeiro dia do mês.
	 */
	public static Date firstDayOfMonth(Date date) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(GregorianCalendar.DAY_OF_MONTH, 1);

		return gc.getTime();
	}

	/**
	 * Retorna o primeiro dia do ano.
	 * 
	 * @param date
	 *            Data em que se deseja retornar o primeiro dia do ano.
	 * @return Date Data do primeiro dia do ano.
	 */
	public static Date firstDayOfYear(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(GregorianCalendar.DAY_OF_MONTH, 1);
		gc.set(GregorianCalendar.MONTH, 0);

		return gc.getTime();
	}

	/**
	 * Retorna o dia anterior da data passada.
	 * 
	 * @param date
	 *            Data em que se deseja obter o dia anterior.
	 * @return Date Data do dia anterior.
	 */
	public static Date diaAnterior(Date date) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.add(GregorianCalendar.DAY_OF_MONTH, -1);
		return gc.getTime();
	}

	/**
	 * Retorna o último dia do mês.
	 * 
	 * @param date
	 *            Data que se deseja o último dia do mês.
	 * @return Date Data no último dia do mês.
	 */
	public static Date lastDayOfMonth(Date date) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		gc.set(GregorianCalendar.DAY_OF_MONTH, gc.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));

		return gc.getTime();
	}

	/**
	 * Retorna o mês.
	 * 
	 * @param date
	 *            Data que se deseja o mês.
	 * @return Date Data mês.
	 */
	public static String getStrMonth(Date date) {
		String retorno = null;

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.JANUARY) {
			retorno = "Janeiro";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.FEBRUARY) {
			retorno = "Fevereiro";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.MARCH) {
			retorno = "Março";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.APRIL) {
			retorno = "Abril";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.MAY) {
			retorno = "Maio";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.JUNE) {
			retorno = "Junho";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.JULY) {
			retorno = "Julho";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.AUGUST) {
			retorno = "Agosto";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.SEPTEMBER) {
			retorno = "Setembro";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.OCTOBER) {
			retorno = "Outubro";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.NOVEMBER) {
			retorno = "Novembro";
		} else if (gc.get(GregorianCalendar.MONTH) == GregorianCalendar.DECEMBER) {
			retorno = "Dezembro";
		}
		return retorno;
	}

	/**
	 * Retorna o dia da semana.
	 * 
	 * @param date
	 *            Data que se deseja o dia da semana.
	 * @return int Dia da semana de acordo com a classe GregorianCalendar.
	 */
	public static int dayOfWeek(Date date) {

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);

		return gc.get(GregorianCalendar.DAY_OF_WEEK);
	}

	/**
	 * Retorna um valor (String) formatado com o número de casas decimais
	 * informado.
	 * 
	 * @param valor
	 *            Número a ser formatado.
	 * @param dec
	 *            Número de casas decimais.
	 * @return String Número Formatado. Se o valor informado for nulo retorna
	 *         uma string vazia.
	 */
	public static String getNumberFormat(String valor, int dec) {
		String retorno = "";
		if (null != valor) {
			BigDecimal bigDecimal = new BigDecimal(valor).setScale(dec, BigDecimal.ROUND_HALF_EVEN);
			retorno = bigDecimal.toString();
		}
		return retorno;
	}

	public static String getNumberFormat(String valor) {
		String retorno = "";
		if (null != valor) {
			double d = Double.parseDouble(valor);
			BigDecimal bigDecimal = new BigDecimal(d / 100).setScale(2, BigDecimal.ROUND_HALF_EVEN);
			retorno = bigDecimal.toString();
		}
		return retorno;
	}

	public static String getNumberFormat(double valor, int dec) {
		String retorno = " ";
		BigDecimal bigDecimal = new BigDecimal(valor).setScale(dec, BigDecimal.ROUND_HALF_EVEN);
		retorno = bigDecimal.toString();
		return retorno;
	}

	/**
	 * Retorna um valor com o número de casas decimais informado.
	 * 
	 * @param valor
	 *            - double a ser formatado.
	 * @param dec
	 *            - Número de casas decimais.
	 * @return double - Número Formatado.
	 */
	public static double getRoundNumber(double valor, int dec) throws Exception {
		double retorno = valor;
		BigDecimal bigDecimal = new BigDecimal(valor).setScale(dec, BigDecimal.ROUND_HALF_EVEN);
		retorno = bigDecimal.doubleValue();
		return retorno;
	}

	public static String getLocalValueFormat(String valor) {
		String retorno = valor;
		String lang = System.getProperty("user.language");
		if (lang != null && lang.length() >= 2 && lang.substring(0, 2).equalsIgnoreCase("pt")) {
			retorno = valor.replace(".", ",");
		}
		return retorno;
	}

	/**
	 * Retorna um número com formato em moeda local.
	 * 
	 * @param valor
	 *            Número a ser formatado.
	 * @param dec
	 *            Número de casas decimais.
	 * @return String Número Formatado. Se o valor informado for nulo retorna
	 *         uma string vazia.
	 */
	public static String getNumberInstance(String valor, int dec) {
		String retorno = " ";
		if (null != valor) {
			// valor = getNumberFormat(valor, dec);
			double x = Double.parseDouble(valor);
			NumberFormat nf = NumberFormat.getNumberInstance();

			retorno = nf.format(x);

			// Verifica se retorno possui casas decimais
			int inic = 0;
			if (retorno.indexOf(",") == -1) {
				retorno = retorno + ",";
			} else {
				int x1 = retorno.lastIndexOf(",") + 1;
				inic = retorno.length() - x1;
			}
			for (int i = inic; i < dec; i++) {
				retorno = retorno + "0";
			}
			retorno = retorno.replaceAll("\\.", "");
		}
		return retorno;
	}

	/**
	 * Retorna a Data Atual do Sistema
	 * 
	 * @return Date - Data Atual do Sistema
	 */
	public static Date getDataAtual() {
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		TimeZone.setDefault(tz);
		Calendar ca = GregorianCalendar.getInstance(tz);
		return ca.getTime();
	}

	public static String getString(Object obj) {
		return (null == obj ? "" : obj.toString());
	}

	public static java.sql.Date getSqlDate(Date data) {
		return java.sql.Date.valueOf(FormatToolKit.dateToStr(data, "yyyy-MM-dd"));
	}

	public static Short getShortValue(Object value) {
		Short retorno = Short.valueOf("0");
		try {
			if (value instanceof Double) {
				retorno = ((Double) value).shortValue();
			} else if (value instanceof Long) {
				retorno = ((Long) value).shortValue();
			} else if (value instanceof Integer) {
				retorno = ((Integer) value).shortValue();
			} else if (value instanceof String) {
				retorno = Short.parseShort((String) value);
			}
		} catch (Exception e) {
		}
		return retorno;
	}

	/**
	 * Formata o valor com máscara monetária.
	 * 
	 * @param d
	 *            Valor a ser formatado.
	 * @return Valor formatado.
	 *         <p>
	 *         <b>Procedimentos</b>
	 *         <p>
	 *         Configura as propriedades do objeto nf para utilizar duas casas
	 *         decimais; <br>
	 *         Formar o valor informado como parâmetro;<br>
	 *         Retorna strin formatada.<br>
	 */
	public static String getCurrencyFormat(double d) {
		return getCurrencyFormatDecimal(d, 2);
	}

	/**
	 * Formata o valor com máscara monetária.
	 * 
	 * @param d
	 *            Valor a ser formatado.
	 * @return Valor formatado.
	 *         <p>
	 *         <b>Procedimentos</b>
	 *         <p>
	 *         Configura as propriedades do objeto nf para utilizar duas casas
	 *         decimais; <br>
	 *         Formar o valor informado como parâmetro;<br>
	 *         Retorna strin formatada.<br>
	 */
	public static String getCurrencyFormat(String valor) {
		DecimalFormat formatador = new DecimalFormat("0.##");
		String ret = formatador.format(new java.math.BigDecimal(valor));
		ret = getNumberInstance(ret, 2);
		return ret;
	}

	public static String getCurrencyFormatDecimal(double d, int qtdCasasDecimais) {
		return getCurrencyFormatDecimal(d, qtdCasasDecimais, true);
	}

	public static String getCurrencyFormatDecimal(double d, int qtdCasasDecimais, boolean mask) {
		String retorno = null;
		nf.setMaximumFractionDigits(qtdCasasDecimais);
		nf.setMinimumFractionDigits(qtdCasasDecimais);
		if (mask && d < 0.0d) {
			d = Math.abs(d);
			retorno = "(" + nf.format(d) + ")";
		} else {
			retorno = nf.format(d);
		}
		return retorno;
	}

	public static boolean isNumber(String valor) {
		boolean retorno = true;
		try {
			long number = Long.parseLong(valor);
		} catch (Exception e) {
			try {
				double number = Double.parseDouble(valor);
			} catch (Exception e2) {
				return false;
			}
		}
		return retorno;
	}

	public static strictfp double toDouble(String str) throws ParseException {
		return toDoubleDecimais(str, 2);
	}

	public static strictfp double toDoubleDecimais(String str, int qtdCasasDecimais) throws ParseException {
		double d = 0;

		nf.setMaximumFractionDigits(qtdCasasDecimais);
		nf.setMinimumFractionDigits(qtdCasasDecimais);

		if (str == null || str.trim().length() == 0) {
			str = "0";
		}

		d = nf.parse(str).doubleValue();

		return d;
	}

	public static int getIntValue(String value) {
		int valor = 0;
		try {
			if (value != null) {
				valor = Integer.parseInt(value.trim());
			}
		} catch (Exception e) {
		}
		return valor;
	}

	public static long getLongValue(String value) {
		long valor = 0;
		try {
			if (value != null) {
				valor = Long.parseLong(value);
			}
		} catch (Exception e) {
		}
		return valor;
	}

	public static short getShortValue(String value) {
		short valor = 0;
		try {
			if (value != null) {
				valor = Short.parseShort(value);
			}
		} catch (Exception e) {
		}
		return valor;
	}

	public static float getFloatValue(String value) {
		float valor = 0.0f;
		try {
			if (value != null) {
				valor = Float.parseFloat(value);
			}
		} catch (Exception e) {
		}
		return valor;
	}

	public static double getDoubleValue(String value) {
		double valor = 0.0d;
		try {
			if (value != null) {
				valor = Double.parseDouble(value);
			}
		} catch (Exception e) {
		}
		return valor;
	}

	public static Date getDateValue(String value) {
		Date valor = null;
		try {
			if (value != null) {
				valor = FormatToolKit.strToDate(value, "dd/MM/yyyy");
			}
		} catch (Exception e) {
		}
		return valor;
	}

	public static String getStringValue(String value) {
		String valor = null;
		try {
			if (value != null && value.length() > 0) {
				valor = value;
			}
		} catch (Exception e) {
		}
		return valor;
	}

	public static Date stringToDate(String stringDate) {

		Date date = null;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt_BR"));
			date = sdf.parse(stringDate);
		} catch (ParseException e) {
		}

		return date;
	}

	public static int deductDates(Date initialDate, Date finalDate) {

		if (initialDate == null || finalDate == null) {
			return 0;
		}
		int days = (int) ((finalDate.getTime() - initialDate.getTime()) / (24 * 60 * 60 * 1000));
		return (days > 0 ? days : 0);
	}

	public static String dateToString(Date date) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", new Locale("pt_BR"));
		String dateFormated = sdf.format(date);

		return dateFormated;
	}

	public static Date clearHour(Date date) {
		return (stringToDate(dateToString(date)));
	}

	public static int getWorkingDays(Date initialDate, Date finalDate) {
		Calendar startCal = Calendar.getInstance();
		startCal.setTime(initialDate);

		Calendar endCal = Calendar.getInstance();
		endCal.setTime(finalDate);

		int workingDays = 0;

		if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
			startCal.setTime(finalDate);
			endCal.setTime(initialDate);
		}

		while (startCal.getTimeInMillis() <= endCal.getTimeInMillis()) {
			if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
					&& startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				workingDays++;
			}
			startCal.add(Calendar.DAY_OF_MONTH, 1);
		}
		return workingDays;
	}

	public static String replaceWithZeros(String valor, int tamanho) {
		String retorno = "";
		if (valor != null) {

			int len = valor.length();
			int dif = tamanho - len;
			if (len >= tamanho) {
				retorno = valor.substring(0, tamanho);
			} else {
				while (dif > 0) {
					retorno = "0" + retorno;
					dif--;
				}
				retorno = retorno + valor;
			}
		}
		return retorno;
	}

	public static String replaceWithZeros(double vlr, int tamanho) {
		String valor = FormatToolKit.getNumberFormat(vlr, 2);
		tamanho = (tamanho < 3 ? 3 : tamanho);
		valor = valor.replaceAll("\\.", "");
		valor = FormatToolKit.replaceWithZeros(valor, tamanho);
		return valor;
	}

	/**
	 * Completa o preenchimento de uma String com um valor especifico, a direita
	 * ou a esquerda
	 * 
	 * @param valor
	 * @param tamanho
	 * @param fill
	 * @param posicao
	 * @return
	 */
	public static String replaceWith(String valor, int tamanho, String fill, int posicao) {
		String retorno = "";
		if (valor == null) {
			valor = "";
		}
		valor = valor.trim();
		int len = valor.length();
		if (len >= tamanho) {
			retorno = valor.substring(0, tamanho);
		} else {
			int dif = tamanho - len;
			for (int i = 0; i < dif; i++) {
				retorno = retorno + fill;
			}
			if (posicao == POSICAO_LEFT) { // Left
				retorno = retorno + valor;
			} else {
				retorno = valor + retorno;
			}
		}
		return retorno;
	}

	public static String replaceWithBlank(String valor, int tamanho) {
		return replaceWith(valor, tamanho, BLANK, POSICAO_RIGHT);
	}

	public static long getConta(long conta, short digito) {
		long retorno = 0;
		if (conta > 0) {
			String valor = String.valueOf(conta) + String.valueOf(digito);
			retorno = Long.parseLong(valor);
		}
		return retorno;
	}

	/**
	 * Método para Copiar uma obejto.
	 * 
	 * @param objetoOriginal
	 *            - Objeto a ser copiado.
	 * @return Object - Objeto Copiado.
	 */
	public static Object clone(Object objetoOriginal) {
		Object obj = null;
		if (objetoOriginal != null) {
			Vector original = new Vector();
			original.addElement(objetoOriginal);
			Vector clone = (Vector) original.clone();
			obj = clone.get(0);
		}
		return obj;
	}

	public static long obterHora(String valor) throws Exception {
		String[] valores = valor.split(":");
		long hora = Long.parseLong(valores[0]) * 60 * 60;
		long minuto = Long.parseLong(valores[1]) * 60;
		long segundo = Long.parseLong(valores[2]);
		long time = hora + minuto + segundo;
		return time;
	}

	public static String trim(String valor) {
		if (valor != null) {
			valor = valor.trim();
		}
		return valor;
	}

	public static String getDB2Value(Object valor) throws Exception {
		String retorno = "00";
		try {
			if (valor != null) {
				BigDecimal value = (BigDecimal) valor;
				BigDecimal bigDecimal = value.setScale(2, BigDecimal.ROUND_HALF_EVEN);
				retorno = bigDecimal.toString();
				retorno = retorno.replace(".", "");
			}
		} catch (Exception e) {
		}
		return retorno;
	}

	public static Integer getNumeroCPF(String cpf) {
		Integer numeroCPF = new Integer(0);
		try {
			if (cpf != null && cpf.length() >= 11) {
				numeroCPF = Integer.valueOf(getIntValue(cpf.substring(0, 9)));
			}
		} catch (Exception e) {
		}
		return numeroCPF;
	}

	public static Short getDigitoCPF(String cpf) {
		Short digitoCPF = new Short("0");
		try {
			if (cpf != null && cpf.length() >= 11) {
				digitoCPF = Short.parseShort(cpf.substring(10, 11), 10);
			}
		} catch (Exception e) {
		}
		return digitoCPF;
	}

	public static Double getDoubleValue(String value, int decimal) {
		Double retorno = new Double(0.00);
		if (value != null && value.length() > 0) {
			try {
				String valor = value.substring(0, value.length() - 2) + "." + value.substring(value.length() - 2);
				retorno = Double.valueOf(valor);
			} catch (Exception e) {
			}
		}
		return retorno;
	}

	public static BigDecimal getBigDecimalValue(String value, int decimal) {
		BigDecimal bd = BigDecimal.ZERO;
		if (value != null && value.length() > 0) {
			try {
				String valor = value.substring(0, value.length() - 2) + "." + value.substring(value.length() - 2);
				double d = Double.valueOf(valor);
				bd = BigDecimal.valueOf(d).setScale(decimal, BigDecimal.ROUND_HALF_DOWN);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bd;
	}

}