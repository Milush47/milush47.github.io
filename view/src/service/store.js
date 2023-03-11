import Vuex from 'vuex';

const store = new Vuex.Store({
    state: {
      authenticated: false
    },
    mutations: {
      SET_AUTHENTICATED(state, value) {
        state.authenticated = value;
      }
    }
  });
  
export default store;