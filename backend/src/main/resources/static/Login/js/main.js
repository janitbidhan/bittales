function login() {
  var uname = document.getElementById("username").value;
  var pwd = document.getElementById("password").value;
  alert("Thank You for Login & You are Redirecting to home page");

  //   var filter = "/^([a-zA-Z0-9_.-])+@(([a-zA-Z0-9-])+.)+([a-zA-Z0-9]{2,4})+$/";
  //   if (uname == "") {
  //     alert("please enter user name.");
  //   } else if (pwd == "") {
  //     alert("enter the password");
  //   } else if (!filter.test(uname)) {
  //     alert("Enter valid email id.");
  //   } else if (pwd.length < 6 || pwd.length > 6) {
  //     alert("Password min and max length is 6.");
  //   } else {
  //     alert("Thank You for Login & You are Redirecting to home page");
  //     //Redirecting to other page or webste code or you can set your own html page.
  //     // window.location = "home.html";
  //   }

  //   window.open("home.html");
  if (uname == "admin" && pwd == "admin") {
    window.open("pages/admin.html");
  } else if (uname == "" && pwd == "") {
    return false;
  }
  true;
}
