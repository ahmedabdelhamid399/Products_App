package com.example.productsonline

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val rating: Double,
    val brand: String,
    val thumbnail: String,

)


object ProductUtil {
    fun getProducts(): List<Product> {
        return listOf(
            Product(
                id = 1,
                title = "iPhone 9",
                description = "An apple mobile which is nothing like apple",
                price = 549.0,
                rating = 4.69,
                brand = "Apple",
                thumbnail = "https://i.dummyjson.com/data/products/1/thumbnail.jpg"
            ),
            Product(
                id = 2,
                title = "iPhone X",
                description = "SIM-Free, Model A19211 6.5-inch Super Retina HD display with OLED technology A12 Bionic chip with ...",
                price = 899.0,
                rating = 4.44,
                brand = "Apple",
                thumbnail = "https://i.dummyjson.com/data/products/2/thumbnail.jpg"
            ),
            Product(
                id = 3,
                title = "Samsung Universe 9",
                description = "Samsung's new variant which goes beyond Galaxy to the Universe",
                price = 1249.0,
                rating = 4.09,
                brand = "Samsung",
                thumbnail = "https://i.dummyjson.com/data/products/3/thumbnail.jpg"
            ),
            Product(
                id = 4,
                title = "OPPOF19",
                description = "OPPO F19 is officially announced on April 2021.",
                price = 280.0,
                rating = 4.3,
                brand = "OPPO",
                thumbnail = "https://i.dummyjson.com/data/products/4/thumbnail.jpg"
            ),
            Product(
                id = 5,
                title = "Huawei P30",
                description = "Huawei’s re-badged P30 Pro New Edition was officially unveiled yesterday in Germany and now the device has made its way to the UK.",
                price = 499.0,
                rating = 4.09,
                brand = "Huawei",
                thumbnail = "https://i.dummyjson.com/data/products/5/thumbnail.jpg"
            ),
            Product(
                id = 6,
                title = "MacBook Pro",
                description = "MacBook Pro 2021 with mini-LED display may launch between September, November",
                price = 1749.0,
                rating = 4.57,
                brand = "Apple",
                thumbnail = "https://i.dummyjson.com/data/products/6/thumbnail.png"
            ),
            Product(
                id = 7,
                title = "Samsung Galaxy Book",
                description = "Samsung Galaxy Book S (2020) Laptop With Intel Lakefield Chip, 8GB of RAM Launched",
                price = 1499.0,
                rating = 4.25,
                brand = "Samsung",
                thumbnail = "https://i.dummyjson.com/data/products/7/thumbnail.jpg"
            )
        )
    }

}

