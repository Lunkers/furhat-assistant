package furhatos.app.gettingstarted.flow

import furhatos.app.gettingstarted.QueryEvent
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.gettingstarted.nlu.*
import furhatos.flow.kotlin.voice.CereprocVoice
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.gestures.Gestures
val TIMEOUT = 10000
val Start : State = state(Interaction) {

    onEntry {
        furhat.ask("How can I help?", timeout= TIMEOUT)
    }

    onResponse<LeavingOptions>{
        furhat.say("I like humans.") //never mind -> tillbaka till "general"
    }

    onResponse{
        furhat.say() {
            Gestures.GazeAway
            + "Let me think"
            + Gestures.Thoughtful
        }
        send(QueryEvent(it.text)) // send query to assistant server
        goto(Thinking)

    }

    onNoResponse {
        furhat.listen(timeout = TIMEOUT)
    }


}

val Thinking : State = state(Interaction) {
    onEvent("AssistantResponse") {
        val response = it.get("text") //get response text from received event
        if (response != null) {
            furhat.say(response as String)
            goto(Idle)
        } else {
            furhat.say("Sorry, i did not find an answer. Try asking another question.")
            goto(Start)
        }
        //val text = if (response != null) response as String else "sorry, do not know"
        //furhat.say(text)
        //goto(Idle)
    }
}
