
        var request = myCreateXMLHttpRequest();

        function myCreateXMLHttpRequest() {
            try { return new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) { }
            try { return new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) { }
            try { return new XMLHttpRequest(); } catch (e) { }
            return null;
        }

        function myOnKeyUp() {

            if (request != null) {
                var textField = document.getElementById("myInputField");
                var url = "rest/RestFeed?str=" + textField.value;

                request.open("GET", url, true);
                request.onreadystatechange = myHandleCallback;
                request.send(null);
            }
        }

        function myOnKeyUp1() {

            if (request != null) {
                var textField = document.getElementById("myInputField1");
                var url = "rest/Strat1?str=" + textField.value;

                request.open("GET", url, true);
               // request.onreadystatechange = myHandleCallback;
                request.send(null);
            }
        }
        function myOnKeyUp2() {

            if (request != null) {
                //var textField = document.getElementById("myInputField");
                var url = "rest/RestFeed2";

                request.open("GET", url, true);
                request.onreadystatechange = myHandleCallback2;
                request.send(null);
            }
        }

        function myHandleCallback() {

            if (request.readyState == 4 && request.status == 200) {
                var outputField = document.getElementById("myEchoField");
                outputField.innerHTML = request.responseText;
            }
        }

        function myHandleCallback2() {

            if (request.readyState == 4 && request.status == 200) {
                var outputField = document.getElementById("myEchoField2");
                outputField.innerHTML = request.responseText;
            }
        }