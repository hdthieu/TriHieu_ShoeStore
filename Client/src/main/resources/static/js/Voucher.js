function setActive(element) {
    let menuItems = document.querySelectorAll(".sidebar .nav-link");
    menuItems.forEach((item) => item.classList.remove("active-border"));
    element.classList.add("active-border");
}
const apiUrl = 'http://localhost:8080/vouchers';

// Hàm tải danh sách khuyến mãi
async function loadVoucherList() {
    try {
        const response = await fetch(apiUrl);
        if (response.ok) {
            const responseData = await response.json();
            const vouchers = responseData.vouchers || []; // Truy cập trường 'vouchers' chứa mảng vouchers

            const voucherListContainer = document.getElementById('voucher-list');
            if (!voucherListContainer) {
                console.error("Không tìm thấy phần tử 'voucher-list' trong DOM.");
                return;
            }

            voucherListContainer.innerHTML = ''; // Xóa danh sách cũ

            vouchers.forEach(voucher => {
                const voucherElement = document.createElement('tr');  // Sử dụng <tr> thay vì <div> cho bảng
                voucherElement.classList.add('voucher-item');
                voucherElement.innerHTML = `
                    <td>${voucher.voucherID}</td>
                    <td>${voucher.name}</td>
                    <td>${voucher.discountValue}</td>
                    <td>${voucher.startDate}</td>
                    <td>${voucher.endDate}</td>
                    <td>${voucher.status}</td>
                    <td>
                        <button class="btn-success rounded">Chỉnh Sửa</button> |
                        <button class="btn-danger rounded" onclick="deleteVoucher(${voucher.voucherID})">Xóa</button>
                    </td>
                `;
                voucherListContainer.appendChild(voucherElement);
            });
        }
    } catch (err) {
        console.error('Lỗi khi tải danh sách khuyến mãi:', err);
    }
}

// Hàm xóa khuyến mãi
async function deleteVoucher(voucherID) {
    const confirmation = confirm("Bạn có chắc chắn muốn xóa khuyến mãi này?");
    if (!confirmation) return;

    try {
        const response = await fetch(`${apiUrl}/${voucherID}`, {
            method: 'DELETE',
        });

        if (response.ok) {
            alert('Khuyến mãi đã được xóa!');
            loadVoucherList(); // Tải lại danh sách sau khi xóa
        } else {
            alert('Xóa khuyến mãi thất bại!');
        }
    } catch (err) {
        console.error('Lỗi khi xóa khuyến mãi:', err);
        alert('Có lỗi xảy ra khi xóa khuyến mãi!');
    }
}

// Gọi hàm loadVoucherList khi trang web được tải hoặc sau khi thêm một voucher mới
document.addEventListener('DOMContentLoaded', loadVoucherList);  // Đảm bảo danh sách được tải khi trang load


// Lắng nghe sự kiện submit của form
// Lắng nghe sự kiện submit của form
document.getElementById('voucher-form').addEventListener('submit', async function (e) {
    e.preventDefault(); // Ngăn form gửi theo cách mặc định

    // Thu thập dữ liệu từ form
    const voucherData = {
        name: document.getElementById('tenPhieuGiamGia').value,
        discountType: document.querySelector('input[name="discount-type"]:checked').value,
        discountValue: parseFloat(document.getElementById('phanTramGiam').value),
        minimumOrderValue: parseFloat(document.getElementById('giaTriDonToiThieu').value),
        startDate: document.getElementById('ngayBatDau').value,
        endDate: document.getElementById('ngayKetThuc').value,
        description: document.getElementById('moTa').value // Lấy mô tả
    };

    try {
        // Gửi dữ liệu tới server bằng Fetch API
        const response = await fetch(apiUrl + '/add', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(voucherData)
        });

        if (response.ok) {
            const result = await response.json();
            alert('Thêm khuyến mãi thành công!');
            console.log(result); // Kiểm tra dữ liệu trả về từ server

            // Gọi lại danh sách khuyến mãi mà không làm thay đổi giá trị input
            loadVoucherList();

            // Reset các trường input trong form sau khi gửi thành công
            document.getElementById('voucher-form').reset(); // Đặt lại form
        } else {
            const error = await response.json();
            alert(`Thêm khuyến mãi thất bại: ${error.message}`);
        }
    } catch (err) {
        console.error(err);
        alert('Có lỗi xảy ra khi thêm khuyến mãi!');
    }
});


// Gọi hàm loadVoucherList khi trang web được tải hoặc sau khi thêm một voucher mới
document.addEventListener('DOMContentLoaded', loadVoucherList);  // Đảm bảo danh sách được tải khi trang load
