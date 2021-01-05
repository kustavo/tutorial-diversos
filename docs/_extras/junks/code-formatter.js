/*
Captura o código do arquivo html, forta e coloca em uma tag <code>

Como Usar:

- Criar um iframe no markdown com o parametro "?code"
Exemplo: <iframe src="../_css/grid/exemplo-introducao/introducao.html?code" style="height: 500px;"></iframe>

- O arquivo htlm deve chamar o script "code-formatter.js"
Exemplo:  <script type="module" src="../../../../_extras/code-formatter.js" defer> </script>
 
*/

function getParamValue(paramName) {
    const url = window.location.search.substring(1); //get rid of "?" in querystring
    const qArray = url.split('&');
    for (let i = 0; i < qArray.length; i++) 
    {
        let pArr = qArray[i].split('=');
        if (pArr[0] == paramName) {

            if (pArr[1] === undefined)
                return null;

            return pArr[1];
        }    
    }
}

function addScriptHead(src, callBack) {
    const script = document.createElement('script');
    script.type = 'text/javascript';
    script.src = src;
    script.defer = true;    

    const head = document.querySelector("head");
    head.insertAdjacentElement("afterbegin", script);
    
    script.onload = () => {
      if (callBack) callBack();
    };
}

function getHtmlBeautify(htmlString) {
    const htmlStringBeautify = html_beautify(htmlString, {
        indent_size: "4",
        indent_char: " ",
        max_preserve_newlines: "-1",
        preserve_newlines: false,
        keep_array_indentation: false,
        break_chained_methods: false,
        indent_scripts: "keep",
        brace_style: "collapse",
        space_before_conditional: true,
        unescape_strings: false,
        jslint_happy: false,
        end_with_newline: false,
        wrap_line_length: "0",
        indent_inner_html: false,
        comma_first: false,
        e4x: false,
        indent_empty_lines: false,
    });

    return htmlStringBeautify;
}

function createPreElement(textNode) {
    const body = document.querySelector("body");
    const code = document.createElement("code");
    const pre = document.createElement("pre");

    code.classList.add("language-html");
    pre.classList.add("prettyprint");

    body.innerHTML = '';
    code.appendChild(textNode);
    pre.appendChild(code);
    body.appendChild(pre);
}

function getExtensionFile() {
    const pathArray = window.location.pathname.split('/');
    const fileName = pathArray.slice(-1)[0];

    if (!fileName.includes('.'))
        return undefined;

    return fileName.split('.').pop();
}

function getBaseUrl() {
    const baseUrl = window.location.origin;
    const pathArray = window.location.pathname.split('/');

    return baseUrl + '/' + pathArray[1];
}

function escapeRegExp(string) {
    return string.replace(/[.*+?^${}()|[\]\\]/g, "\\$&"); // $& means the whole matched string
}

function getWithoutScriptElement(regExp, htmlString) {
    const escapedRegExp = escapeRegExp(regExp);
    const re = new RegExp("<script\\b[^<]*" + escapedRegExp + ".*(?:(?!</script>)<[^<]*)*</script>", "im");

    return htmlString.replace(re, "");
}

function getWithoutStyleElement(regExp, htmlString) {
    const escapedRegExp = escapeRegExp(regExp);
    const re = new RegExp("<link.*" + escapedRegExp + ".*?>", "im");

    return htmlString.replace(re, "");
}

function main() {
    if (getParamValue('code') === undefined)
        return;

    if (getExtensionFile() === "html") {

        addScriptHead(getBaseUrl() + '/_extras/' + 'beautify-html.min.js', function() {  // https://github.com/beautify-web/js-beautify
            addScriptHead(getBaseUrl() + '/_extras/' + 'run_prettify.js', function() {   // https://github.com/googlearchive/code-prettify

                let htmlString = document.querySelector("html").outerHTML;
                htmlString = getWithoutScriptElement('/_extras/beautify-html.min.js', htmlString);
                htmlString = getWithoutScriptElement('/_extras/code-formatter.js', htmlString);
                htmlString = getWithoutScriptElement('/_extras/run_prettify.js', htmlString);
                htmlString = getWithoutStyleElement('/code-prettify@master/loader/prettify.css', htmlString);

                const htmlStringBeautify = getHtmlBeautify(htmlString);
                const textNode = document.createTextNode(htmlStringBeautify);
                createPreElement(textNode);
            });       
        });                                     
    }
}

main();







