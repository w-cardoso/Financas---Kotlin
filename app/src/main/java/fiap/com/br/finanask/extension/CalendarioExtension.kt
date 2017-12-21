package fiap.com.br.finanask.extension

import java.text.SimpleDateFormat
import java.util.*


fun Calendar.formataBrasileiro(): String? {
    val formatoBrasileiro = "dd/MM/yyyy"
    val format = SimpleDateFormat(formatoBrasileiro)
    return format.format(this.time)

}