document.addEventListener("DOMContentLoaded", function () {
  // Load header
  fetch("header.html")
    .then((response) => response.text())
    .then((html) => {
      document.getElementById("header").innerHTML = html;
    })
    .catch((error) => console.error("Error loading the header:", error));

  // Load footer
  fetch("footer.html")
    .then((response) => response.text())
    .then((html) => {
      document.getElementById("footer").innerHTML = html;
    })
    .catch((error) => console.error("Error loading the footer:", error));

});
