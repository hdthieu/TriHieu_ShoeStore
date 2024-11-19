document.addEventListener("DOMContentLoaded", function () {
  const statusRadios = document.querySelectorAll('input[name="status"]');
  const searchInput = document.getElementById("search-input");

  // Hàm gọi API để lấy dữ liệu từ server
  function fetchVouchers(status = "all", search = "") {
    const url = `http://localhost:8080/vouchers?status=${status}&search=${search}`;

    fetch(url)
      .then((response) => {
        if (!response.ok) {
          throw new Error("Không thể lấy dữ liệu");
        }
        return response.json();
      })
      .then((data) => {
        const voucherListBody = document.getElementById("voucher-list-body");
        voucherListBody.innerHTML = ""; // Clear previous data

        // Hiển thị danh sách khuyến mãi
        data.vouchers.forEach((voucher) => {
          const row = document.createElement("tr");

          // Tạo các ô dữ liệu cho mỗi cột
          row.innerHTML = `
            <td>${voucher.name}</td>
            <td>${voucher.description}</td>
            <td>${voucher.discountValue} ${voucher.discountType}</td>
            <td>${voucher.status}</td>
            <td>${voucher.startDate} đến ${voucher.endDate}</td>
          `;

          // Thêm hàng mới vào tbody
          voucherListBody.appendChild(row);
        });
      })
      .catch((error) => console.error("Lỗi khi gọi API:", error));
}


  // Xử lý sự thay đổi trạng thái
  statusRadios.forEach((radio) => {
    radio.addEventListener("change", function () {
      const status = document.querySelector(
        'input[name="status"]:checked'
      ).value;
      fetchVouchers(status, searchInput.value); // Gọi lại API khi trạng thái thay đổi
    });
  });

  // Xử lý tìm kiếm
  searchInput.addEventListener("input", function () {
    const search = searchInput.value;
    const status = document.querySelector('input[name="status"]:checked').value;
    fetchVouchers(status, search); // Gọi lại API khi người dùng tìm kiếm
  });

  // Lấy dữ liệu khi tải trang lần đầu (trạng thái mặc định là "all")
  fetchVouchers("all", "");
});
