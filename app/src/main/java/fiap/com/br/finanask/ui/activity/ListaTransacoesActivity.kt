package fiap.com.br.finanask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import fiap.com.br.finanask.R
import fiap.com.br.finanask.model.Tipo
import fiap.com.br.finanask.model.Transacao
import fiap.com.br.finanask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf(
                Transacao(valor = BigDecimal(100.0), categoria = "Economia Baseada em Bitcoin", tipo = Tipo.RECEITA),
                Transacao(valor = BigDecimal(300.0), categoria = "Comida", tipo = Tipo.DESPESA))


        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}
