package furhatos.app.gettingstarted.flow

import furhatos.app.gettingstarted.QueryEvent
import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.gettingstarted.nlu.*
import furhatos.flow.kotlin.voice.CereprocVoice
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.gestures.Gestures

val Start : State = state(Interaction) {

    onEntry {
        furhat.ask("How can I help?")
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
        furhat.listen()
    }


}

val Thinking : State = state(Interaction) {
    onEvent("AssistantResponse") {
        val response = it.get("text") //get response text from received event
        val text = if (response != null) response as String else "sorry, do not know"
        furhat.say(text)
        goto(Idle)
    }
}
