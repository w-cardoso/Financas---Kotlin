package fiap.com.br.finanask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import fiap.com.br.finanask.R
import fiap.com.br.finanask.delegate.TransacaoDelegate
import fiap.com.br.finanask.model.Tipo
import fiap.com.br.finanask.model.Transacao
import fiap.com.br.finanask.ui.ResumoView
import fiap.com.br.finanask.ui.adapter.ListaTransacoesAdapter
import fiap.com.br.finanask.ui.dialog.AlteraTransacaoDialog
import fiap.com.br.finanask.ui.dialog.adicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()
    private val viewDaActivity by lazy {
        window.decorView
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita
                .setOnClickListener {
                    chamaDialogDeAdicao(Tipo.RECEITA)
                }

        lista_transacoes_adiciona_despesa
                .setOnClickListener {
                    chamaDialogDeAdicao(Tipo.DESPESA)
                }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        adicionaTransacaoDialog(viewDaActivity as ViewGroup, this)
                .configuraDialog(tipo, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        adicionaTransacao(transacao)
                        lista_transacoes_adiciona_menu.close(true)
                    }

                })
    }

    private fun adicionaTransacao(transacao: Transacao) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(viewDaActivity, this, transacoes)
        resumoView.atualiza()
    }

    private fun configuraLista() {
        val listaTransacoesAdapter = ListaTransacoesAdapter(transacoes, this)
        with(lista_transacoes_listview) {
            adapter = listaTransacoesAdapter
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogDeAlteracao(transacao, position)
            }

        }
    }

    private fun chamaDialogDeAlteracao(transacao: Transacao, position: Int) {
        AlteraTransacaoDialog(viewDaActivity as ViewGroup, context = this)
                .chama(transacao, object : TransacaoDelegate {
                    override fun delegate(transacao: Transacao) {
                        altera(transacao, position)
                    }

                })
    }

    private fun altera(transacao: Transacao, position: Int) {
        transacoes[position] = transacao
        atualizaTransacoes()
    }
}
