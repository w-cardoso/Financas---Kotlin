package fiap.com.br.finanask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import fiap.com.br.finanask.R
import fiap.com.br.finanask.model.Tipo


class adicionaTransacaoDialog(
        viewGroup: ViewGroup,
        context: Context) : FormularioTransacaoDialog(context, viewGroup) {
    override val tituloBotaoPositivo: String
        get() = "Adicionar"


    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa


    }


}