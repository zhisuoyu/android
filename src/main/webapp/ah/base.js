const MAX_SIZE = 100;
var count = 0;
const CONTENT_ID = "content";
var contentElement;

function getContentElement() {
    if (contentElement == null) {
        contentElement = $("#" + CONTENT_ID);
    }
    return contentElement
}


function appendContent(content) {
    getContentElement().append(content);
    count++;
    if (count > MAX_SIZE) {
        getContentElement().children().first().remove();
    }
}

// function getHtml() {
//     return document.getElementsByTagName('html')[0].outerHTML
// }

// function test() {
//     var html = '<div  class="common-items">' +
//         '<div class="common-usercontent">' +
//         '項目' + count +
//         '</div>' +
//         '<div class="common-previewcontent">' +
//         ' 內容' + count +
//         ' </div> ' +
//         ' </div>';
//     // var c = $("#content")
//     // c.append(html);
//     appendContent(html)
//     var e = document.getElementById("");
//     console.log(document.getElementsByTagName('html')[0].outerHTML)
// }


// function appendTest() {
//     var html = "<div style='margin: 10px;width:100%;height: 500px;background-color: aqua'>" +
//         "000000<br> 111111<br> 222222<br> 333333<br> 444444<br> 555555<br> 666666<br> 777777<br> 888888<br> 999999<br> 101010101010<br> 111111111111<br> 121212121212<br> 131313131313<br> 141414141414<br> 151515151515<br> 161616161616<br> " +
//         "</div>"
//     appendContent(html)
// }


function connectWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) {
        callback(WebViewJavascriptBridge)
    } else {
        // alert("addEventListener")
        document.addEventListener(
            'WebViewJavascriptBridgeReady'
            , function () {
                callback(WebViewJavascriptBridge)
            }
        );
    }
}

connectWebViewJavascriptBridge(function (bridge) {
    bridge.init(function (message, responseCallback) {
        // document.getElementById("show").innerHTML = message;
        // if (responseCallback) {
        //     responseCallback("default response data from js");
        // }
    });
    // bridge.registerHandler("SpecJsFun", function (data, responseCallback) {
    //     document.getElementById("show").innerHTML = data;
    //     if (responseCallback) {
    //         var responseData = "spec response data from js";
    //         responseCallback(responseData);
    //     }
    // });
})

function test() {
    load()
}

$(function () {
    // appendTest()
    // appendTest()
    // appendTest()
    $(window).scroll(function () {
        var body = document.body;
        //FF支持document.documentElement.scrollTop，chrome支持document.body.scrollTop
        if (body.scrollHeight - body.clientHeight <= (body.scrollTop | document.documentElement.scrollTop) + 200) {
            load()
        }
    });
});

function load() {
    console.log("load")
    WebViewJavascriptBridge.callHandler(
        'loadNewPage'
        , {'jsKey': "jsValue"}
        , function (responseData) {
            appendContent(responseData)
            // document.getElementById("show").innerHTML = "send get responseData from java, data = " + responseData
        }
    );
}




