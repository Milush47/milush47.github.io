import store from '../service/store';

export default function auth(to, from, next) {
  if (store.state.authenticated) {
    // Пользователь авторизован, разрешаем переход на страницу профиля
    next();
  } else {
    // Пользователь неавторизован, перенаправляем на страницу входа
    next('/auth/authenticate');
  }
}
