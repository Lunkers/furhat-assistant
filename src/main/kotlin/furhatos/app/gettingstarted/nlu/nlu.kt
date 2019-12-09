package furhatos.app.gettingstarted.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class RequestOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Okay Simon", "Hi Simon", "Hello Simon", "Hey Simon", "Help us Simon", "Help me Simon",
                "We need help Simon")
    }
}

class LeavingOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Nevermind", "I forgot", "It was nothing", "You can't")
    }
}