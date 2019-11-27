import Furhat from 'furhat-core';
const GoogleAssistant = require('./googleassistant')
const deviceCredentials = require('./devicecredentials.json')

//setup assistant credentials
const CREDENTIALS = {
    client_id: deviceCredentials.client_id,
    client_secret: deviceCredentials.client_secret,
    refresh_token: deviceCredentials.refresh_token,
    type: "authorized_user"
};
//create google assistant object
const assistant = new GoogleAssistant(CREDENTIALS);

let furhat = new Furhat()
furhat.init('localhost', '8080', 'api', (status, hat) => {
    if (status === 'open') {
        hat.subscribe("furhatos.event.senses.senseSpeech", event => console.log(event))
        //hat.say('I am connected!')

        //test furhat and assistant 
        assistant.assist("what time is it?").then(({text}) => hat.say(text))
    }

})






