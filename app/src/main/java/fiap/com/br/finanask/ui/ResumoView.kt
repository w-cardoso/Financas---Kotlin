package fiap.com.br.finanask.ui

import android.view.View
import fiap.com.br.finanask.extension.formataParaBrasileiro
import fiap.com.br.finanask.model.Tipo
import fiap.com.br.finanask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val view: View,
                 private val transacoes: List<Transacao>) {

    fun adicionaReceita() {
        var totalRecita = BigDecimal.ZERO

        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.RECEITA) {
                totalRecita = totalRecita.plus(transacao.valor)
            }
        }
        view.resumo_card_receita.text = totalRecita.formataParaBrasileiro()
    }

    fun adicionaDespesa() {
        var totalRecita = BigDecimal.ZERO

        for (transacao in transacoes) {
            if (transacao.tipo == Tipo.DESPESA) {
                totalRecita = totalRecita.plus(transacao.valor)
            }
        }
        view.resumo_card_despesa.text = totalRecita.formataParaBrasileiro()
    }
}