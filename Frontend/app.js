function suggest(value) {
    //document.getElementById("search-result-title").style.display = "none"
    var h1 = document.getElementById("search-result-title-with-count")
    h1.style.display="none";
    var searchResultList = document.getElementById("search-result-list")
    searchResultList.innerHTML = "";

    var lengthOfInput = value.length
    if (lengthOfInput >= 3) {
        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                if (xmlhttp.status == 200) {
                    //document.getElementById("search-result-list").style.display = "hidden"
                    //document.getElementById("suggestion-result-list").style.display = "block"
                    var ul = document.getElementById("suggestion-result-list");
                    ul.innerHTML = "";
                    var jsonResponse = JSON.parse(xmlhttp.responseText)
                    var i;
                    for (i = 0; i < jsonResponse.length; i++) {
                        var a = document.createElement("a");
                        a.setAttribute('class', "list-group-item");
                        var hrefString = "http://www." + jsonResponse[i]
                        a.setAttribute('href', hrefString)
                        a.setAttribute('target', "_blank") // to open in new tab
                        a.appendChild(document.createTextNode(jsonResponse[i])); // to add text
                        ul.appendChild(a)
                    }
                }
            }
        };


        xmlhttp.open("GET", "http://localhost:8088/domain/suggest/" + value, true);
        //xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
        xmlhttp.send();
    } else {
        var ul = document.getElementById("suggestion-result-list");
        ul.innerHTML = "";
    }
}


function search() {
    var xmlhttp = new XMLHttpRequest();
    var arr = ["list-group-item-dark", "list-group-item-light"]
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == XMLHttpRequest.DONE) {
            if (xmlhttp.status == 200) {
                var suggestionResultList = document.getElementById("suggestion-result-list")
                suggestionResultList.innerHTML = "";

                var ul = document.getElementById("search-result-list");
                ul.innerHTML = "";
                var h1 = document.getElementById("search-result-title-with-count")
                
               
                //document.getElementById("search-result-title").style.display = "block"
                var jsonResponse = JSON.parse(xmlhttp.responseText)
                h1.innerHTML ="Search Result:  " + jsonResponse.length
                h1.style.display="block"
                if (jsonResponse.length > 0) {
                    var i;
                    for (i = 0; i < jsonResponse.length; i++) {
                        var a = document.createElement("a");
                        var classString = "list-group-item " + arr[i % 2]
                        a.setAttribute('class', classString);
                        var hrefString = "http://www." + jsonResponse[i]
                        a.setAttribute('href', hrefString)
                        a.setAttribute('target', "_blank")
                        a.appendChild(document.createTextNode(jsonResponse[i]));
                        ul.appendChild(a)
                    }
                }else{
                    var h2 = document.createElement("h2")
                    h2.setAttribute("class","no-results-found-text")
                    h2.appendChild(document.createTextNode("No Results Found..."))
                    ul.appendChild(h2)
                }

            }

        }
    };

    var searchString = document.getElementById("search-bar").value
    xmlhttp.open("GET", "http://localhost:8088/domain/search/" + searchString, true);
    xmlhttp.send();
}

var input = document.getElementById("search-bar")
// Execute a function when the user releases a key on the keyboard
input.addEventListener("keyup", function(event) {
  // Cancel the default action, if needed
  event.preventDefault();
  // Number 13 is the "Enter" key on the keyboard
  if (event.keyCode === 13) {
    // Trigger the button element with a click
    document.getElementById("search-button").click();
  }
});