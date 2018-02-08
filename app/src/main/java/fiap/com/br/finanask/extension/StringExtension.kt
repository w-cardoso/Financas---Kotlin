package fiap.com.br.finanask.extension

import java.text.SimpleDateFormat
import java.util.*

fun String.limitaEmAte(caracteres: Int): String {
    if (this.length > caracteres) {
        return "${this.substring(0, caracteres)}..."
    }
    return this
}

fun String.converteParaCalendar(): Calendar {
    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dataCovertida: Date = formatoBrasileiro.parse(this)
    val data = Calendar.getInstance()
    data.time = dataCovertida
    return data
}
