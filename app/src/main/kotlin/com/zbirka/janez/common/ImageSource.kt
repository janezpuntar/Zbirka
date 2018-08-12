package com.zbirka.janez.common

import java.util.*

class ImageSource {

    companion object {
        val random: String
        get() = LIST[Random().nextInt(LIST.size)]

        private val LIST = listOf("https://upload.wikimedia.org/wikipedia/en/8/8d/Pablo_Picasso%2C_1905%2C_Au_Lapin_Agile_%28At_the_Lapin_Agile%29%2C_oil_on_canvas%2C_99.1_x_100.3_cm%2C_Metropolitan_Museum_of_Art.jpg"
                , "https://uploads8.wikiart.org/images/pablo-picasso/untitled-1937-8.jpg!PinterestSmall.jpg"
                , "https://imgix.ranker.com/user_node_img/520/10382885/original/girl-before-a-mirror-artwork-photo-u3?w=650&q=50&fm=jpg&fit=crop&crop=faces"
                , "http://www.themost10.com/wp-content/uploads/2012/03/Seated-Woman-Marie-Therese-By-Pablo-Picasso.jpg"
                , "https://i.dailymail.co.uk/i/pix/2012/01/27/article-2092698-0F64156C00000578-968_306x423.jpg"
                , "https://uploads3.wikiart.org/images/pablo-picasso/portrait-of-dora-maar-1937-1.jpg!PinterestSmall.jpg"
                , "https://www.tate.org.uk/art/images/work/T/T05/T05010_10.jpg"
        )
    }

}