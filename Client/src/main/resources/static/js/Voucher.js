// function setActive(element) {
//     let menuItems = document.querySelectorAll(".sidebar .nav-link");
//     menuItems.forEach((item) => item.classList.remove("active-border"));
//     element.classList.add("active-border");
// }
// const apiUrl = 'http://localhost:8080/vouchers';
//
// // Hàm tải danh sách khuyến mãi
// async function loadVoucherList() {
//     try {
//         const response = await fetch(apiUrl);
//         if (response.ok) {
//             const responseData = await response.json();
//             const vouchers = responseData.vouchers || []; // Truy cập trường 'vouchers' chứa mảng vouchers
//
//             const voucherListContainer = document.getElementById('voucher-list');
//             if (!voucherListContainer) {
//                 console.error("Không tìm thấy phần tử 'voucher-list' trong DOM.");
//                 return;
//             }
//
//             voucherListContainer.innerHTML = ''; // Xóa danh sách cũ
//
//             vouchers.forEach(voucher => {
//                 const voucherElement = document.createElement('tr');  // Sử dụng <tr> thay vì <div> cho bảng
//                 voucherElement.classList.add('voucher-item');
//                 voucherElement.innerHTML = `
//                     <td>${voucher.voucherID}</td>
//                     <td>${voucher.name}</td>
//                     <td>${voucher.discountValue}</td>
//                     <td>${voucher.startDate}</td>
//                     <td>${voucher.endDate}</td>
//                     <td>${voucher.status}</td>
//                     <td>${voucher.minValueOrder}</td>
//                     <td>
//                         <button class="btn-success rounded">Chỉnh Sửa</button> |
//                         <button class="btn-danger rounded" onclick="deleteVoucher(${voucher.voucherID})">Xóa</button>
//                     </td>
//                 `;
//                 voucherListContainer.appendChild(voucherElement);
//             });
//         }
//     } catch (err) {
//         console.error('Lỗi khi tải danh sách khuyến mãi:', err);
//     }
// }
//
// // Hàm xóa khuyến mãi
// async function deleteVoucher(voucherID) {
//     const confirmation = confirm("Bạn có chắc chắn muốn xóa khuyến mãi này?");
//     if (!confirmation) return;
//
//     try {
//         const response = await fetch(`${apiUrl}/${voucherID}`, {
//             method: 'DELETE',
//         });
//
//         if (response.ok) {
//             alert('Khuyến mãi đã được xóa!');
//             loadVoucherList(); // Tải lại danh sách sau khi xóa
//         } else {
//             alert('Xóa khuyến mãi thất bại!');
//         }
//     } catch (err) {
//         console.error('Lỗi khi xóa khuyến mãi:', err);
//         alert('Có lỗi xảy ra khi xóa khuyến mãi!');
//     }
// }
//
// // Gọi hàm loadVoucherList khi trang web được tải hoặc sau khi thêm một voucher mới
// document.addEventListener('DOMContentLoaded', loadVoucherList);  // Đảm bảo danh sách được tải khi trang load
//
//
// // Lắng nghe sự kiện submit của form
// document.getElementById('voucher-form').addEventListener('submit', async function (e) {
//     e.preventDefault(); // Ngăn form gửi theo cách mặc định
//
//     // Lấy giá trị từ các trường input
//     const name = document.getElementById('tenPhieuGiamGia').value.trim();
//     const discountType = document.querySelector('input[name="discount-type"]:checked');
//     const discountValue = document.getElementById('phanTramGiam').value.trim();
//     const minValueOrder = document.getElementById('giaTriDonToiThieu').value.trim();
//     const startDate = document.getElementById('ngayBatDau').value.trim();
//     const endDate = document.getElementById('ngayKetThuc').value.trim();
//     const description = document.getElementById('moTa').value.trim();
//
//     // Danh sách lỗi
//     const errors = [];
//
//     // Kiểm tra từng trường
//     if (!name) {
//         errors.push('Vui lòng nhập tên phiếu giảm giá.');
//     }
//     if (!discountType) {
//         errors.push('Vui lòng chọn loại giảm giá.');
//     }
//     if (!discountValue || isNaN(discountValue) || parseFloat(discountValue) <= 0) {
//         errors.push('Vui lòng nhập giá trị giảm hợp lệ.');
//     }
//     if (!minValueOrder || isNaN(minValueOrder) || parseFloat(minValueOrder) < 0) {
//         errors.push('Vui lòng nhập giá trị đơn tối thiểu hợp lệ.');
//     }
//     if (!startDate) {
//         errors.push('Vui lòng chọn ngày bắt đầu.');
//     }
//     if (!endDate) {
//         errors.push('Vui lòng chọn ngày kết thúc.');
//     }
//     if (!description) {
//         errors.push('Vui lòng nhập mô tả.');
//     }
//
//     // Kiểm tra logic ngày
//     const today = new Date();
//     const start = new Date(startDate);
//     const end = new Date(endDate);
//
//     if (startDate && endDate) {
//         if (end < start) {
//             errors.push('Ngày kết thúc không được nhỏ hơn ngày bắt đầu.');
//         }
//         if (end < today) {
//             errors.push('Ngày kết thúc không được nhỏ hơn ngày hiện tại.');
//         }
//     }
//
//     // Hiển thị lỗi nếu có
//     if (errors.length > 0) {
//         alert(errors.join('\n')); // Hiển thị tất cả các lỗi
//         return;
//     }
//
//     // Nếu không có lỗi, tiếp tục xử lý dữ liệu
//     const voucherData = {
//         name,
//         discountType: discountType.value,
//         discountValue: parseFloat(discountValue),
//         minValueOrder: parseFloat(minValueOrder),
//         startDate,
//         endDate,
//         description
//     };
//
//     try {
//         const response = await fetch(apiUrl + '/add', {
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(voucherData)
//         });
//
//         if (response.ok) {
//             const result = await response.json();
//             alert('Thêm khuyến mãi thành công!');
//             console.log(result);
//             loadVoucherList();
//             document.getElementById('voucher-form').reset();
//         } else {
//             const error = await response.json();
//             alert(`Thêm khuyến mãi thất bại: ${error.message}`);
//         }
//     } catch (err) {
//         console.error(err);
//         alert('Có lỗi xảy ra khi thêm khuyến mãi!');
//     }
// });
//
//
//
// document.getElementById('voucher-form-edit').addEventListener('submit', async function (e) {
//     e.preventDefault();
//
//     const voucherID = this.dataset.currentId;  // ID của voucher cần cập nhật
//     const updatedVoucherData = {
//         name: document.getElementById('tenPhieuGiamGiaEdit').value,
//         discountType: document.querySelector('input[name="discount-type"]:checked').value,
//         discountValue: parseFloat(document.getElementById('phanTramGiam-edit').value),
//         minValueOrder: parseFloat(document.getElementById('giaTriDonToiThieu-edit').value),
//         startDate: document.getElementById('ngayBatDau-edit').value,
//         endDate: document.getElementById('ngayKetThuc-edit').value,
//         description: document.getElementById('moTa-edit').value
//     };
//
//     try {
//         const response = await fetch(`${apiUrl}/${voucherID}`, {
//             method: 'PUT',  // Đảm bảo là PUT để cập nhật dữ liệu
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify(updatedVoucherData)  // Chuyển đổi dữ liệu thành JSON
//         });
//
//         if (response.ok) {
//             alert('Cập nhật khuyến mãi thành công!');
//             loadVoucherList();  // Tải lại danh sách khuyến mãi
//             this.reset();  // Đặt lại form sau khi cập nhật thành công
//         } else {
//             const error = await response.json();
//             alert(`Cập nhật thất bại: ${error.message}`);
//         }
//     } catch (error) {
//         console.error('Lỗi khi cập nhật khuyến mãi:', error);
//         alert('Có lỗi xảy ra khi cập nhật khuyến mãi!');
//     }
// });
//
// document.getElementById('voucher-list').addEventListener('click', async (e) => {
//     if (e.target.classList.contains('btn-success')) {
//         const voucherID = e.target.closest('tr').children[0].textContent; // Lấy ID từ hàng tương ứng.
//
//         try {
//             const response = await fetch(`${apiUrl}/${voucherID}`);
//             console.log(response)
//             if (response.ok) {
//                 const voucher = await response.json();
//
//                 // Hiển thị thông tin lên form chỉnh sửa
//                 document.getElementById('tenPhieuGiamGiaEdit').value = voucher.name;
//                 document.querySelector(`input[name="discount-type"][value="${voucher.discountType}"]`).checked = true;
//                 document.getElementById('phanTramGiam-edit').value = voucher.discountValue;
//                 document.getElementById('giaTriDonToiThieu-edit').value = voucher.minValueOrder;
//                 document.getElementById('ngayBatDau-edit').value = voucher.startDate;
//                 document.getElementById('ngayKetThuc-edit').value = voucher.endDate;
//                 document.getElementById('moTa-edit').value = voucher.description;
//
//                 // Thêm ID hiện tại vào một biến để sử dụng khi cập nhật
//                 document.getElementById('voucher-form-edit').dataset.currentId = voucherID;
//             } else {
//                 alert('Không thể tải dữ liệu khuyến mãi!');
//             }
//         } catch (error) {
//             console.error('Lỗi khi lấy thông tin khuyến mãi:', error);
//         }
//     }
// });
//
// async function handleSearch() {
//     const startDate = document.getElementById("searchStartDate").value.trim();
//     const endDate = document.getElementById("searchEndDate").value.trim();
//
//     console.log("startDate:", startDate);  // In giá trị startDate
//     console.log("endDate:", endDate);      // In giá trị endDate
//
//     let queryString = '';
//
//     // Kiểm tra các điều kiện tìm kiếm
//     if (startDate) {
//         if (!isValidDate(startDate)) {
//             alert("Ngày bắt đầu không hợp lệ");
//             return;
//         }
//         queryString += `startDate=${encodeURIComponent(startDate)}&`;
//     }
//     if (endDate) {
//         if (!isValidDate(endDate)) {
//             alert("Ngày kết thúc không hợp lệ");
//             return;
//         }
//         queryString += `endDate=${encodeURIComponent(endDate)}&`;
//     }
//
//     // Loại bỏ dấu '&' thừa ở cuối queryString
//     if (queryString.endsWith('&')) {
//         queryString = queryString.slice(0, -1);
//     }
//
//     const apiUrl = 'http://localhost:8080/vouchers/search';  // Endpoint tìm kiếm
//
//     try {
//         const response = await fetch(`${apiUrl}?${queryString}`);
//         if (response.ok) {
//             const responseData = await response.json();
//
//             // Kiểm tra xem có thông báo không có voucher hay không
//             if (responseData.message) {
//                 alert(responseData.message);  // Hiển thị thông báo cho người dùng
//                 return;
//             }
//
//             const vouchers = responseData.vouchers || [];
//
//             const voucherListContainer = document.getElementById('voucher-list');
//             if (!voucherListContainer) {
//                 console.error("Không tìm thấy phần tử 'voucher-list' trong DOM.");
//                 return;
//             }
//
//             voucherListContainer.innerHTML = '';  // Xóa danh sách cũ
//
//             // Hiển thị các voucher nếu có
//             vouchers.forEach(voucher => {
//                 const voucherElement = document.createElement('tr');
//                 voucherElement.classList.add('voucher-item');
//                 voucherElement.innerHTML = `
//                     <td>${voucher.voucherID}</td>
//                     <td>${voucher.name}</td>
//                     <td>${voucher.discountValue}</td>
//                     <td>${voucher.startDate}</td>
//                     <td>${voucher.endDate}</td>
//                     <td>${voucher.status}</td>
//                     <td>${voucher.minValueOrder}</td>
//                     <td>
//                         <button class="btn-success rounded">Chỉnh Sửa</button> |
//                         <button class="btn-danger rounded" onclick="deleteVoucher(${voucher.voucherID})">Xóa</button>
//                     </td>
//                 `;
//                 voucherListContainer.appendChild(voucherElement);
//             });
//         } else {
//             console.error('Lỗi khi tìm kiếm voucher:', response.status);
//         }
//     } catch (err) {
//         console.error('Lỗi khi gọi API tìm kiếm:', err);
//     }
// }
//
//
//
// // Hàm kiểm tra định dạng ngày hợp lệ (yyyy-MM-dd)
// function isValidDate(date) {
//     const regex = /^\d{4}-\d{2}-\d{2}$/;
//     return regex.test(date);
// }
//
//
//
//
// // Gọi hàm loadVoucherList khi trang web được tải hoặc sau khi thêm một voucher mới
// document.addEventListener('DOMContentLoaded', loadVoucherList);  // Đảm bảo danh sách được tải khi trang load
//
//



document.getElementById("voucher-form").addEventListener("submit", function (event) {
    const name = document.getElementById("tenPhieuGiamGia").value.trim();
    const description = document.getElementById("moTa").value.trim();
    const discountType = document.querySelector('input[name="discount-type"]:checked')?.value;
    const discountValue = parseFloat(document.getElementById("phanTramGiam").value);
    const minValueOrder = parseFloat(document.getElementById("giaTriDonToiThieu").value);
    const startDate = document.getElementById("ngayBatDau").value;
    const endDate = document.getElementById("ngayKetThuc").value;

    const errors = [];

    // Kiểm tra các trường bắt buộc
    if (!name) {
        errors.push("Vui lòng nhập tên phiếu giảm giá.");
    }
    if (!description) {
        errors.push("Vui lòng nhập mô tả.");
    }
    if (!discountType) {
        errors.push("Vui lòng chọn kiểu giảm giá.");
    }
    if (!discountValue || isNaN(discountValue) || discountValue <= 0) {
        errors.push("Vui lòng nhập giá trị giảm giá hợp lệ.");
    }
    if (!minValueOrder || isNaN(minValueOrder) || minValueOrder < 0) {
        errors.push("Vui lòng nhập giá trị đơn tối thiểu hợp lệ.");
    }
    if (!startDate) {
        errors.push("Vui lòng chọn ngày bắt đầu.");
    }
    if (!endDate) {
        errors.push("Vui lòng chọn ngày kết thúc.");
    }

    // Kiểm tra logic ngày
    const today = new Date();
    const start = new Date(startDate);
    const end = new Date(endDate);

    if (startDate && endDate) {
        if (end < start) {
            errors.push("Ngày kết thúc không được nhỏ hơn ngày bắt đầu.");
        }
        if (end < today) {
            errors.push("Ngày kết thúc không được nhỏ hơn ngày hiện tại.");
        }
    }

    // Nếu có lỗi, hiển thị và dừng xử lý
    if (errors.length > 0) {
        alert(errors.join("\n"));
        return;
    }
    fetch("/vouchers/add", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            name,
            description,
            discountType,
            discountValue,
            minValueOrder,
            startDate,
            endDate,
        }),
    })
        .then(response => {
            if (response.ok) {
                alert("Voucher added successfully!");
                return response.json();
                location.reload(); // Reload lại trang để cập nhật danh sách
            } else {
                throw new Error("Có lỗi xảy ra khi thêm voucher.");
            }
        })
        .catch(error => {
            alert("Error: " + error.message);
        });
});


document.addEventListener("DOMContentLoaded", function () {
    const deleteButtons = document.querySelectorAll(".delete-btn");

    deleteButtons.forEach((button) => {
        button.addEventListener("click", function () {
            const voucherID = this.getAttribute("data-id");

            if (confirm("Bạn có chắc chắn muốn xóa voucher này không?")) {
                fetch(`/vouchers/delete/${voucherID}`, {
                    method: "DELETE",
                })
                    .then((response) => {
                        if (response.ok) {
                            alert("Xóa voucher thành công!");
                            // Xóa dòng tương ứng trong bảng HTML
                            this.closest("tr").remove();
                        } else {
                            throw new Error("Không thể xóa voucher. Vui lòng thử lại!");
                        }
                    })
                    .catch((error) => {
                        console.error(error);
                        alert("Lỗi: " + error.message);
                    });
            }
        });
    });
});

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".btn-primary").forEach((button) => {
        button.addEventListener("click", (event) => {
            const row = event.target.closest("tr");
            const voucherId = row.querySelector("td:first-child").textContent;

            // Gọi API để lấy thông tin voucher
            fetch(`/admin/vouchers/${voucherId}`)
                .then((response) => {
                    if (response.ok) {
                        return response.json();
                    }
                    throw new Error("Không thể lấy thông tin voucher");
                })
                .then((voucher) => {
                    // Hiển thị thông tin lên form
                    document.getElementById("tenPhieuGiamGiaEdit").value = voucher.name;
                    document.getElementById("moTa-edit").value = voucher.description;
                    document.getElementById("phanTramGiam-edit").value = voucher.discountValue;
                    document.getElementById("giaTriDonToiThieu-edit").value = voucher.minValueOrder;
                    document.getElementById("ngayBatDau-edit").value = voucher.startDate;
                    document.getElementById("ngayKetThuc-edit").value = voucher.endDate;

                    // Set radio button
                    document.getElementById(
                        voucher.discountType === "Percentage"
                            ? "discount-percentage-edit"
                            : "discount-flat-edit"
                    ).checked = true;
                    document.getElementById("voucher-form-edit").dataset.voucherId = voucherId;
                })
                .catch((error) => {
                    alert(error.message);
                    console.error(error);
                });
        });
    });
});

document.getElementById("voucher-form-edit").addEventListener("submit", function (event) {
    event.preventDefault();
    const voucherId = this.dataset.voucherId;
    const name = document.getElementById("tenPhieuGiamGiaEdit").value.trim();
    const description = document.getElementById("moTa-edit").value.trim();
    const discountType = document.querySelector('input[name="discount-type"]:checked')?.value;
    const discountValue = parseFloat(document.getElementById("phanTramGiam-edit").value);
    const minValueOrder = parseFloat(document.getElementById("giaTriDonToiThieu-edit").value);
    const startDate = document.getElementById("ngayBatDau-edit").value;
    const endDate = document.getElementById("ngayKetThuc-edit").value;

    const errors = [];
    if (!name) {
        errors.push("Vui lòng nhập tên phiếu giảm giá.");
    }
    if (!description) {
        errors.push("Vui lòng nhập mô tả.");
    }
    if (!discountType) {
        errors.push("Vui lòng chọn kiểu giảm giá.");
    }
    if (!discountValue || isNaN(discountValue) || discountValue <= 0) {
        errors.push("Vui lòng nhập giá trị giảm giá hợp lệ.");
    }
    if (!minValueOrder || isNaN(minValueOrder) || minValueOrder < 0) {
        errors.push("Vui lòng nhập giá trị đơn tối thiểu hợp lệ.");
    }
    if (!startDate) {
        errors.push("Vui lòng chọn ngày bắt đầu.");
    }
    if (!endDate) {
        errors.push("Vui lòng chọn ngày kết thúc.");
    }
    const today = new Date();
    const start = new Date(startDate);
    const end = new Date(endDate);

    if (startDate && endDate) {
        if (end < start) {
            errors.push("Ngày kết thúc không được nhỏ hơn ngày bắt đầu.");
        }
        if (end < today) {
            errors.push("Ngày kết thúc không được nhỏ hơn ngày hiện tại.");
        }
    }
    if (errors.length > 0) {
        alert(errors.join("\n"));
        return;
    }
    const updatedVoucher = {
        name: name,
        description: description,
        discountType: discountType,
        discountValue: discountValue,
        minValueOrder: minValueOrder,
        startDate: startDate,
        endDate: endDate,
    };
    fetch(`/admin/vouchers/${voucherId}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify(updatedVoucher),
    })
        .then((response) => {
            if (response.ok) {
                return response.json();
            }
            throw new Error("Cập nhật không thành công");
        })
        .then((data) => {
            alert(`Voucher ${data.name} đã được cập nhật thành công!`);
            location.reload(); // Reload lại trang để cập nhật danh sách
        })
        .catch((error) => {
            alert(error.message);
            console.error(error);
        });
});

// search
function handleSearch() {
    // Lấy giá trị ngày từ các input
    const startDate = document.getElementById("searchStartDate").value;
    const endDate = document.getElementById("searchEndDate").value;

    // Tạo URL với tham số ngày
    let url = `http://localhost:8080/vouchers/search?startDate=${startDate}&endDate=${endDate}`;

    fetch(url)
        .then((response) => response.json())
        .then((data) => {
            const tableBody = document.getElementById("voucher-list");

            // Xóa dữ liệu cũ trong bảng
            tableBody.innerHTML = "";

            // Kiểm tra nếu có dữ liệu trả về
            if (data.vouchers && data.vouchers.length === 0) {
                alert("Không có voucher nào trong khoảng thời gian này");
            } else {
                console.log("Danh sách voucher tìm thấy: ", data.vouchers);

                // Duyệt qua danh sách vouchers và thêm vào bảng
                data.vouchers.forEach(voucher => {
                    const row = document.createElement("tr");

                    // Tạo các ô (td) cho từng cột trong bảng
                    row.innerHTML = `
                        <td>${voucher.voucherID}</td>
                        <td>${voucher.name}</td>
                        <td>${voucher.description}</td>
                        <td>${voucher.startDate}</td>
                        <td>${voucher.endDate}</td>
                    `;

                    // Thêm dòng mới vào bảng
                    tableBody.appendChild(row);
                });
            }
        })
        .catch((error) => {
            alert("Đã có lỗi xảy ra khi tìm kiếm: " + error.message);
            console.error(error);
        });
}







