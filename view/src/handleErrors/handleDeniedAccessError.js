import axios from 'axios';
import router from '@/router';

// Добавляем обработчик интерцепторов для Axios
axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    // Проверяем, что ошибка - это 401 Unauthorized
    if (error.response && error.response.status === 401) {
      // Перенаправляем пользователя на страницу входа
      router.push('/auth/authorization');
    }

    // Пробрасываем ошибку дальше для обработки другими обработчиками
    return Promise.reject(error);
  }
);
