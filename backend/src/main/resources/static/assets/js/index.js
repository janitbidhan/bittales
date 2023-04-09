function login() {
  var uname = document.getElementById("username").value;
  var pwd = document.getElementById("password").value;
  alert("Thank You for Login & You are Redirecting to home page");
  //   window.open("home.html");
  if (uname === "jake@gmail.com" && pwd === "ram") {
    alert("Invalid Credentials");
  } else if (uname === "" && pwd === "") {
    return false;
  }
  true;
}
