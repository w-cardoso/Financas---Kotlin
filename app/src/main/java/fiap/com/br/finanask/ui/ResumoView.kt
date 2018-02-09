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

class ResumoView(private val view: View?,
                 context: Context,
                 transacoes: List<Transacao>) {

    private val resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza() {
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val totalReceita = resumo.receita
        view?.let {
            with(it.resumo_card_receita) {
                setTextColor(corReceita)
                text = totalReceita.formataParaBrasileiro()
            }

        }
    }

    private fun adicionaDespesa() {
        val totalDespesa = resumo.despesa
        view?.let {
            with(it.resumo_card_despesa) {
                setTextColor(corDespesa)
                text = totalDespesa.formataParaBrasileiro()
            }
        }
    }

    private fun adicionaTotal() {
        val total = resumo.total
        val cor = corPor(total)
        view?.let {
            with(it.resumo_card_total) {
                setTextColor(cor)
                text = total.formataParaBrasileiro()
            }
        }
    }

    private fun corPor(valor: BigDecimal): Int {
        if (valor.compareTo(BigDecimal.ZERO) >= 0) {
            return corReceita
        }
        return corDespesa

    }

}