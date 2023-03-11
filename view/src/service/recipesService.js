import axios from 'axios';

export async function getRecipes() {
    const response = await this.$axios.get('/catalog');
    return response.data;
}