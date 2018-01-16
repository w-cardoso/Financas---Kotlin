package fiap.com.br.finanask.ui

import android.view.View
import fiap.com.br.finanask.extension.formataParaBrasileiro
import fiap.com.br.finanask.model.Resumo
import fiap.com.br.finanask.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*

class ResumoView(private val view: View,
                 transacoes: List<Transacao>) {

    private val resumo = Resumo(transacoes)

    fun adicionaReceita() {
        val totalReceita = resumo.receita()
        view.resumo_card_receita.text = totalReceita.formataParaBrasileiro()
    }

    fun adicionaDespesa() {
        val totalDespesa = resumo.despesa()
        view.resumo_card_despesa.text = totalDespesa.formataParaBrasileiro()
    }

    fun adicionaTotal() {
        val total = resumo.total()
        view.resumo_card_total.text = total.formataParaBrasileiro()
    }
}