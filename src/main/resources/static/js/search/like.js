class LikeApi {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new LikeApi();
        }
        return this.#instance;
    }

}

class LiseService {
    static #instance = null;
    static getInstance() {
        if (this.#instance == null) {
            this.#instance = new LiseService();
        }
        return this.#instance;
    }
}