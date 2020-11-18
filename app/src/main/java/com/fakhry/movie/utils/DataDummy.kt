package com.fakhry.movie.utils

import com.fakhry.movie.data.source.local.entity.MovieAndTvShowEntity
import java.util.*

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
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/ugZW8ocsrfgI95pnQ7wrmKDxIe.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/betExZlgK0l7CZ9CsCBVcwO1OjL.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/jlJ8nDhMhCYJuzOw3f52CP1W8MW.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/hddzYJtfYYeMDOQVcH58n8m1W3A.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/elZ6JCzSEvFOq4gNjNeZsnRFsvj.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/lQfdytwN7eh0tXWjIiMceFdBBvD.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/riYInlsq2kf1AWoGm80JQW5dLKp.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/9HT9982bzgN5on1sLRmc1GMn6ZC.jpg"

        )

        val backgroundPath = arrayOf(
            "https://image.tmdb.org/t/p/w500_and_h282_face/xoqr4dMbRJnzuhsWDF3XNHQwJ9x.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/86L8wqGMDbwURPni2t7FQ0nDjsH.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/4lWr2j3ZSEe8qlt3W3ma8TiiMQB.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/wu1uilmhM4TdluKi2ytfz8gidHf.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/5UkzNSOK561c2QRy2Zr4AkADzLT.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/DA7gzvlBoxMNL0XmGgTZOyv67P.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/aO5ILS7qnqtFIprbJ40zla0jhpu.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/htBUhLSS7FfHtydgYxUWjL3J1Q1.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/kMe4TKMDNXTKptQPAdOF0oZHq3V.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/gnf4Cb2rms69QbCnGFJyqwBWsxv.jpg"
        )

        val rating = doubleArrayOf(6.0, 1.0, 5.0, 7.0, 8.3, 5.9, 7.3, 6.3, 7.7, 6.5, 6.4)

        val movieList = ArrayList<MovieAndTvShowEntity>()

        for (i in movieId.indices) {
            val movie = MovieAndTvShowEntity(
                movieId[i],
                movieTitle[i],
                movieSynopsis[i],
                moviewPosterUrl[i],
                backgroundPath[i],
                rating[i])
            movieList.add(movie)
        }

        return movieList
    }

    fun generateDummyTvShow(): ArrayList<MovieAndTvShowEntity> {
        val tvShowId = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

        val tvShowTitle = arrayOf(
            "The Good Doctor",
            "The Mandalorian",
            "Fear the Walking Dead",
            "Lucifer",
            "The Boys",
            "The Queen's Gambit",
            "The Walking Dead: World Beyond",
            "His Dark Materials",
            "Grey's Anatomy",
            "The Simpsons",
        )

        val tvShowSynopsis = arrayOf(
            "A young surgeon with Savant syndrome is recruited into the surgical unit of a prestigious hospital. The question will arise: can a person who doesn't have the ability to relate to people actually save their lives?",
            "After the fall of the Galactic Empire, lawlessness has spread throughout the galaxy. A lone gunfighter makes his way through the outer reaches, earning his keep as a bounty hunter.",
            "What did the world look like as it was transforming into the horrifying apocalypse depicted in \"The Walking Dead\"? This spin-off set in Los Angeles, following new characters as they face the beginning of the end of the world, will answer that question.",
            "Bored and unhappy as the Lord of Hell, Lucifer Morningstar abandoned his throne and retired to Los Angeles, where he has teamed up with LAPD detective Chloe Decker to take down criminals. But the longer he's away from the underworld, the greater the threat that the worst of humanity could escape.",
            "A group of vigilantes known informally as “The Boys” set out to take down corrupt superheroes with no more than blue-collar grit and a willingness to fight dirty.",
            "In a Kentucky orphanage in the 1950s, a young girl discovers an astonishing talent for chess while struggling with addiction.",
            "A heroic group of teens sheltered from the dangers of the post-apocalyptic world receive a message that inspires them to leave the safety of the only home they have ever known and embark on a cross-country journey to find the one man who can possibly save the world.",
            "Lyra is an orphan who lives in a parallel universe in which science, theology and magic are entwined. Lyra's search for a kidnapped friend uncovers a sinister plot involving stolen children, and turns into a quest to understand a mysterious phenomenon called Dust. She is later joined on her journey by Will, a boy who possesses a knife that can cut windows between worlds. As Lyra learns the truth about her parents and her prophesied destiny, the two young people are caught up in a war against celestial powers that ranges across many worlds.",
            "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
            "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general."
        )

        val tvShowPosterUrl = arrayOf(
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/z31GxpVgDsFAF4paZR8PRsiP16D.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/g6tIKGc3f1H5QMz1dcgCwADKpZ7.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            "https://image.tmdb.org/t/p/w600_and_h900_bestv2/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg"
        )

        val backgroundPath = arrayOf(
            "https://image.tmdb.org/t/p/w500_and_h282_face/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/34OGjFEbHj0E3lE2w0iTUVq0CBz.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/pLVrN9B750ehwTFdQ6n3HRUERLd.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/9yKCJTOh9m3Lol2RY3kw99QPH6x.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/3IIBf6VlwEyKAX4cN2XCM1gKdgM.jpg",
            "https://image.tmdb.org/t/p/w500_and_h282_face/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg"

        )

        val rating = doubleArrayOf(6.0, 1.0, 5.0, 7.0, 8.3, 5.9, 7.3, 6.3, 7.7, 6.5, 6.4)


        val tvShowList = ArrayList<MovieAndTvShowEntity>()

        for (i in tvShowId.indices) {
            val tvShow = MovieAndTvShowEntity(
                tvShowId[i],
                tvShowTitle[i],
                tvShowSynopsis[i],
                tvShowPosterUrl[i],
                backgroundPath[i],
                rating[i])
            tvShowList.add(tvShow)
        }

        return tvShowList
    }
}