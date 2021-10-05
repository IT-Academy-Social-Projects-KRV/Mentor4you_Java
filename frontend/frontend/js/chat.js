const url = 'http://localhost:8080';
let stompClient;
let selectedUser;
let newMessages = new Map();

function connectToChat(userName="Alex") {
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, function (response) {

            let data = JSON.parse(response.body);
            if (selectedUser === data.fromLogin) {
                render(data.message, data.fromLogin);

            } else {
                newMessages.set(data.fromLogin, data.message);

                $('#userNameAppender_' + data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="color: red">+1</span>');
            }

        });
            $.get(url + "/chat", function (res) {
                                       console.log(res)
                               });
    });
}


function sendMsg(from, text) {
//    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
    stompClient.send("/app/chat/" + "2", {}, JSON.stringify({
        chatId:"1",
        senderId:from,
        recipientId:"2",
        senderName:from,
        recipientName:"2",
        message:text
    }));
}

function registration() {
 let userName = document.getElementById("userName").value;
        connectToChat(userName);
}



function selectUser(userName) {
    console.log("selecting users: " + userName);
    selectedUser = userName;
    let isNew = document.getElementById("newMessage_" + userName) !== null;
    if (isNew) {
        let element = document.getElementById("newMessage_" + userName);
        element.parentNode.removeChild(element);
        render(newMessages.get(userName), userName);
    }
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Chat with ' + userName);
}

function fetchAll() {
    $.get(url + "/chat", function (response) {
        console.log(response)
    });
}
