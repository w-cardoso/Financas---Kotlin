package fiap.com.br.finanask.model

import java.math.BigDecimal

/**
 * Created by re034850 on 16/01/2018.
 */
class Resumo(private val transacoes: List<Transacao>) {

    val receita get() = somarPor(Tipo.RECEITA)

    val despesa get() = somarPor(Tipo.DESPESA)

    val total get() = receita.subtract(despesa)


    fun somarPor(tipo: Tipo): BigDecimal {
        val somaDeTransacaoPorTipo = transacoes
                .filter { it.tipo == tipo }
                .sumByDouble { it.valor.toDouble() }
        return BigDecimal(somaDeTransacaoPorTipo)
    }


}