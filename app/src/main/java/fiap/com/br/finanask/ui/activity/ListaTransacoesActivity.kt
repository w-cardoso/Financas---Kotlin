package fiap.com.br.finanask.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fiap.com.br.finanask.R
import fiap.com.br.finanask.extension.formataBrasileiro
import fiap.com.br.finanask.model.Tipo
import fiap.com.br.finanask.model.Transacao
import fiap.com.br.finanask.ui.ResumoView
import fiap.com.br.finanask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = transacoesDeExemplo()

        configuraResumo(transacoes)

        configuraLista(transacoes)

        lista_transacoes_adiciona_receita
                .setOnClickListener {
                    val view: View = window.decorView
                    val viewCriada = LayoutInflater.from(this)
                            .inflate(R.layout.form_transacao, view as ViewGroup, false)

                    val ano = 2018
                    val mes = 1
                    val dia = 5

                    val hoje = Calendar.getInstance()
                    viewCriada.form_transacao_data.setText(hoje.formataBrasileiro())

                    viewCriada.form_transacao_data
                            .setOnClickListener {
                                DatePickerDialog(this,
                                        DatePickerDialog.OnDateSetListener { view, ano, mes, dia ->
                                            val dataSelecionada = Calendar.getInstance()
                                            dataSelecionada.set(ano, mes, dia)
                                            viewCriada.form_transacao_data
                                                    .setText(dataSelecionada.formataBrasileiro())
                                        }, ano, mes, dia).show()
                            }

                    AlertDialog.Builder(this)
                            .setTitle(R.string.adiciona_receita)
                            .setView(viewCriada)
                            .show()

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
