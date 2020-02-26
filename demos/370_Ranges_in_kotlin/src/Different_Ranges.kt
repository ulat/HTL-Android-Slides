//different types of ranges

// int ranges
val intRange: IntRange = 1..9
val anotherIntRange: IntRange = 1 until 10

// char range
val charRange: CharRange = 'a'..'z'
// string range
val stringRange: ClosedRange<String> = "ab".."az"

// range of custom date class (would have to declare the class MyDate!
//val dateRange: ClosedRange<MyDate> = startDate..endDate
