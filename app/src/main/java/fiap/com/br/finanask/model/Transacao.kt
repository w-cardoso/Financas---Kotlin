package fiap.com.br.finanask.model

import java.math.BigDecimal
import java.util.*

/**
 * Created by re034850 on 21/12/2017.
 */
class Transacao(val valor: BigDecimal,
                val categoria: String = "indefinido",
                val tipo: Tipo,
                val data: Calendar = Calendar.getInstance())

