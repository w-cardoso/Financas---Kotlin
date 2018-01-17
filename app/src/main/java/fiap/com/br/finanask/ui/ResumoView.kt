package fiap.com.br.finanask.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import fiap.com.br.finanask.R
import fiap.com.br.finanask.extension.formataParaBrasileiro
import fiap.com.br.finanask.model.Resumo
import fiap.com.br.finanask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(private val view: View,
                 private val context: Context,
                 transacoes: List<Transacao>) {

    private val resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()
        view.resumo_card_receita.setTextColor(corReceita)
        view.resumo_card_receita.text = totalReceita.formataParaBrasileiro()
    }

    fun adicionaDespesa() {
        val totalDespesa = resumo.despesa()
        view.resumo_card_despesa.setTextColor(corDespesa)
        view.resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()
    }

    fun adicionaTotal() {
        val total = resumo.total()
        if (total.compareTo(BigDecimal.ZERO) >= 0) {
            view.resumo_card_total
                    .setTextColor(corReceita)
        } else {
            view.resumo_card_total
                    .setTextColor(corDespesa)
        }
        view.resumo_card_total.text = total.formataParaBrasileiro()
    }
}