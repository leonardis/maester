package example.maester.utils.extensions

val Int.minToHours: String get() {
    val hours = this / 60
    val minutes = this % 60
    return "${hours}h ${minutes}min"
}