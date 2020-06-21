package javalinvue

import com.google.gson.*
import io.javalin.plugin.json.FromJsonMapper
import io.javalin.plugin.json.ToJsonMapper
import java.lang.reflect.Type
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


private object GsonSerializerLocalDate : JsonSerializer<LocalDate?> {
    override fun serialize(date: LocalDate?, p1: Type?, p2: JsonSerializationContext?): JsonElement? {
        return if (date != null) {
            JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)) // "yyyy-mm-dd"}
        } else null
    }
}

private object GsonDeserializerLocalDate : JsonDeserializer<LocalDate?> {
    override fun deserialize(jsonElement: JsonElement?, type: Type?, context: JsonDeserializationContext?): LocalDate? {
        return if(jsonElement!=null) {
            val x = jsonElement.asJsonPrimitive.toString()
            LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(x)) //ofInstant(instant, ZoneId.systemDefault())
        } else null
    }
}

private object GsonSerializerLocalDateTime : JsonSerializer<LocalDateTime?> {
    override fun serialize(date: LocalDateTime?, p1: Type?, p2: JsonSerializationContext?): JsonElement? {
        return if (date != null) {
            JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)) // "yyyy-mm-dd"}
        } else null
    }
}

private object GsonDeserializerLocalDateTime : JsonDeserializer<LocalDateTime?> {
    override fun deserialize(jsonElement: JsonElement?, type: Type?, context: JsonDeserializationContext?): LocalDateTime? {
        return if(jsonElement!=null) {
            val x = jsonElement.asJsonPrimitive.toString()
            LocalDateTime.from(DateTimeFormatter.ISO_LOCAL_DATE_TIME.parse(x))
        } else null
    }
}

inline fun <reified T> fromJson(json: String): T = GSON.fromJson(json, T::class.java)


val GSON = GsonBuilder().apply {
    // converter for datetime
    registerTypeAdapter(LocalDate::class.java, GsonSerializerLocalDate)
    registerTypeAdapter(LocalDate::class.java, GsonDeserializerLocalDate)
    registerTypeAdapter(LocalDateTime::class.java, GsonSerializerLocalDateTime)
    registerTypeAdapter(LocalDateTime::class.java, GsonDeserializerLocalDateTime)

    // only map specific fields
    excludeFieldsWithoutExposeAnnotation()

    // Serialized names
    //@SerializedName("id")

    // set api version
    // use @Since @Until to version
    setVersion(1.0)

}.create()

fun toJson(obj: Any): String? = GSON.toJson(obj)

fun <K, V> Map<K, V>.mergeReduce(other: Map<K, V>, reduce: (V, V) -> V = { _, b -> b }): Map<K, V> =
    this.toMutableMap().apply { other.forEach { merge(it.key, it.value, reduce) } }

object JavalinGson {

    val fromMapper: FromJsonMapper = object : FromJsonMapper {
        override fun <T> map(json: String, targetClass: Class<T>): T = fromJson(json, targetClass)

        fun <T> fromJson(json: String, clazz: Class<T>): T {
            return GSON.fromJson(json, clazz)
        }
    }

    val toMapper: ToJsonMapper = object : ToJsonMapper {

        override fun map(obj: Any): String = toJson(obj)

        fun toJson(`object`: Any): String {
            return GSON.toJson(`object`)
        }
    }
}