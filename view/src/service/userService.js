import axios from "axios";
import setAuthHeader from "./setAuthHeader";

const BASE_URL = "http://localhost:8080";

const userService = {
  login: async function (request) {
    try {
      const response = await axios.post(
        `${BASE_URL}/auth/authenticate`,
        request
      );

      const token = response.data.data.JWT;
      localStorage.setItem("token", token);

      localStorage.setItem("user", JSON.stringify(response.data.data.user));
    } catch (error) {
      console.error(error);
      throw new Error(error.response.message);
    }
  },

  logout: async function () {
    localStorage.clear();
  },

  getCurrentUser: async function () {
    try {
      const response = localStorage.getItem("token");

      return response.data;
    } catch (error) {
      console.error(error);

      throw new Error(error.response.message);
    }
  },

  register: async function (registerRequest) {
    try {
      const response = await axios.post(
        `${BASE_URL}/auth/register`,
        registerRequest
      );

      localStorage.setItem("token", response.data.data.JWT);

      const verificationToken = response.data.data.verificationToken;
      localStorage.setItem("verificationToken", verificationToken);
    } catch (error) {
      console.error(error.response.message);
      throw new Error(error.response.message);
    }
  },

  confirmRegistration: async function (verificationToken) {
    try {
      const JWT = localStorage.getItem("token");

      setAuthHeader(JWT);

      const response = await axios.post(
        `${BASE_URL}/auth/confirmRegistration/${verificationToken}`
      );

      return response.data;
    } catch (error) {
      console.error(error);
      throw new Error(error.response.message);
    }
  },

  // verifyEmail: async function (verificationToken) {
  //   try {
  //     const response = await axios.post(`${BASE_URL}/auth/confirmRegistration`, {
  //       params: {
  //         verificationToken: verificationToken,
  //       },

  //     });

  //     return response.data;
  //   } catch (error) {
  //     console.error(error);
  //     throw new Error(error.response.message);
  //   }
  // },

  verifyEmail: async function (verificationToken) {
    try {
      setAuthHeader(localStorage.getItem("token"));

      const response = await axios.post(
        `${BASE_URL}/auth/confirmRegistration?verificationToken=${verificationToken}`
      );
      return response.data;
    } catch (error) {
      console.error(error);
      throw new Error(error.response.message);
    }
  },

  resetPassword: async function (resetPasswordRequest) {
    try {
      const response = await axios.post(
        `${BASE_URL}/auth/resetPassword`,
        resetPasswordRequest
      );

      return response.data;
    } catch (error) {
      console.error(error);
      throw new Error(error.response.message);
    }
  },

  provideEmail: async function (emailRequest) {
    try {
      const response = await axios.post(
        `${BASE_URL}/auth/provideEmail`,
        JSON.stringify(emailRequest)
      );

      return response.data;
    } catch (error) {
      console.error(error);
      throw new Error(error.response.message);
    }
  },
};

export default userService;
