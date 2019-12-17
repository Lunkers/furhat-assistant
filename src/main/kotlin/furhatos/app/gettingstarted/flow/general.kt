package furhatos.app.gettingstarted.flow

import furhatos.app.gettingstarted.nlu.RequestOptions
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.records.Location
import furhatos.util.*

val Idle: State = state {

    init {
        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        users.setSimpleEngagementPolicy(2.0, 3)
    }

    onEntry {
        furhat.attendNobody()
        if ( users.count > 0) {
            furhat.listen()
        }
    }

    onUserEnter {
        furhat.listen()
    }

    onResponse<RequestOptions>{

        furhat.attend(it.userId)
        goto(Start)
    }

    onResponse {
        furhat.listen()
    }

    onNoResponse {
        furhat.listen()
    }

}

val Interaction: State = state {

    onUserLeave(instant = true) {
        if (users.count > 0) {
            if (it == users.current) {
                furhat.attend(users.other)
                goto(Start)
            } else {
                furhat.glance(it)
            }
        } else {
            goto(Idle)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
    }

}