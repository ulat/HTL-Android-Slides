class Weather_converter {
    internal enum class Color {
        BLUE, ORANGE, RED
    }

    fun updateWeather(degrees: Int) {
        val description: String
        val color: Color
        if (degrees < 10) {
            description = "cold"
            color = Color.BLUE
        } else if (degrees < 25) {
            description = "mild"
            color = Color.ORANGE
        } else {
            description = "hot"
            color = Color.RED
        }
    }
}