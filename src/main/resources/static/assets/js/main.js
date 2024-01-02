function toggleForm(idForm) {
    var form = document.getElementById(idForm);
    var overlay = document.getElementById('overlay');

    if (form.style.display === "none") {
        form.style.display = "block";
        overlay.style.display = "block";
    } else {
        form.style.display = "none";
        overlay.style.display = "none";
    }
}

document.getElementById('overlay').addEventListener('click', function() {
    document.getElementById('hiddenRequestInfor').style.display = "none";
    this.style.display = "none";
});

document.getElementById('overlay').addEventListener('click', function() {
    document.getElementById('hiddenRequestInfor').style.display = "none";
    this.style.display = "none";
});


document.getElementById('tabDecription').classList.add('active');

function showTab(tabId) {
    // Ẩn tất cả các tab
    var tabs = document.getElementsByClassName('decripContent');
    for (var i = 0; i < tabs.length; i++) {
        tabs[i].classList.remove('active');
    }

    // Hiển thị tab được chọn
    document.getElementById(tabId).classList.add('active');
}

function checkLogin() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    if (username === "p" && password === "1") {
        window.location.href = "index.html";
        return false;

    }
}

function changeStatus() {
    var button1 = document.querySelector('.requestStatus-1');
    var button0 = document.querySelector('.requestStatus-0');
    var requestDiv = document.getElementById('request');

    if (button1.style.display !== 'none') {
        button1.style.display = 'none';
        button0.style.display = 'inline-block';
        requestDiv.style.backgroundColor = '#EEF296';
    } else {
        button0.style.display = 'none';
        button1.style.display = 'inline-block';
        requestDiv.style.backgroundColor = '#9ADE7B';
    }
}

var closeButton = document.getElementById('outForm');
var form = document.getElementById('hiddenSetTourForm');
var overlay = document.getElementById('overlay');

closeButton.addEventListener('click', function() {
    form.style.display = "none";
    overlay.style.display = "none";
});

function submitForm() {
    var name = document.getElementById('name').value;
    var phone = document.getElementById('phone').value;
    var address = document.getElementById('address').value;
    var startDate = document.getElementById('startDate').value;
    var number = document.getElementById('number').value;

    if (name && phone && address && startDate && number) {
        showConfirmationMessage();
        // Hiển thị lại form sau 3 giây
        setTimeout(function () {
            document.getElementById('hiddenSetTourForm').style.display = "block";
            document.getElementById('confirmationMessage').style.display = "none";
        }, 1000);
    } else {
        alert('Vui lòng điền đầy đủ thông tin bắt buộc.');
    }
}

function showConfirmationMessage() {
    document.getElementById('hiddenSetTourForm').style.display = "none";
    document.getElementById('confirmationMessage').style.display = "block";

    setTimeout(function () {
        document.getElementById('confirmationMessage').style.display = "none";
    }, 3000);
}

// tạo hàm nhận vào id của the, trả về hành động tắt divv đó
function showTabText(tabId) {
    hideAllTabs();
    document.getElementById(tabId).classList.add('showTab');
}

function hideAllTabs() {
    const tabs = document.querySelectorAll('.hiddenFormTab');
    tabs.forEach(tab => {
        tab.classList.remove('showTab');
    });
}
