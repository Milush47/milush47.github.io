<template>
  <div>
    <h2>Create Recipe</h2>
    <form>
      <label for="name">Name:</label>
      <input type="text" id="name" v-model="recipe.name" />
      <br />
      <label for="ingredients">Ingredients:</label>
      <input type="text" id="ingredients" v-model="recipe.ingredients" />
      <br />
      <label for="difficulty">Difficulty:</label>
      <select id="difficulty" v-model="recipe.difficulty">
        <option value="easy">Easy</option>
        <option value="medium">Medium</option>
        <option value="hard">Hard</option>
      </select>
      <br />
      <button @click.prevent="createRecipe">Create Recipe</button>
    </form>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "CreateRecipe",
  data() {
    return {
      recipe: {
        name: "",
        ingredients: "",
        difficulty: "easy",
      },
    };
  },
  methods: {
    async createRecipe() {
      try {
        await axios.post("http://localhost:8080/recipes", this.recipe);
        this.recipe = {
          name: "",
          ingredients: "",
          difficulty: "easy",
        };
        alert("Recipe created successfully!");
      } catch (err) {
        console.error(err);
        alert("An error occurred while creating the recipe");
      }
    },
  },
};
</script>
