<template>
  <div>
    <h2>Filter Recipes</h2>
    <form>
      <label for="ingredient">Ingredient:</label>
      <input type="text" id="ingredient" v-model="filter.ingredient" />
      <br />
      <label for="prep_time">Preparation Time (in minutes):</label>
      <input type="number" id="prep_time" v-model="filter.prep_time" />
      <br />
      <label for="difficulty">Difficulty:</label>
      <select id="difficulty" v-model="filter.difficulty">
        <option value="">All</option>
        <option value="easy">Easy</option>
        <option value="medium">Medium</option>
        <option value="hard">Hard</option>
      </select>
      <br />
      <button @click.prevent="applyFilter">Apply Filter</button>
    </form>
    <h3>Recipes</h3>
    <ul v-if="recipes.length">
      <li v-for="recipe in recipes">
        {{ recipe.name }} - {{ recipe.ingredients }} - {{ recipe.prep_time }} -
        {{ recipe.difficulty }}
      </li>
    </ul>
    <p v-else>No recipes found</p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      filter: {
        ingredient: "",
        prep_time: "",
        difficulty: "",
      },
      recipes: [],
    };
  },
  methods: {
    async applyFilter() {
      try {
        const res = await axios.get("http://localhost:8080/recipes", {
          params: this.filter,
        });
        this.recipes = res.data;
      } catch (err) {
        console.error(err);
      }
    },
  },
};
</script>
