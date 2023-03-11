<template>
  <section onload="getAllRecipes" class="recipes">
    <div class="container">
      <h1 class="title">Каталог рецептів</h1>

      <div class="filters">
        <div class="container">
          <Filter />
        </div>
      </div>

      <div class="recipie-frame spec-frame catalog-frame">
        <div class="recipies-text recipies-text2 text-center">
          <h2 class="recipie-text-title">Стейк "Москальске немовля"</h2>
          <div class="recipie-text-overview">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras
            sodales facilisis lorem. Duis vestibulum lectus magna. Duis metus
            velit, posuere eleifend diam nec, scelerisque luctus lorem. Nulla ac
            risus elit. Sed porttitor est arcu, eget malesuada nisl tempor
            vulputate.
          </div>
          <router-link to="/recipe/1" class="btn btn-primary btn-recipie">
            Детальніше
          </router-link>
        </div>

        <img
          class="recipie-img recipie-img2"
          src="@ast/img/chad-montano-lP5MCM6nZ5A-unsplash.jpg"
          alt=""
        />
      </div>

      <div class="between"></div>
      <div class="recipie-frame spec-frame catalog-frame">
        <div class="recipies-text recipies-text2 text-center">
          <h2 class="recipie-text-title">Стейк "Москальске немовля"</h2>
          <div class="recipie-text-overview">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras
            sodales facilisis lorem. Duis vestibulum lectus magna. Duis metus
            velit, posuere eleifend diam nec, scelerisque luctus lorem. Nulla ac
            risus elit. Sed porttitor est arcu, eget malesuada nisl tempor
            vulputate.
          </div>
          <router-link to="/recipe/1" class="btn btn-primary btn-recipie">
            Детальніше
          </router-link>
        </div>

        <img
          class="recipie-img recipie-img2"
          src="@ast/img/chad-montano-lP5MCM6nZ5A-unsplash.jpg"
          alt=""
        />
      </div>

      <div class="between"></div>
      <div class="recipie-frame spec-frame catalog-frame">
        <div class="recipies-text recipies-text2 text-center">
          <h2 class="recipie-text-title">Стейк "Москальске немовля"</h2>
          <div class="recipie-text-overview">
            Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras
            sodales facilisis lorem. Duis vestibulum lectus magna. Duis metus
            velit, posuere eleifend diam nec, scelerisque luctus lorem. Nulla ac
            risus elit. Sed porttitor est arcu, eget malesuada nisl tempor
            vulputate.
          </div>
          <router-link to="/recipe/1" class="btn btn-primary btn-recipie">
            Детальніше
          </router-link>
        </div>

        <img
          class="recipie-img recipie-img2"
          src="@ast/img/chad-montano-lP5MCM6nZ5A-unsplash.jpg"
          alt=""
        />
      </div>

      <div class="between"></div>

      <nav class="pager" aria-label="...">
        <ul class="pagination">
          <li class="page-item disabled">
            <a class="page-link" href="#" tabindex="-1" aria-disabled="true"
              >Попередня</a
            >
          </li>
          <li class="page-item active">
            <a class="page-link" href="#">1</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">2</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">3</a>
          </li>
          <li class="page-item">
            <a class="page-link" href="#">Наступна</a>
          </li>
        </ul>
      </nav>
    </div>
  </section>
</template>

<script>
import Filter from "./view/Filters.vue";
import axios from "axios";
import { getRecipes } from "@/service/recipesService";
export default {
  name: "CatalogPage",
  components: {
    Filter,
  },
  methods: {
    getAllRecipes() {
      getRecipes();
      console.log(getRecipes());
    }
  },
};
</script>

<style scoped>
@font-face {
  font-family: fixelMedium;
  src: url("@ast/fonts/MacPawFixelText-Medium.otf");
}
@font-face {
  font-family: fixelRegular;
  src: url("@ast/fonts/MacPawFixelText-Regular.otf");
}
@font-face {
  font-family: fixelBold;
  src: url("@ast/fonts/MacPawFixelText-Bold.otf");
}

.recipes {
  min-height: 87.5vh;
}

.title {
  font-family: fixelMedium;
  text-align: center;
  margin-top: 2.5vh;
}
.pager {
  display: flex;
  justify-content: center;
  /* display: none !important; */
}
</style>
