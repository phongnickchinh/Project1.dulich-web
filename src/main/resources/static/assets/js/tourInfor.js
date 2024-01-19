function calculateTotal() {
    var adults = document.getElementById("adults").value;
    var children6to11 = document.getElementById("children6to11").value;
    var childrenUnder6 = document.getElementById("childrenUnder6").value;

    var totalPrice = calculatePrice(adults, children6to11, childrenUnder6);
    
    document.getElementById("totalPrice").innerText = "Tổng số tiền: " + totalPrice + " đồng";
}

function calculatePrice(adults, children6to11, childrenUnder6) {
    var tourPrice = document.getElementById("price").value;
    var totalPrice = adults * tourPrice + 0.5 * children6to11 * tourPrice + 0.3 * childrenUnder6 * tourPrice;
    return totalPrice;
}

    // Lắng nghe sự kiện thay đổi của <select>
    document.getElementById('filter-content').addEventListener('change', function () {
        // Lấy giá trị được chọn
        var selectedValue = this.value;

        // Thực hiện xử lý sắp xếp tùy thuộc vào giá trị được chọn
        if (selectedValue === 'nearlyDate') {
            //sắp xếp theo ngày gần nhất trong tất cả các tour trong listTour d

        } else if (selectedValue === 'increPrice') {
            // Sắp xếp theo giá tăng dần
            // ...

        } else if (selectedValue === 'decrePrice') {
            // Sắp xếp theo giá giảm dần
            // ...

        } else if (selectedValue === 'increLenght') {
            // Sắp xếp theo thời gian tăng dần
            // ...

        } else if (selectedValue === 'decreLenght') {
            // Sắp xếp theo thời gian giảm dần
            // ...
        }
    });