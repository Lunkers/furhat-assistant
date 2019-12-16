package furhatos.app.gettingstarted.flow

import furhatos.app.gettingstarted.nlu.RequestOptions
import furhatos.flow.kotlin.*
import furhatos.util.*

val Idle: State = state {

    init {
        furhat.setVoice(Language.ENGLISH_US, Gender.MALE)
        if (users.count > 0) {
            furhat.attend(users.random)
            goto(Start)
        }
    }

    onEntry {
        furhat.attendNobody()
        if ( users.count > 0) {
            print("There are users here!")
            furhat.listen()
        }
    }

    onUserEnter {
        furhat.attendAll()
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