package fiap.com.br.finanask.ui.activity

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import fiap.com.br.finanask.R
import fiap.com.br.finanask.extension.formataBrasileiro
import fiap.com.br.finanask.model.Tipo
import fiap.com.br.finanask.model.Transacao
import fiap.com.br.finanask.ui.ResumoView
import fiap.com.br.finanask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)



        configuraResumo()

        configuraLista()

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

                    val adapter = ArrayAdapter
                            .createFromResource(this,
                                    R.array.categorias_de_receita,
                                    android.R.layout.simple_spinner_dropdown_item)

                    viewCriada.form_transacao_categoria.adapter = adapter


                    AlertDialog.Builder(this)
                            .setTitle(R.string.adiciona_receita)
                            .setView(viewCriada)
                            .setPositiveButton("Adiconar", { dialoginterface, which ->

                                val valorEmTexto = viewCriada.form_transacao_valor.text.toString()
                                val dataEmTexto = viewCriada.form_transacao_data.text.toString()
                                val categoriaEmTexto = viewCriada.form_transacao_categoria.selectedItem.toString()

                                val valor = try {
                                    BigDecimal(valorEmTexto)
                                } catch (exception: NumberFormatException) {
                                    Toast.makeText(this, "Falha na conversão de valor", Toast.LENGTH_LONG).show()
                                    BigDecimal.ZERO
                                }

                                val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
                                val dataCovertida: Date = formatoBrasileiro.parse(dataEmTexto)
                                val data = Calendar.getInstance()
                                data.time = dataCovertida

                                val transacaoCriada = Transacao(tipo = Tipo.RECEITA,
                                        valor = valor,
                                        data = data,
                                        categoria = categoriaEmTexto)

                                atualizaTransacoes(transacaoCriada)
                                lista_transacoes_adiciona_menu.close(true)


                            })
                            .setNegativeButton("Cancelar", null)
                            .show()

                }
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val view = window.decorView
        val resumoView = ResumoView(view, this, transacoes)
        resumoView.atualiza()
    }


    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }
}
