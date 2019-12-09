package furhatos.app.gettingstarted.flow

import furhatos.nlu.common.*
import furhatos.flow.kotlin.*
import furhatos.app.gettingstarted.nlu.*

val Start : State = state(Interaction) {

    onEntry {
        furhat.ask("How can I help?")
    }

    onResponse<LeavingOptions>{
        furhat.say("I like humans.") //never mind -> tillbaka till "general"
    }

    onResponse{
        furhat.say("Let me think.") //skicka till assistant
    }

    onNoResponse {
        furhat.listen()
    }
}
