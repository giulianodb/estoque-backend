package br.org.demaosunidas.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class NumeroUtil {

	public static final String MULTI = "*";
	public static final String SOMA = "+";
	public static final String SUB = "-";
	public static final String DIV = "/";
	public static final String ABRE = "(";
	public static final String FECHA = ")";
	
    private static final String[] UNIDADES = {"", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove"};
    private static final String[] DEZ_A_DEZENOVE = {"dez", "onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"};
    private static final String[] DEZENAS = {"", "", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"};
    private static final String[] CENTENAS = {"", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"};

    private static final String[] MILHARES_SINGULAR = {"", "mil", "milhão", "bilhão", "trilhão", "quadrilhão", "quintilhão", "sextilhão", "setilhão", "otrilhão", "decilhão"};
    private static final String[] MILHARES_PLURAL = {"", "mil", "milhões", "bilhões", "trilhões", "quadrilhões", "quintilhões", "sextilhões", "setilhões", "otrilhões", "decilhões"};
    
    
    public static String formatarMoeda(BigDecimal valor) {
        // Define o formato da moeda brasileira
        NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        formatoMoeda.setMinimumFractionDigits(2);
        formatoMoeda.setMaximumFractionDigits(2);
        // Formata o valor usando o formato da moeda
        return formatoMoeda.format(valor);
    }
    
    
    public static String converterValorPorExtenso(BigDecimal valor) {
        long parteInteira = valor.longValue();
        int centavos = valor.remainder(BigDecimal.ONE).multiply(new BigDecimal(100)).intValue();

        StringBuilder extenso = new StringBuilder();

        extenso.append(converterParteInteira(parteInteira));
        extenso.append(" reais");

        if (centavos > 0) {
            extenso.append(" e ");
            extenso.append(converterCentavos(centavos));
            extenso.append(" centavos");
        }

        return extenso.toString();
    }

    private static String converterParteInteira(long parteInteira) {
        if (parteInteira == 0) {
            return "zero";
        }

        StringBuilder extenso = new StringBuilder();

        int grupo = 0;
        while (parteInteira > 0) {
            int bloco = (int) (parteInteira % 1000);
            if (bloco > 0) {
                if (extenso.length() > 0) {
                    extenso.insert(0, " e ");
                }
                extenso.insert(0, converterBloco(bloco, grupo));
            }

            parteInteira /= 1000;
            grupo++;
        }

        return extenso.toString();
    }

    private static String converterBloco(int bloco, int grupo) {
        StringBuilder extensoBloco = new StringBuilder();

        int centenas = bloco % 100;
        int dezenas = (bloco % 1000) / 100;
        int unidades = bloco / 1000;

        if (unidades > 0) {
            if (extensoBloco.length() > 0) {
                extensoBloco.append(" e ");
            }
            extensoBloco.append(converterCentenas(unidades));
            extensoBloco.append(" ").append(unidades == 1 ? MILHARES_SINGULAR[grupo] : MILHARES_PLURAL[grupo]);
        }

        if (centenas > 0 || dezenas > 0) {
            if (extensoBloco.length() > 0) {
                extensoBloco.append(" e ");
            }
            extensoBloco.append(converterCentenas(centenas + dezenas * 100));
        }

        return extensoBloco.toString();
    }

    private static String converterCentavos(int centavos) {
        if (centavos == 0) {
            return "zero";
        }

        if (centavos < 10) {
            return UNIDADES[centavos];
        } else if (centavos < 20) {
            return DEZ_A_DEZENOVE[centavos - 10];
        } else {
            int unidade = centavos % 10;
            int dezena = centavos / 10;
            return DEZENAS[dezena] + (unidade > 0 ? " e " + UNIDADES[unidade] : "");
        }
    }
    
    private static String converterCentenas(int centenas) {
        if (centenas == 100) {
            return "cem";
        }

        int dezena = centenas % 100;
        int centena = centenas / 100;

        StringBuilder extenso = new StringBuilder(CENTENAS[centena]);

        if (dezena > 0) {
            if (extenso.length() > 0) {
                extenso.append(" e ");
            }
            extenso.append(converterDezenas(dezena));
        }

        return extenso.toString();
    }
    
    private static String converterDezenas(int dezenas) {
        if (dezenas < 10) {
            return UNIDADES[dezenas];
        } else if (dezenas < 20) {
            return DEZ_A_DEZENOVE[dezenas - 10];
        } else {
            int unidade = dezenas % 10;
            int dezena = dezenas / 10;
            return DEZENAS[dezena] + (unidade > 0 ? " e " + UNIDADES[unidade] : "");
        }
    }

	public static Float deixarFloatDuasCasas(Float numero) {
		if (numero != null) {
			String numeroEmString = numero.toString();
			numeroEmString = numeroEmString.replace(".", ",");

			String numeroSplit[] = numeroEmString.split(",");
			String numeroFloat = "";

			if (numeroSplit.length >= 2) {
				if (numeroSplit[1].length() == 1) {
					numeroSplit[1] = numeroSplit[1] + "0";
				}
				if (numeroSplit[1].length() >= 2) {
					numeroFloat = numeroSplit[0] + "." + numeroSplit[1].substring(0, 2);
				}
			}

			return Float.valueOf(numeroFloat);
		} else {
			return numero;
		}
	}

	public static Float deixarFloatDuasCasasSimplificado(Float numero) {

		DecimalFormat df = new DecimalFormat("#.##");
		String resulta = df.format(numero);
		resulta = resulta.replaceAll(",", ".");
		return Float.valueOf(resulta);
	}

	public static Float multiplicarDinheiro(Float x, Float y, Integer casas) {
		if (x == null) {
			x = 0f;
		}

		if (y == null) {
			y = 0f;
		}
		BigDecimal a = new BigDecimal(x.toString());
		BigDecimal b = new BigDecimal(y.toString());

		a.setScale(casas, RoundingMode.DOWN);
		b.setScale(casas, RoundingMode.DOWN);

		Float retorno = a.multiply(b).floatValue();

		return (retorno);
	}

	public static Float DividirDinheiro(Float x, Float y, Integer casas) {
		if (x == null) {
			x = 0f;
		}

		if (y == null) {
			y = 0f;
		}
		BigDecimal a = new BigDecimal(x.toString());
		BigDecimal b = new BigDecimal(y.toString());

		Float retorno = a.divide(b, casas, RoundingMode.DOWN).floatValue();

		return retorno;
	}

	public static Float somarDinheiro(Float x, Float y, Integer casas) {
		if (x == null) {
			x = 0f;
		}

		if (y == null) {
			y = 0f;
		}

		BigDecimal a = new BigDecimal(x.toString());
		BigDecimal b = new BigDecimal(y.toString());

		a.setScale(casas, RoundingMode.DOWN);
		b.setScale(casas, RoundingMode.DOWN);

		Float retorno = a.add(b).floatValue();

		return retorno;
	}

	public static Float diminuirDinheiro(Float x, Float y, Integer casas) {
		if (x == null) {
			x = 0f;
		}

		if (y == null) {
			y = 0f;
		}
		BigDecimal a = new BigDecimal(x.toString());
		BigDecimal b = new BigDecimal(y.toString());

		a.setScale(casas, RoundingMode.DOWN);
		b.setScale(casas, RoundingMode.DOWN);

		Float retorno = a.subtract(b).floatValue();

		return retorno;
	}

	/**
	 * da o valor em porcento de uma valor
	 */

	public static Float porcentagem(Float valor, Float porcentagem) {
//		BigDecimal a = new BigDecimal(valor.toString());
//		BigDecimal b = new BigDecimal(porcentagem.toString());

		Float a = multiplicarDinheiro(valor, porcentagem, 3);
		Float b = DividirDinheiro(a, 100f, 3);

		return b;

	}

	/**
	 * 
	 * @param valor
	 * @param porcentagem
	 * @return
	 */

	public static Float valorDaPorcentagem(Float valor, Float porcentagem) {
//		BigDecimal a = new BigDecimal(valor.toString());
//		BigDecimal b = new BigDecimal(porcentagem.toString());

		Float a = DividirDinheiro(porcentagem, 100f, 3);
		Float b = multiplicarDinheiro(a, valor, 3);

		return b;

	}

	public static Float expressao(List<String> expressao) {

		return null;
	}


}
