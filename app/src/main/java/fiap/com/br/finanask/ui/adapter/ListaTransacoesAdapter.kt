package fiap.com.br.finanask.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import fiap.com.br.finanask.R
import fiap.com.br.finanask.extension.formataBrasileiro
import fiap.com.br.finanask.model.Tipo
import fiap.com.br.finanask.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

/**
 * Created by re034850 on 19/12/2017.
 */
class ListaTransacoesAdapter(transacoes: List<Transacao>,
                             context: Context) : BaseAdapter() {

    private val transacoes = transacoes
    private val context = context

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
        val viewCriada = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]

        if (transacao.tipo == Tipo.RECEITA) {
            viewCriada.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.receita))
            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        } else {
            viewCriada.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.despesa))
            viewCriada.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }

        viewCriada.transacao_valor.text = transacao.valor.toString()
        viewCriada.transacao_categoria.text = transacao.categoria

        val dataFormatada = transacao.data.formataBrasileiro()
        viewCriada.transacao_data.text = dataFormatada




        return viewCriada
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


