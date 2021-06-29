const tabItem = document.querySelectorAll(".main__tab-menu-item");
    const tabContent = document.querySelectorAll(".main__tab-content-concern");

    tabItem.forEach((item) => {
      item.addEventListener("click", tabHandler);
    });

    function tabHandler(item) {
      const tabTarget = item.currentTarget;
      const target = tabTarget.dataset.tab;
      tabItem.forEach((title) => {
        title.classList.remove("active");
      });
      tabContent.forEach((target) => {
        target.classList.remove("target");
      });
      document.querySelector("#" + target).classList.add("target");
      tabTarget.classList.add("active");
    }