class Weather_kt_short {
    internal enum class Color {
        BLUE, ORANGE, RED
    }

    fun updateWeather(degrees: Int) {
        val (description, color) = when {
            degrees < 10 -> "cold" to Color.BLUE
                degrees < 25 -> "mild" to Color.ORANGE
            else -> "hot" to Color.RED
        }
    }
}