package javalinvue

import io.javalin.Javalin
import io.javalin.core.security.Role
import io.javalin.core.security.SecurityUtil.roles
import io.javalin.core.util.Header
import io.javalin.http.Context
import io.javalin.http.staticfiles.Location
import io.javalin.plugin.json.JavalinJson
import io.javalin.plugin.rendering.vue.JavalinVue
import io.javalin.plugin.rendering.vue.VueComponent
import java.io.File
import java.nio.file.Paths

enum class AppRole : Role { ANYONE, LOGGED_IN }

fun main(args: Array<String>) {

    val app = Javalin.create { config ->
//        val (path,location) = getPathAndLocation("/public/app.js")
        config.addStaticFiles("src/main/resources/public", Location.EXTERNAL)

        config.enableWebjars()
        config.accessManager { handler, ctx, permittedRoles ->
            when {
                AppRole.ANYONE in permittedRoles -> handler.handle(ctx)
                AppRole.LOGGED_IN in permittedRoles && currentUser(ctx) != null -> handler.handle(ctx)
                else -> ctx.status(401).header(Header.WWW_AUTHENTICATE, "Basic")
            }
        }

        // configure JavalinVue
        JavalinVue.apply {

            // set path to serve vue templates
            //val (path,location) = getPathAndLocation("/vue/layout.html")
            //rootDirectory(path, location)

            // state function to provide server-side state to web client
            stateFunction = { ctx ->
                val m = mapOf(
                    "currentUser" to currentUser(ctx),
                    "currentView" to currentView(ctx)
                )
                m
            }
        }
    }.start(7000)

    // browser security enhancements:
    // By February, 2020: Enforcement rollout for Chrome 80 Stable: The SameSite-by-default and SameSite=None-requires-Secure
    app.after { ctx ->
        ctx.cookie("cross-site-cookie","name")
        ctx.cookie("SameSite","Strict")
        ctx.cookie("HttpOnly")
        ctx.cookie("Secure")
    }

    app.get("/", VueComponent("<landing-page></landing-page>"), roles(AppRole.ANYONE))
    app.get("/cards", VueComponent("<user-cards></user-cards>"), roles(AppRole.LOGGED_IN))
    app.get("/users", VueComponent("<user-overview></user-overview>"), roles(AppRole.ANYONE))
    app.get("/users/:user-id", VueComponent("<user-profile></user-profile>"), roles(AppRole.LOGGED_IN))
    app.error(404, "html", VueComponent("<not-found></not-found>"))

    app.get("/api/users", UserController::getAll, roles(AppRole.ANYONE))
    app.get("/api/users/:user-id", UserController::getOne, roles(AppRole.LOGGED_IN))

    // register custom jsonmapper
    JavalinJson.fromJsonMapper = JavalinGson.fromMapper
    JavalinJson.toJsonMapper = JavalinGson.toMapper
}

fun getPathAndLocation(resourceReference: String): Pair<String, Location> {
    /*
        file:/.../target/classes/public/app.js
        jar:file:/.../javalinvue-example-1.0-SNAPSHOT-full.jar!/public/app.js
     */

    val resource = AppRole::class.java.getResource(resourceReference).toString()
    println(resource)
    val jarResource = resource.startsWith("jar:")

    val r = if(jarResource)
        Paths.get(resource.substringAfter("!")).parent.toString().removePrefix(File.pathSeparatorChar.toString()) to Location.CLASSPATH
    else
        Paths.get(resource.substringAfter("file:/"))
            .parent.toString()
            .replace("""target${File.pathSeparatorChar}classes""","src/main/resources") to Location.EXTERNAL
    println(r)
    return r
}

private fun currentUser(ctx: Context) =
    if (ctx.basicAuthCredentialsExist()) ctx.basicAuthCredentials().username else null

private fun currentView(ctx: Context) =
    "JavalinVue - "+when(ctx.path().removePrefix("/")) {
        "" -> "Dashboard"
        "users" -> "Users"
        "cards" -> "CardView"
        else -> "404"
    }
