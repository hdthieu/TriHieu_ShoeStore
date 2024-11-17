document.querySelectorAll(".cart-item__quantity-btn").forEach((button) => {
  button.addEventListener("click", (e) => {
    const isIncrement = e.target.textContent === "+";
    const quantityElement = e.target.parentNode.querySelector(
      ".cart-item__quantity-number"
    );
    let quantity = parseInt(quantityElement.textContent);

    if (isIncrement) {
      quantity += 1;
    } else if (quantity > 1) {
      quantity -= 1;
    }

    quantityElement.textContent = quantity;
  });
});

fetch("breadcrumb.html")
  .then((response) => response.text())
  .then((data) => {
    document.getElementById("breadcrumb-container").innerHTML = data;

    // Cập nhật tên trang sau khi breadcrumb được tải
    document.getElementById("current-page").textContent = "Cart";
  });
