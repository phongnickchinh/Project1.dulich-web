document.getElementById('imageUpload').addEventListener('change', function () {
    var preview = document.getElementById('previewImage');
    var file = this.files[0];
    var reader = new FileReader();

    reader.onloadend = function () {
        preview.src = reader.result;
        preview.style.display = 'block';
    }

    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.src = '';
        preview.style.display = 'none';
    }
});

function openConfirmDeleteForm() {
    // Hiển thị overlay và form xác nhận
    document.getElementsByClassName('overlay').style.display = 'block';
    document.getElementById('confirmDeleteForm').style.display = 'block';
}

function confirmDelete() {
    // Xử lý logic khi người dùng chọn "Có"
    alert("Đã xoá!");
    closeConfirmDeleteForm();
}

function cancelDelete() {
    // Xử lý logic khi người dùng chọn "Không"
    closeConfirmDeleteForm();
}