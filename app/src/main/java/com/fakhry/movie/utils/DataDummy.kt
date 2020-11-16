package com.fakhry.movie.utils

import com.fakhry.movie.model.MovieAndTvShowEntity
import java.util.ArrayList

object DataDummy {

    fun generateDummyMovie(): ArrayList<MovieAndTvShowEntity> {
        val movieId = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val movieTitle = arrayOf(
            "Demon Slayer: Kimetsu no Yaiba - The Movie: Mugen Train",
            "Hard Kill",
            "Roald Dahl's The Witches",
            "The SpongeBob Movie: Sponge on the Run",
            "2067",
            "Once Upon a Snowman",
            "Welcome to Sudden Death",
            "Over the Moon",
            "Enola Holmes",
            "Rogue City"
        )

        val movieSynopsis = arrayOf(
            "Tanjirō Kamado, joined with Inosuke Hashibira, a boy raised by boars who wears a boar's head, and Zenitsu Agatsuma, a scared boy who reveals his true power when he sleeps, boards the Infinity Train on a new mission with the Fire Hashira, Kyōjurō Rengoku, to defeat a demon who has been tormenting the people and killing the demon slayers who oppose it!",
            "The work of billionaire tech CEO Donovan Chalmers is so valuable that he hires mercenaries to protect it, and a terrorist group kidnaps his daughter just to get it.",
            "In late 1967, a young orphaned boy goes to live with his loving grandma in the rural Alabama town of Demopolis. As the boy and his grandmother encounter some deceptively glamorous but thoroughly diabolical witches, she wisely whisks him away to a seaside resort. Regrettably, they arrive at precisely the same time that the world's Grand High Witch has gathered.",
            "When his best friend Gary is suddenly snatched away, SpongeBob takes Patrick on a madcap mission far beyond Bikini Bottom to save their pink-shelled pal.",
            "A lowly utility worker is called to the future by a mysterious radio signal, he must leave his dying wife to embark on a journey that will force him to face his deepest fears in an attempt to change the fabric of reality and save humankind from its greatest environmental crisis yet.",
            "The previously untold origins of Olaf, the innocent and insightful, summer-loving snowman are revealed as we follow Olaf’s first steps as he comes to life and searches for his identity in the snowy mountains outside Arendelle.",
            "Jesse Freeman is a former special forces officer and explosives expert now working a regular job as a security guard in a state-of-the-art basketball arena. Trouble erupts when a tech-savvy cadre of terrorists kidnap the team's owner and Jesse's daughter during opening night. Facing a ticking clock and impossible odds, it's up to Jesse to not only save them but also a full house of fans in this highly charged action thriller.",
            "A girl builds a rocket to travel to the moon in hopes of meeting the legendary Moon Goddess.",
            "While searching for her missing mother, intrepid teen Enola Holmes uses her sleuthing skills to outsmart big brother Sherlock and help a runaway lord.",
            "Caught in the crosshairs of police corruption and Marseille’s warring gangs, a loyal cop must protect his squad by taking matters into his own hands."
        )

        val moviewPosterUrl = arrayOf(
            "/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
            "/betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
            "/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
            "/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
            "/hddzYJtfYYeMDOQVcH58n8m1W3A.jpg",
            "/elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg",
            "/lQfdytwN7eh0tXWjIiMceFdBBvD.jpg",
            "/riYInlsq2kf1AWoGm80JQW5dLKp.jpg",
            "/9HT9982bzgN5on1sLRmc1GMn6ZC.jpg",
            "/6agKYU5IQFpuDyUYPu39w7UCRrJ.jpg",
        )

        val backgroundPath = arrayOf(
            "/xoqr4dMbRJnzuhsWDF3XNHQwJ9x.jpg",
            "/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg",
            "/4lWr2j3ZSEe8qlt3W3ma8TiiMQB.jpg",
            "/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
            "/5UkzNSOK561c2QRy2Zr4AkADzLT.jpg",
            "/DA7gzvlBoxMNL0XmGgTZOyv67P.jpg",
            "/aO5ILS7qnqtFIprbJ40zla0jhpu.jpg",
            "/htBUhLSS7FfHtydgYxUWjL3J1Q1.jpg",
            "/kMe4TKMDNXTKptQPAdOF0oZHq3V.jpg",
            "/gnf4Cb2rms69QbCnGFJyqwBWsxv.jpg",
            "/hbrXbVoE0NuA1ORoSGGYNASagrl.jpg",
        )

        val rating = doubleArrayOf(6.0, 1.0, 5.0, 7.0, 8.3, 5.9, 7.3, 6.3, 7.7, 6.5, 6.4)

        val movieList = ArrayList<MovieAndTvShowEntity>()
        for (i in movieId.indices){
            val movie = MovieAndTvShowEntity()
            movie.id = movieId[i]
            movie.title = movieTitle[i]
            movie.synopsis = movieSynopsis[i]
            movie.poster_url = moviewPosterUrl[i]
            movie.backdrop_url = backgroundPath[i]
            movie.rating = rating[i]
            movieList.add(movie)
        }

        return movieList
    }
}