package fiap.com.br.finanask.delegate

import fiap.com.br.finanask.model.Transacao


interface TransacaoDelegate {

    fun delegate(transacao: Transacao)
}