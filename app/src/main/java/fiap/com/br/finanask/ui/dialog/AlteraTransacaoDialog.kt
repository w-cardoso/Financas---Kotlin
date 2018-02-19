package fiap.com.br.finanask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import fiap.com.br.finanask.R
import fiap.com.br.finanask.extension.formataBrasileiro
import fiap.com.br.finanask.model.Tipo
import fiap.com.br.finanask.model.Transacao


class AlteraTransacaoDialog(
        viewGroup: ViewGroup,
        private val context: Context) : FormularioTransacaoDialog(context, viewGroup) {
    override val tituloBotaoPositivo: String
        get() = "Alterar"

    fun chama(transacao: Transacao, delegate: (transacao:Transacao) -> Unit) {
        val tipo = transacao.tipo
        super.chama(tipo, delegate)
        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao) {
        val tipo = transacao.tipo
        inicializaCampoValor(transacao)
        inicializaCampoData(transacao)
        inicializaCampoCategoria(tipo, transacao)
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }

    private fun inicializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formataBrasileiro())
    }

    private fun inicializaCampoCategoria(tipo: Tipo, transacao: Transacao) {
        val categoriasRetornadas = context.resources.getStringArray(categoriaPor(tipo))
        val posicaoCategorias = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategorias, true)
    }

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }
}