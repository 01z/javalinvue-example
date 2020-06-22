package javalinvue

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.javalin.http.Context
import io.javalin.http.NotFoundResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.random.Random

enum class UserState { OK, WARN, ERROR }
data class User(val id: String,
                val name: String,
                val email: String,
                val quota: Int = Random.nextInt(0,100),
                val state: UserState = when {
                    quota >= 80 -> UserState.ERROR
                    quota >= 50 -> UserState.WARN
                    else -> UserState.OK
                },
                @GsonHide() val hiddenText: String = "XXX",
                @SerializedName("details") val userDetails: UserDetails?,
                @SerializedName("secret") val _hiddenDetail: String = "IamSecret" )

val DATE_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy")

data class UserDetails(val dateOfBirth: LocalDate,
                       @Transient val salary: String) {
    constructor(dateString: String, salary: String) : this(
        LocalDate.parse(dateString, DATE_FORMATTER),
        salary
    )
}

val users = setOf<User>(
    User(id = "1", name = "John", email = "john@fake.co", userDetails = UserDetails("21.02.1964", "2773 JB")),
    User(id = "2", name = "Mary", email = "mary@fake.co", userDetails = UserDetails("12.05.1994", "1222 JB")),
    User(id = "3", name = "Dave", email = "dave@fake.co", userDetails = UserDetails("01.05.1984", "1833 JB")),
    User(id = "4", name = "Jane", email = "jane@fake.co", userDetails = UserDetails("30.12.1989", "1532 JB")),
    User(id = "5", name = "Eric", email = "eric@fake.co", userDetails = UserDetails("14.09.1973", "2131 JB")),
    User(id = "6", name = "Gina", email = "gina@fake.co", userDetails = UserDetails("16.08.1977", "1982 JB")),
    User(id = "7", name = "Ryan", email = "ryan@fake.co", userDetails = UserDetails("07.11.1988", "1638 JB")),
    User(id = "8", name = "Judy", email = "judy@fake.co", userDetails = UserDetails("05.01.1959", "2983 JB"))
)

object UserController {

    fun getAll(ctx: Context) {
        ctx.json(users.map { it.copy(userDetails = null) }) // remove sensitive information
    }

    fun getOne(ctx: Context) {
        val user = users.find { it.id == ctx.pathParam("user-id") } ?: throw NotFoundResponse()
        ctx.json(user)
    }

}
