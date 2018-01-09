package fiap.com.br.finanask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import fiap.com.br.finanask.R
import fiap.com.br.finanask.extension.formataBrasileiro
import fiap.com.br.finanask.extension.formataParaBrasileiro
import fiap.com.br.finanask.extension.limitaEmAte
import fiap.com.br.finanask.model.Tipo
import fiap.com.br.finanask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(private val transacoes: List<Transacao>,
                             private val context: Context) : BaseAdapter() {

    private val limiteDaCategoria = 14

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]

        adicionaValor(transacao, viewCriada)

        adicionaIcone(transacao, viewCriada)

        adicionaCategoria(viewCriada, transacao)

        adicionaData(viewCriada, transacao)

        return viewCriada
    }

    private fun adicionaValor(transacao: Transacao, viewCriada: View) {

        val cor: Int = corPorTipo(transacao.tipo)
        viewCriada.transacao_valor.setTextColor(cor)

        viewCriada.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPorTipo(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {

            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)

    }

    private fun adicionaIcone(transacao: Transacao, viewCriada: View) {
        val icone = iconePor(transacao.tipo)
        viewCriada.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.drawable.icone_transacao_item_receita
        }
        return R.drawable.icone_transacao_item_despesa

    }

    private fun adicionaCategoria(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_categoria.text = transacao.categoria.limitaEmAte(limiteDaCategoria)
    }

    private fun adicionaData(viewCriada: View, transacao: Transacao) {
        viewCriada.transacao_data.text = transacao.data.formataBrasileiro()
    }


    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size

    }
}


