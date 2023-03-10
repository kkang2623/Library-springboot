window.onload = () => {
    HeaderService.getInstance().loadHeader();
    ConponentEvent.getInstance().addClickEventSearchButton();
}

class ConponentEvent {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new ConponentEvent();
        }
        return this.#instance;
    }

    addClickEventSearchButton() {
        const searchButton = document.querySelector(".search-button");
        const searchInput = document.querySelector(".search-input");
        searchButton.onclick = () => {
            location.href = `http://127.0.0.1:8000/search?searchValue=${searchInput.value}`;
        }

        searchInput.onkeyup = () => {
            if (window.event.keyCode == 13) {
                searchButton.click();
            }
        }
    }
}