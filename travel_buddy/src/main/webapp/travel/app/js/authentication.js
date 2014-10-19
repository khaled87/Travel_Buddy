    var Base64 = {
// private property
                _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
// public method for encoding

                encode: function (input) {
                    var output = "";
                    var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
                    var i = 0;

                    input = Base64._utf8_encode(input);

                    while (i < input.length) {

                        chr1 = input.charCodeAt(i++);
                        chr2 = input.charCodeAt(i++);
                        chr3 = input.charCodeAt(i++);

                        enc1 = chr1 >> 2;
                        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                        enc4 = chr3 & 63;

                        if (isNaN(chr2)) {
                            enc3 = enc4 = 64;
                        } else if (isNaN(chr3)) {
                            enc4 = 64;
                        }

                        output = output +
                                Base64._keyStr.charAt(enc1) + Base64._keyStr.charAt(enc2) +
                                Base64._keyStr.charAt(enc3) + Base64._keyStr.charAt(enc4);

                    }

                    return output;
                },
// public method for decoding
                decode: function (input) {
                    var output = "";
                    var chr1, chr2, chr3;
                    var enc1, enc2, enc3, enc4;
                    var i = 0;

                    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

                    while (i < input.length) {

                        enc1 = Base64._keyStr.indexOf(input.charAt(i++));
                        enc2 = Base64._keyStr.indexOf(input.charAt(i++));
                        enc3 = Base64._keyStr.indexOf(input.charAt(i++));
                        enc4 = Base64._keyStr.indexOf(input.charAt(i++));

                        chr1 = (enc1 << 2) | (enc2 >> 4);
                        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                        chr3 = ((enc3 & 3) << 6) | enc4;

                        output = output + String.fromCharCode(chr1);

                        if (enc3 != 64) {
                            output = output + String.fromCharCode(chr2);
                        }
                        if (enc4 != 64) {
                            output = output + String.fromCharCode(chr3);
                        }

                    }

                    output = Base64._utf8_decode(output);

                    return output;

                },
// private method for UTF-8 encoding
                _utf8_encode: function (string) {
                    string = string.replace(/\r\n/g, "\n");
                    var utftext = "";

                    for (var n = 0; n < string.length; n++) {

                        var c = string.charCodeAt(n);

                        if (c < 128) {
                            utftext += String.fromCharCode(c);
                        }
                        else if ((c > 127) && (c < 2048)) {
                            utftext += String.fromCharCode((c >> 6) | 192);
                            utftext += String.fromCharCode((c & 63) | 128);
                        }
                        else {
                            utftext += String.fromCharCode((c >> 12) | 224);
                            utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                            utftext += String.fromCharCode((c & 63) | 128);
                        }

                    }

                    return utftext;
                },
// private method for UTF-8 decoding
                _utf8_decode: function (utftext) {
                    var string = "";
                    var i = 0;
                    var c = c1 = c2 = 0;

                    while (i < utftext.length) {

                        c = utftext.charCodeAt(i);

                        if (c < 128) {
                            string += String.fromCharCode(c);
                            i++;
                        }
                        else if ((c > 191) && (c < 224)) {
                            c2 = utftext.charCodeAt(i + 1);
                            string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                            i += 2;
                        }
                        else {
                            c2 = utftext.charCodeAt(i + 1);
                            c3 = utftext.charCodeAt(i + 2);
                            string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                            i += 3;
                        }

                    }
                    return string;
                }
            }


            var count = 2;
            function validate() {
                var un = document.loginform.username.value;
                var pw = document.loginform.pword.value;
                var conpw = document.loginform.conpword.value;
                var encodedpw = Base64.encode(pw);
                var decodepw = Base64.decode(encodedpw);

                //var encrypted = CryptoJS.AES.encrypt("Message", pw);
                //var decrypted = CryptoJS.AES.decrypt("Message", pw);

                //alert(encrypted);
                //alert(decrypted);

                var valid = false;
                //just array data should be replaced to be coneccted to DB
                var unArray = ["khaled", "ahmad", "sarah", "duy"];  // as many as you like - no comma after final entry
                var pwArray = ["Khaled123", "Password2", "Password3", "Password4"];  // the corresponding passwords;


                if (un == "" && decodepw == "") {
                    alert("Please enter your username and password");
                }

                if (decodepw == "") {
                    alert("Please enter your password");
                    loginform.pword.focus();
                    return false;
                }
                if (conpw == "") {
                    alert("Please enter the confirm password");
                    loginform.conpword.focus();
                    return false;
                }
                if (decodepw.length < 8) {
                    alert("Password should be at least 8 charachters");
                    loginform.pword.focus();
                    return false;
                }

                if (decodepw.length > 12) {
                    alert("Password should be maximum 12 charachters");
                    loginform.pword.focus();
                    return false;
                }

                if (conpw != decodepw) {
                    alert("Password confirmation does not match!");
                    return false;
                }

                //the below function "checkPassword(str)" we use it in sigen up restrection 
                /*function checkPassword(str) {
                 
                 var re = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/;
                 return re.test(str);
                 }
                 re = /[0-9]/;
                 if (!re.test(pw)) {
                 alert("Error: password must contain at least one number (0-9)!");
                 
                 }
                 re = /[a-z]/;
                 if (!re.test(pw)) {
                 alert("Error: password must contain at least one lowercase letter (a-z)!");
                 
                 }
                 re = /[A-Z]/;
                 if (!re.test(pw)) {
                 alert("Error: password must contain at least one uppercase letter (A-Z)!");
                 
                 }*/
                for (var i = 0; i < unArray.length; i++) {
                    if ((un == unArray[i]) && (decodepw == pwArray[i])) {
                        valid = true;
                        break;
                    }
                }

                if (valid) {
                    alert("Login was successful");
                    window.location.href = '../app/partials/authentication/admin_page.html';
                    return false;
                }

                var t = " tries";
                if (count == 1) {
                    t = " try"
                }

                if (count >= 1) {
                    alert("Invalid username and/or password.  You have " + count + t + " left.");
                    document.loginform.username.value = "";
                    document.loginform.pword.value = "";
                    document.loginform.conpword.value = "";
                    setTimeout("document.myform.username.focus()", 25);
                    setTimeout("document.myform.username.select()", 25);
                    count--;
                }

                else {
                    alert("Still incorrect! You have no more tries left!");
                    document.loginform.username.value = "No more tries allowed!";
                    document.loginform.pword.value = "";
                    document.loginform.conpword.value = "";
                    document.loginform.username.disabled = true;
                    document.loginform.pword.disabled = true;
                    document.loginform.conpword.disabled = true;
                    return false;
                }

            }