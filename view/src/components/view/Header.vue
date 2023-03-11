<template>
  <nav class="navbar navbar-expand-xl sticky-top navbar-dark nnav">
    <div class="container">
      <div class="logo-and-nav-items">
        <router-link to="/" class="navbar-brand ms-1 logo-text">
          <img
            src="@ast/img/logo.png"
            alt="Logo"
            width="30"
            height="24"
            class="me-1"
          />
          Рецепти ♆
        </router-link>
      </div>

      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navmenu"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navmenu">
        <form class="d-flex search-area" role="search">
          <input
            class="form-control ms-4 me-2"
            type="search"
            placeholder="Шукати рецепт"
            aria-label="Пошук"
          />
          <button class="btn btn-success" type="submit">Пошук</button>
        </form>
        <ul class="navbar-nav ms-auto">
          <li class="nav-item">
            <router-link
              to="/catalog"
              class="nav-link"
              style="text-align: center"
            >
              Каталог рецептів
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              to="/my-recipes"
              class="nav-link"
              style="text-align: center"
            >
              Мої рецепти
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              to="/my-products"
              class="nav-link"
              style="text-align: center"
            >
              Мої продукти
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              to="/create-recipe"
              class="nav-link"
              style="text-align: center"
            >
              Створити рецепт
            </router-link>
          </li>
          <li class="nav-item">
            <router-link
              to="/profile"
              class="nav-link"
              style="text-align: center"
            >
              Мій профіль
            </router-link>
          </li>
          <li class="nav-item text-center me-sm-2 ms-sm-2">
            <router-link
              to="/auth/authenticate"
              class="btn btn-outline-light account-button"
              style="margin-bottom: 2%"
              >Увійти</router-link
            >
          </li>
          <li class="nav-item text-center">
            <router-link
              to="/auth/register"
              class="btn btn-primary account-button"
              >Зареєструватися</router-link
            >
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  name: "Header",
};
</script>
