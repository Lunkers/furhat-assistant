package furhatos.app.gettingstarted

import furhatos.event.Event

//custom event to communicate with server
class QueryEvent(
        var query : String? = null  //user query, AKA question for the assistant
) : Event()

