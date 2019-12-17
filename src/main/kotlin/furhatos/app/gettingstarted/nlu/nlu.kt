package furhatos.app.gettingstarted.nlu

import furhatos.nlu.Intent
import furhatos.util.Language

class RequestOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Okay Bob", "Hi Bob", "Hello Bob", "Hey Bob", "Help us Bob", "Help me Bob",
                "We need help Bob", "Ok Bob", "O.K Bob")
    }
}

class LeavingOptions: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("Nevermind", "I forgot", "It was nothing", "You can't")
    }
}