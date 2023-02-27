package com.beatriz.motivation.infra

class MotivationConstants private constructor(){

    object KEY {
        const val USER_NAME = "USER_NAME"
    }

    object LANGUAGE {
        const val ENGLISH = "en"
        const val PORTUGUESE = "pt"
        const val FRENCH = "fr"
    }

    object PHRASEFILTER {
        const val ALL = 1
        const val HAPPY = 2
        const val SUNNY = 3
    }
}