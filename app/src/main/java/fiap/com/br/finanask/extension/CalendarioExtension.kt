package fiap.com.br.finanask.extension

import java.text.SimpleDateFormat
import java.util.*


fun Calendar.formataBrasileiro(): String? {
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    val dataFormatada = format.format(this.time)
    return dataFormatada
}