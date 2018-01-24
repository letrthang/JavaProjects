/**
 * 
 */
var wsUri = "ws://echo.websocket.org/";
var output;
var startTimer1Var;
var startTimer2Var;
var address_1;
var address_2;
var msg1Send;
var msg2Send;
var msg1Res;
var msg2Res;
var message1ID = 0;
var message2ID = 0;
var websocket1;
var websocket2;
var counter1 = 0;
var counter2 = 0;
var logsize = 0;

function openConnection() {
	initSocket1();
	initSocket2();
}

function closeConnection() {
	if (websocket1 != null) {
		websocket1.close();
	}
	if (websocket2 != null) {
		websocket2.close();
	}

	websocket1 = null;
	websocket2 = null;

	writeToScreen("Closed connections");
}

function startTimer() {
	writeToScreen("Start all timers");
	startTimer1Var = window.setInterval(doSend1, 4000);
	startTimer2Var = window.setInterval(doSend2, 5000);
}

function stopTimer() {
	writeToScreen("Stop all timers ...");
	window.clearInterval(startTimer1Var);
	window.clearInterval(startTimer2Var);
}

function clearLogMsg() {
	clearScreen();
}

function initSocket1() {
	address_1 = document.getElementById("url1").value;
	msg1Send = document.getElementById("msg1").value;
	msg1Res = document.getElementById("msg1res").value;

	if (address_1 != "") {
		writeToScreen("open websocket 1 ...");
		connectWebSocket1(address_1);
	}

}

function initSocket2() {
	address_2 = document.getElementById("url2").value;
	msg2Send = document.getElementById("msg2").value;
	msg2Res = document.getElementById("msg2res").value;

	if (address_2 != "") {
		writeToScreen("open websocket 2...");
		connectWebSocket2(address_2);
	}

}

function connectWebSocket1(url) {
	websocket1 = new WebSocket(url);
	websocket1.onopen = function(evt) {
		onOpen1(evt)
	};
	websocket1.onclose = function(evt) {
		onClose1(evt)
	};
	websocket1.onmessage = function(evt) {
		onMessage1(evt)
	};
	websocket1.onerror = function(evt) {
		onError1(evt)
	};
}

function connectWebSocket2(url) {
	websocket2 = new WebSocket(url);
	websocket2.onopen = function(evt) {
		onOpen2(evt)
	};
	websocket2.onclose = function(evt) {
		onClose2(evt)
	};
	websocket2.onmessage = function(evt) {
		onMessage2(evt)
	};
	websocket2.onerror = function(evt) {
		onError2(evt)
	};
}

function onOpen1(evt) {
	writeToScreen("CONNECTED 1");
//doSend1("WebSocket rocks 1");
}

function onOpen2(evt) {
	writeToScreen("CONNECTED 2");
//doSend2("WebSocket rocks 2");
}

function onClose1(evt) {
	websocket1.close();
	websocket1 = null;
	writeToScreen("DISCONNECTED from sockets 1");
}

function onClose2(evt) {
	websocket2.close();
	websocket2 = null;
	writeToScreen("DISCONNECTED from sockets 2");
}

function onMessage1(evt) {
	var inboundMsg = JSON.parse(evt.data);
	var controlMsgType;

	if (inboundMsg.protocolID == "UHES2@1.0@RES") {
		writeToScreen('<span style="color: blue;">RESPONSE from sockets 1: ' + evt.data
			+ '</span>');
	} else {
		// request Transport message 0x314 from NNC
		if (inboundMsg.functionID == "0x314") {
			controlMsgType = parseUHESControlMsgType(inboundMsg);
			writeToScreen(controlMsgType);
		}
	}
}

function parseUHESControlMsgType(UHESmsg) {
	var base64String = UHESmsg.functionArgument[0].argumentValue;
	base64String = base64String.substring(7, base64String.length - 9);
	var argValueXML = window.atob(base64String);

	var parser = new DOMParser();
	xmlDoc = parser.parseFromString(argValueXML, "text/xml");
	var msgType = xmlDoc.getElementsByTagName("UHES_Message")[0].getAttribute("MessageType");
	return msgType;
}

function onMessage2(evt) {
	writeToScreen('<span style="color: green;">RESPONSE from sockets 2: ' + evt.data
		+ '</span>');
}

function onError1(evt) {
	writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function onError2(evt) {
	writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
}

function doSend1() {
	if (websocket1 == null) {
		return;
	}

	msg1Send = document.getElementById("msg1").value;
	msg2Send = document.getElementById("msg2").value;
	counter1++;

	if (counter1 == 1 && msg1Send != "") {
		var outboundMsg1 = JSON.parse(msg1Send);
		outboundMsg1.messageID = ++message1ID;
		msg1Send = JSON.stringify(outboundMsg1);
		writeToScreen("SENT MESSAGE1 to websockets 1");
		websocket1.send(msg1Send);
	} else if (counter1 > 1 && msg2Send != "") {
		var outboundMsg2 = JSON.parse(msg2Send);
		outboundMsg2.messageID = ++message2ID;
		msg2Send = JSON.stringify(outboundMsg2);
		writeToScreen("SENT MESSAGE2 to websockets 1");
		websocket1.send(msg2Send);
		counter1 = 0;
	}
}

function doSend2() {
	if (websocket2 == null) {
		return;
	}

	msg1Send = document.getElementById("msg1").value;
	msg2Send = document.getElementById("msg2").value;
	counter2++;

	if (counter2 == 1 && msg1Send != "") {
		var outboundMsg1 = JSON.parse(msg1Send);
		outboundMsg1.messageID++;
		msg1Send = JSON.stringify(outboundMsg1);
		writeToScreen("SENT MESSAGE1 to websockets 2");
		websocket2.send(msg1Send);
	} else if (counter2 > 1 && msg2Send != "") {
		var outboundMsg2 = JSON.parse(msg2Send);
		outboundMsg2.messageID++;
		msg2Send = JSON.stringify(outboundMsg2);
		writeToScreen("SENT MESSAGE2 to websockets 2");
		websocket2.send(msg2Send);
		counter2 = 0;
	}
}

function writeToScreen(message) {
	var pre = document.createElement("p");
	pre.style.wordWrap = "break-word";
	pre.innerHTML = message;
	output.appendChild(pre);
	logsize++;

	if (logsize > 100) {
		logsize = 0;
		clearScreen();
	}
}

function clearScreen() {
	output.innerHTML = "";
}

function initButtonListeners() {
	document.getElementById("butOpenConnection").onclick = function() {
		openConnection();
	};
	document.getElementById("butCloseConnection").onclick = function() {
		closeConnection();
	};
	document.getElementById("butStartTimer").onclick = function() {
		startTimer();
	};
	document.getElementById("butStopTimer").onclick = function() {
		stopTimer();
	};
	document.getElementById("butClearLog").onclick = function() {
		clearLogMsg();
	};

	output = document.getElementById("outputMsg");

}