// Trigger animation on load
window.addEventListener("load", () => {
  document
    .querySelector(".header__boot-image")
    .classList.add("header__boot-image--show");
});

// Navbar color change on scroll
window.addEventListener("scroll", () => {
  const navbar = document.querySelector(".navbar");
  if (window.scrollY > 50) {
    navbar.classList.add("navbar--scrolled");
  } else {
    navbar.classList.remove("navbar--scrolled");
  }
});

document.addEventListener("DOMContentLoaded", function () {
  const navbar = document.querySelector(".navbar");
  if (document.body.classList.contains("page-home")) {
    window.addEventListener("scroll", function () {
      if (window.scrollY > 50) {
        navbar.classList.add("navbar--scrolled");
      } else {
        navbar.classList.remove("navbar--scrolled");
      }
    });
  } else {
    // Apply dark background on other pages immediately
    navbar.classList.add("navbar--scrolled");
  }
});


