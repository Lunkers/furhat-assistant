import Furhat from 'furhat-core';
const GoogleAssistant = require('./googleassistant')
const deviceCredentials = require('./devicecredentials.json')
const argv = require('yargs').argv

//setup variables from command line arguments
const robotIp = argv.ip;
const port = argv.port;
console.log(`Ip: ${robotIp}, port: ${port}`);

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
furhat.init(robotIp, port, 'api', (status, hat) => {
    if (status == "open") {
        furhat.say('I am connected')
        const askQuestion = (query) => {
            console.log(query)
            assistant.assist(query).then(({text}) => sendResponse(text))
        }

        const sendResponse = (response) => {
            const responseEvent = {
                event_name: "AssistantResponse",
                text: response
            }
            furhat.send(responseEvent)
        }

        furhat.subscribe("furhatos.app.gettingstarted.QueryEvent", event => askQuestion(event.query))
    }
})









