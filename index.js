import Furhat from 'furhat-core';

let furhat = new Furhat()

furhat.init('localhost', '8080', 'api', (status, hat) => {
    if (status === 'open') {
        hat.subscribe("furhatos.event.senses.senseSpeech", event => console.log(event))
        hat.say('I am connected!')
    }
   
})