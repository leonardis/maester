package example.maester.utils

import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPreferencesHelper constructor(var sharedPreferences: SharedPreferences, var gson: Gson) {

    private var editor: SharedPreferences.Editor = sharedPreferences.edit()

    @Synchronized
    fun setSuggestions(suggestions: List<*>?) {
        setDataToSharedPref(gson.toJsonTree(suggestions, List::class.java)
                .toString(), String::class.java, "suggestions")
    }

    @Synchronized
    fun getSuggestions(): List<*>? {
        return gson.fromJson((getDataFromSharedPref(String::class.java, "suggestions"))
                as String?, List::class.java)
    }

    @Synchronized
    fun <T> getDataFromSharedPref(dataType: Class<T>, key: String): Any? =
            when (dataType) {
                Int::class.java -> sharedPreferences.getInt(key, 0)
                String::class.java -> sharedPreferences.getString(key, "")
                Long::class.java -> sharedPreferences.getLong(key, 0)
                Float::class.java -> sharedPreferences.getFloat(key, 0f)
                Boolean::class.java -> sharedPreferences.getBoolean(key, false)
                else -> null
            }

    @Synchronized
    fun <T> setDataToSharedPref(data: T, dataType: Class<T>, key: String) {
        when (dataType) {
            Int::class.java -> editor.putInt(key, data as Int)
            String::class.java -> editor.putString(key, data as String)
            Long::class.java -> editor.putLong(key, data as Long)
            Float::class.java -> editor.putFloat(key, data as Float)
            Boolean::class.java -> editor.putBoolean(key, data as Boolean)
        }
        editor.commit()
    }
}