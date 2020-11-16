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

        for (i in movieId.indices) {
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
            "/6tfT03sGp9k4c0J3dypjrI8TSAI.jpg",
            "/sWgBv7LV2PRoQgkxwlibdGXKz1S.jpg",
            "/wGFUewXPeMErCe2xnCmmLEiHOGh.jpg",
            "/4EYPN5mVIhKLfxGruy7Dy41dTVn.jpg",
            "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            "/zU0htwkhNvBQdVSIKB9s6hgVeFK.jpg",
            "/z31GxpVgDsFAF4paZR8PRsiP16D.jpg",
            "/g6tIKGc3f1H5QMz1dcgCwADKpZ7.jpg",
            "/clnyhPqj1SNgpAdeSS6a6fwE6Bo.jpg",
            "/2IWouZK4gkgHhJa3oyYuSWfSqbG.jpg"
        )

        val backgroundPath = arrayOf(
            "/iDbIEpCM9nhoayUDTwqFL1iVwzb.jpg",
            "/9ijMGlJKqcslswWUzTEwScm82Gs.jpg",
            "/58PON1OrnBiX6CqEHgeWKVwrCn6.jpg",
            "/ta5oblpMlEcIPIS2YGcq9XEkWK2.jpg",
            "/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg",
            "/34OGjFEbHj0E3lE2w0iTUVq0CBz.jpg",
            "/pLVrN9B750ehwTFdQ6n3HRUERLd.jpg",
            "/9yKCJTOh9m3Lol2RY3kw99QPH6x.jpg",
            "/3IIBf6VlwEyKAX4cN2XCM1gKdgM.jpg",
            "/hpU2cHC9tk90hswCFEpf5AtbqoL.jpg"

        )

        val rating = doubleArrayOf(6.0, 1.0, 5.0, 7.0, 8.3, 5.9, 7.3, 6.3, 7.7, 6.5, 6.4)


        val tvShowList = ArrayList<MovieAndTvShowEntity>()

        for (i in tvShowId.indices) {
            val tvShow = MovieAndTvShowEntity()
            tvShow.id = tvShowId[i]
            tvShow.title = tvShowTitle[i]
            tvShow.synopsis = tvShowSynopsis[i]
            tvShow.poster_url = tvShowPosterUrl[i]
            tvShow.backdrop_url = backgroundPath[i]
            tvShow.rating = rating[i]
            tvShowList.add(tvShow)
        }

        return tvShowList
    }
}