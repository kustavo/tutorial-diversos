function httpGetAsync(url, callBack) {
    fetch(url)
        .then(function (response) {
            // When the page is loaded convert it to text
            return response.text();
        })
        .then(function (data) {
            if (callBack) callBack(data);
        })
        .catch(function (err) {
            console.log("Erro ao obter arquivo: " + url, err);
        });
}

function getExtensionFile(url) {
    const pathArray = url.split("/");
    const fileName = pathArray.slice(-1)[0];

    if (!fileName.includes(".")) return undefined;

    return fileName.split(".").pop();
}

function getBaseUrl() {
    const baseUrl = window.location.origin;
    const pathArray = window.location.pathname.split("/");

    return baseUrl + "/" + pathArray[1];
}

function addScriptHead(src, defer, callBack) {
    const script = document.createElement("script");
    script.type = "text/javascript";
    script.src = src;
    script.defer = defer;

    const head = document.querySelector("head");
    head.insertAdjacentElement("afterbegin", script);

    script.onload = () => {
        if (callBack) callBack();
    };
}

function addStyleHead(src, callBack) {
    const link = document.createElement("link");
    link.type = "text/css";
    link.rel = "stylesheet";
    link.href = src;

    const head = document.querySelector("head");
    head.insertAdjacentElement("afterbegin", link);

    link.onload = () => {
        if (callBack) callBack();
    };
}

function createCodeElement(element, url, text) {
    let className;

    switch (getExtensionFile(url)) {
        case "html":
            className = "language-html";
            break;
        case "css":
            className = "language-css";
            break;
        case "js":
            className = "language-js";
            break;
        default:
            className = "language-html";
    }

    const textNode = document.createTextNode(text);

    const code = document.createElement("code");
    code.classList.add(className);
    code.appendChild(textNode);

    element.appendChild(code);
    hljs.highlightBlock(code);
}

function main() {
    const elements = document.querySelectorAll("[data-url]");

    if (!elements || elements.length === 0) return;

    // https://github.com/highlightjs/highlight.js

    addStyleHead(getBaseUrl() + "/_extras/github.css", function () {
        addScriptHead(getBaseUrl() + "/_extras/highlight.pack.js", false, function () {
            elements.forEach((el) => {
                const dataUrl = el.getAttribute("data-url");
                const url = getBaseUrl() + "/" + dataUrl;

                httpGetAsync(url, function (text) {
                    createCodeElement(el, url, text);
                });
            });
        });
    });
}

main();
