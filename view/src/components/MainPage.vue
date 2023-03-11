<template>
  <section class="showcase bg-dark">
    <div class="container text-center cont">
      <div class="text-showcase">
        <h1 class="text-light showcase-title">
          Шукай улюблені рецепти прямо зараз!
        </h1>

        <router-link to="/catalog" class="btn btn-light catalog-btn"
          >Каталог рецептів</router-link
        >
      </div>

      <img class="showcase-image" src="@ast/img/2.jpg" alt="" />
    </div>
  </section>

  <section class="recipies">
    <div class="container cont2">
      <h1 class="text-dark text-center recipies-main-title">
        Приготуйте сьогодні щось нове:
      </h1>
      <div class="recipies-items">
        <div class="recipie-frame">
          <img
            class="recipie-img"
            src="@ast/img/monika-grabkowska-P1aohbiT-EY-unsplash.jpg"
            alt=""
          />

          <div class="recipies-text text-center">
            <h2 class="recipie-text-title">Пиріг "Дохла русня"</h2>
            <div class="recipie-text-overview">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras
              sodales facilisis lorem. Duis vestibulum lectus magna. Duis metus
              velit, posuere eleifend diam nec, scelerisque luctus lorem. Nulla
              ac risus elit. Sed porttitor est arcu, eget malesuada nisl tempor
              vulputate.
            </div>
            <router-link to="/recipe/1" class="btn btn-primary btn-recipie">
              Детальніше
            </router-link>
          </div>
        </div>

        <div class="recipie-frame spec-frame">
          <div class="recipies-text recipies-text2 text-center">
            <h2 class="recipie-text-title">Стейк "Москальске немовля"</h2>
            <div class="recipie-text-overview">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras
              sodales facilisis lorem. Duis vestibulum lectus magna. Duis metus
              velit, posuere eleifend diam nec, scelerisque luctus lorem. Nulla
              ac risus elit. Sed porttitor est arcu, eget malesuada nisl tempor
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

        <div class="recipie-frame">
          <img
            class="recipie-img"
            src="@ast/img/anna-tukhfatullina-food-photographer-stylist-Mzy-OjtCI70-unsplash.jpg"
            alt=""
          />

          <div class="recipies-text text-center">
            <h2 class="recipie-text-title">Чіз-кейк "Бандеромобіль"</h2>
            <div class="recipie-text-overview">
              Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras
              sodales facilisis lorem. Duis vestibulum lectus magna. Duis metus
              velit, posuere eleifend diam nec, scelerisque luctus lorem. Nulla
              ac risus elit. Sed porttitor est arcu, eget malesuada nisl tempor
              vulputate.
            </div>
            <router-link to="/recipe/1" class="btn btn-primary btn-recipie">
              Детальніше
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </section>

  <svg viewBox="0 0 1440 320">
    <path
      fill="#EBB582"
      fill-opacity="1"
      d="M0,64L60,85.3C120,107,240,149,360,138.7C480,128,600,64,720,64C840,64,960,128,1080,128C1200,128,1320,64,1380,32L1440,0L1440,320L1380,320C1320,320,1200,320,1080,320C960,320,840,320,720,320C600,320,480,320,360,320C240,320,120,320,60,320L0,320Z"
    ></path>
  </svg>

  <section class="create-recipe">
    <div class="cont3">
      <h1 class="create-recipe-title text-center">
        Створюйте власні рецепти та діліться ними!
      </h1>

      <h3 class="create-recipe-text text-center">
        Подаруйте свою кулінарну мастерність та отримуйте нові знання за
        допомогою можливості додавання рецептів користувачами
      </h3>

      <router-link to="create-recipe" class="btn btn-secondary create-button">
        Створити рецепт
      </router-link>
    </div>
  </section>

  <svg class="wave2" viewBox="0 0 1440 320">
    <path
      fill="#EBB582"
      fill-opacity="1"
      d="M0,224L48,197.3C96,171,192,117,288,117.3C384,117,480,171,576,192C672,213,768,203,864,165.3C960,128,1056,64,1152,48C1248,32,1344,64,1392,80L1440,96L1440,0L1392,0C1344,0,1248,0,1152,0C1056,0,960,0,864,0C768,0,672,0,576,0C480,0,384,0,288,0C192,0,96,0,48,0L0,0Z"
    ></path>
  </svg>

  <section class="products">
    <div class="container cont4">
      <img
        src="@ast/img/no-revisions-ixS7UCRJTdM-unsplash.jpg"
        alt=""
        class="products-image"
      />

      <div class="products-text text-center">
        <h1 class="products-text-title">
          Переглядайте, які продукти Вам знадобляться для страви і які Ви вже
          маєте
        </h1>

        <router-link
          to="/my-products"
          class="btn btn-outline-success products-go-to"
          >Перейти до розділу продуктів</router-link
        >
      </div>
    </div>
  </section>

  <section class="questions">
    <div class="container cont5">
      <h1 class="questions-title">
        Маєте якісь питання чи побажання? Напишіть нам!
      </h1>

      <form class="review-area">
        <input
          class="form-control question-form"
          type="name"
          placeholder="Ім'я"
          aria-label="Ім'я"
        />
        <input
          class="form-control question-form"
          type="email"
          placeholder="Електронна адреса"
          aria-label="Електронна адреса"
          required
        />
        <input
          class="form-control question-form question-form-special"
          type="text"
          placeholder="Ваш відгук"
          aria-label="Відгук"
          required
        />
        <button class="btn btn-secondary quest-btn" type="submit">
          Відправити
        </button>
      </form>
    </div>
  </section>
</template>

<script>
export default {
  name: "MainPage",
};
</script>

<style scoped></style>
