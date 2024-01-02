function showTab(tabId) {
    var allTabs = document.querySelectorAll('.col-50');
    for (var i = 0; i < allTabs.length; i++) {
        allTabs[i].classList.remove('selectedTab');
    }

    var selectedTab = document.querySelector('.col-50[data-tab="' + tabId + '"]');
    selectedTab.classList.add('selectedTab');

    var allDivs = document.querySelectorAll('.tabContent');
    for (var i = 0; i < allDivs.length; i++) {
        allDivs[i].classList.remove('visibleDiv');
        allDivs[i].classList.add('hiddenDiv');
    }

    var selectedDiv = document.getElementById(tabId);
    selectedDiv.classList.remove('hiddenDiv');
    selectedDiv.classList.add('visibleDiv');
}

function changeStatus(div) {
    var button1 = document.querySelector('.requestStatus-1');
    var button0 = document.querySelector('.requestStatus-0');
    var requestDiv = document.getElementById(div);

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