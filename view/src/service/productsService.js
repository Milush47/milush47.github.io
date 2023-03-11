import axios from "axios";

export async function getProducts() {
    const response = await this.$axios.get('/my-products');
    return response.data;
}