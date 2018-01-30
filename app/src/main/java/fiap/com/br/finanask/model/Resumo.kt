package fiap.com.br.finanask.model

import java.math.BigDecimal

/**
 * Created by re034850 on 16/01/2018.
 */
class Resumo(private val transacoes: List<Transacao>) {

    fun receita(): BigDecimal {
        /*var totalReceita = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalReceita = totalReceita.plus(transacao.valor)
            }
        }*/

        //utilizando expressões lambdas e funções anonimas
        val somaDeReceita: Double = transacoes
                .filter{ transacao -> transacao.tipo == Tipo.RECEITA }
                .sumByDouble{ transacao -> transacao.valor.toDouble() }
        return BigDecimal(somaDeReceita)
    }

    fun despesa(): BigDecimal {
        /*var totalDespesa = BigDecimal.ZERO
        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalDespesa = totalDespesa.plus(transacao.valor)
            }
        }*/

        val somaDespesa: Double = transacoes
                .filter { transacao -> transacao.tipo == Tipo.DESPESA }
                .sumByDouble { transacao -> transacao.valor.toDouble() }

        return BigDecimal(somaDespesa)
    }

    fun total () : BigDecimal{
        return receita().subtract(despesa())
    }

}