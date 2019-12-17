package furhatos.app.gettingstarted.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class RequestOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Okay Steve", "Hi Steve", "Hello Steve", "Hey Steve", "Help us Steve", "Help me Steve",
                "We need help Steve", "Ok Steve", "O.K Steve")
    }
}

class LeavingOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Nevermind", "I forgot", "It was nothing", "You can't")
    }
}