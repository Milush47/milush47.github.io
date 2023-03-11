import axios from "axios";

axios.interceptors.response.use(function (response) {
    // Do something with response data
    return response;
  }, function (error) {
    // Handle 401 error globally
    if (error.response && error.response.status === 401) {
      // Redirect to login page or show error message
      // depending on your application's requirements
      router.push('/login');
    }
    return Promise.reject(error);
  });

export default axios;