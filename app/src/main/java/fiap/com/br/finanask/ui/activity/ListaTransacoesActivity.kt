package fiap.com.br.finanask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import fiap.com.br.finanask.R
import fiap.com.br.finanask.model.Tipo
import fiap.com.br.finanask.model.Transacao
import fiap.com.br.finanask.ui.ResumoView
import fiap.com.br.finanask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = transacoesDeExemplo()

        configuraResumo(transacoes)

        configuraLista(transacoes)

        lista_transacoes_adiciona_receita
                .setOnClickListener {
                    Toast.makeText(this@ListaTransacoesActivity,
                            "clique de receita", Toast.LENGTH_LONG).show();
                }
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
        val view = window.decorView
        val resumoView = ResumoView(view, this, transacoes)
        resumoView.atualiza()
    }

    private fun transacoesDeExemplo(): List<Transacao> {
        return listOf(
                Transacao(valor = BigDecimal(100.0), categoria = "Economia Baseada em Bitcoin", tipo = Tipo.RECEITA),
                Transacao(valor = BigDecimal(300.0), categoria = "Comida", tipo = Tipo.DESPESA),
                Transacao(valor = BigDecimal(100.0), categoria = "Economia Baseada em Bitcoin", tipo = Tipo.RECEITA),
                Transacao(valor = BigDecimal(300.0), categoria = "Comida", tipo = Tipo.DESPESA))
    }

    private fun configuraLista(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}
